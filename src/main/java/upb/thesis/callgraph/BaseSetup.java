package upb.thesis.callgraph;

import sootup.callgraph.CallGraph;
import sootup.callgraph.CallGraphAlgorithm;
import sootup.callgraph.ClassHierarchyAnalysisAlgorithm;
import sootup.core.inputlocation.AnalysisInputLocation;
import sootup.core.jimple.common.stmt.Stmt;
import sootup.core.model.Body;
import sootup.core.signatures.MethodSignature;
import sootup.core.typehierarchy.ViewTypeHierarchy;
import sootup.core.types.ClassType;
import sootup.java.bytecode.inputlocation.BytecodeClassLoadingOptions;
import sootup.java.bytecode.inputlocation.JavaClassPathAnalysisInputLocation;
import sootup.java.bytecode.interceptors.LocalSplitter;
import sootup.java.core.JavaProject;
import sootup.java.core.JavaSootClass;
import sootup.java.core.JavaSootMethod;
import sootup.java.core.language.JavaLanguage;
import sootup.java.core.views.JavaView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public abstract class BaseSetup {

    protected JavaView view;
    public void setUp() {

        // Create a AnalysisInputLocation, which points to a directory. All class files will be loaded
        // from the directory
        String classPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes";
        //String rtJarPath = System.getProperty("java.home") + "/lib/rt.jar";
        String rtJarPath = System.getProperty("user.dir") + File.separator + "lib" + File.separator +"rt.jar";
        List<AnalysisInputLocation> inputLocations = new ArrayList<>();
        inputLocations.add(new JavaClassPathAnalysisInputLocation(classPath));
        inputLocations.add(new JavaClassPathAnalysisInputLocation(rtJarPath)); // add rt.jar

        JavaLanguage language = new JavaLanguage(8);

        // set project as class path location
        JavaProject project = JavaProject.builder(language).addInputLocation(inputLocations.get(0)).build();
        view = project.createView();
        System.out.println("View Classes: " + view.getClasses());
        view.configBodyInterceptors( aip -> BytecodeClassLoadingOptions.Default);
        ClassType classType =
                view.getIdentifierFactory().getClassType("upb.thesis.callgraph.SampleClass");
        System.out.println("Class Type:" + classType);

        JavaSootClass sootClass = view.getClass(classType).get();
        System.out.println("Soot Class: " +sootClass);

        MethodSignature methodSignature = view.getIdentifierFactory()
                .getMethodSignature("generateCallGraph", "upb.thesis.callgraph.SampleClass", "void", Collections.emptyList());
                //.getMethodSignature(classType, "test", Collections.emptyList());
        System.out.println("Method Signature: " + methodSignature);

        // Create type hierarchy and CHA
        final ViewTypeHierarchy typeHierarchy = new ViewTypeHierarchy(view);
        //System.out.println(typeHierarchy.subclassesOf(classType));
        CallGraphAlgorithm cha = new ClassHierarchyAnalysisAlgorithm(view);
        // Create CG by initializing CHA with entry method(s)
        CallGraph cg = cha.initialize(Collections.singletonList(methodSignature));
        cg.callsFrom(methodSignature).forEach(System.out::println);

//        System.out.println("==========Tranform Methods=======");
//        Set<? extends JavaSootMethod> sootMethods =  sootClass.getMethods();
//        for (JavaSootMethod jsm: sootMethods){
//            Body.BodyBuilder builder = Body.builder(jsm.getBody(), Collections.emptySet());
//            new LocalSplitter().interceptBody(builder, view);
//            System.out.println("========Transformed Body=========");
//            System.out.println(builder.getStmtGraph().toString().trim());
//            System.out.println("========Before Jimple Body=========");
//            System.out.println(jsm.getName());
//            List<Stmt> stmts = jsm.getBody().getStmts();
//            stmts.forEach(System.out::println);
//            System.out.println("=======");

    }

}

