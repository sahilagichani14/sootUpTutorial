package upb.thesis.CG;

import sootup.callgraph.CallGraph;
import sootup.callgraph.ClassHierarchyAnalysisAlgorithm;
import sootup.callgraph.RapidTypeAnalysisAlgorithm;
import sootup.core.inputlocation.AnalysisInputLocation;
import sootup.core.signatures.MethodSignature;
import sootup.core.types.ClassType;
import sootup.java.bytecode.inputlocation.JavaClassPathAnalysisInputLocation;
import sootup.java.core.types.JavaClassType;
import sootup.java.core.views.JavaView;

import java.io.File;
import java.util.*;

public class BaseSetup {

    protected JavaView view;
    public void setUp() {
        //String pathToBinary = "src/test/resources/example/challenge2.jar";
        //AnalysisInputLocation inputLocation = new JavaClassPathAnalysisInputLocation(pathToBinary);

        String classPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes";
        AnalysisInputLocation inputLocation = new JavaClassPathAnalysisInputLocation(classPath, null, Collections.emptyList());
        JavaView view = new JavaView(inputLocation);
        JavaClassType classType = view.getIdentifierFactory().getClassType("upb.thesis.CG.SampleClass");
        MethodSignature methodSignature = view
                .getIdentifierFactory()
                .getMethodSignature(
                        classType, "main", "void", Collections.singletonList("java.lang.String[]"));

        ClassHierarchyAnalysisAlgorithm chaAlgorithm = new ClassHierarchyAnalysisAlgorithm(view);
        RapidTypeAnalysisAlgorithm rtaAlgorithm = new RapidTypeAnalysisAlgorithm(view);
        CallGraph chaCG = chaAlgorithm.initialize(Collections.singletonList(methodSignature));
        CallGraph rtaCG = rtaAlgorithm.initialize(Collections.singletonList(methodSignature));
        Set<MethodSignature> chaMethodSignatures = chaCG.getMethodSignatures();
        Set<MethodSignature> rtaMethodSignatures = rtaCG.getMethodSignatures();
        Set<MethodSignature> uniqueMethodSignatures = chaMethodSignatures;
        uniqueMethodSignatures.removeAll(rtaMethodSignatures);
        List<String> packagenames =new ArrayList<>();
        for (MethodSignature uniqueMethodSignature : uniqueMethodSignatures) {
            Set<MethodSignature> callingMethod = chaCG.callsTo(uniqueMethodSignature);
            for (MethodSignature signature : callingMethod) {
                ClassType callingMethodsignatureClass = signature.getDeclClassType();
                String packageName = callingMethodsignatureClass.getPackageName().getName();
                packagenames.add(packageName);
            }
        }
        HashMap< String, Integer> countPackage  = new HashMap<>();
        for (String packagename : packagenames) {
            countPackage.putIfAbsent(packagename, 0);
            countPackage.put(packagename, countPackage.get(packagename) + 1);
        }
        System.out.println(countPackage);
        Set<String> packageList = countPackage.keySet();
        List<String> myList = new ArrayList<>(packageList);

        // Step 3: Sort the List
        Collections.sort(myList);
        System.out.println(countPackage);
        for (String element : myList) {
            System.out.println(countPackage.get(element));
        }
    }

}

