package upb.thesis.CG;

import sootup.callgraph.CallGraph;
import sootup.callgraph.ClassHierarchyAnalysisAlgorithm;
import sootup.callgraph.GraphBasedCallGraph;
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
        // iterate CHA CG using BFS

        for (MethodSignature srcNode : chaMethodSignatures) {
            String src = srcNode.getDeclClassType().getFullyQualifiedName() + " " + srcNode.getName();
            System.out.println("------ " + src + " ------");
            Set<MethodSignature> outNodes = chaCG.callsFrom(srcNode);
            for (MethodSignature targetNode : outNodes) {
                String tgt = targetNode.getDeclClassType().getFullyQualifiedName() + " " + targetNode.getName();
                System.out.println("Edge:  " + src + " --> " + tgt);
            }
        }

        Set<MethodSignature> rtaMethodSignatures = rtaCG.getMethodSignatures();
        Set<MethodSignature> uniqueMethodSignatures = chaMethodSignatures;
        uniqueMethodSignatures.removeAll(rtaMethodSignatures);
        System.out.println("================================================");

        for (MethodSignature srcNode : rtaMethodSignatures) {
            String src = srcNode.getDeclClassType().getFullyQualifiedName() + " " + srcNode.getName();
            System.out.println("------ " + src + " ------");
            Set<MethodSignature> outNodes = rtaCG.callsFrom(srcNode);
            for (MethodSignature targetNode : outNodes) {
                String tgt = targetNode.getDeclClassType().getFullyQualifiedName() + " " + targetNode.getName();
                System.out.println("Edge:  " + src + " --> " + tgt);
            }
        }

    }

}

