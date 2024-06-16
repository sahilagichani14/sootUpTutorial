package upb.thesis.oldversion.unreachablecodeeliminator;

//import sootup.core.inputlocation.AnalysisInputLocation;
//import sootup.core.jimple.common.stmt.Stmt;
//import sootup.core.model.Body;
//import sootup.core.signatures.MethodSignature;
//import sootup.core.types.ClassType;
//import sootup.java.bytecode.inputlocation.BytecodeClassLoadingOptions;
//import sootup.java.bytecode.inputlocation.JavaClassPathAnalysisInputLocation;
//import sootup.java.core.JavaProject;
//import sootup.java.core.JavaSootClass;
//import sootup.java.core.JavaSootMethod;
//import sootup.java.core.language.JavaLanguage;
//import sootup.java.core.views.JavaView;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public abstract class BaseSetup {

//    protected JavaView view;
//    public void setUp() {
//        String classPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes";
//        AnalysisInputLocation inputLocation = new JavaClassPathAnalysisInputLocation(classPath, null);
//        System.out.println(classPath);
//        System.out.println(inputLocation.toString());
//        JavaLanguage language = new JavaLanguage(8);
//
//        JavaProject project = JavaProject.builder(language).addInputLocation(inputLocation).build();
//        view = project.createView();
//        System.out.println(view.getClasses());
//        view.configBodyInterceptors( aip -> BytecodeClassLoadingOptions.Default);
//        ClassType classType =
//                view.getIdentifierFactory().getClassType("upb.thesis.unreachablecodeeliminator.SampleClass");
//        System.out.println(classType);
//
//        JavaSootClass sootClass = view.getClass(classType).get();
//        System.out.println(sootClass);
//
//        MethodSignature methodSignature = view.getIdentifierFactory()
//                .getMethodSignature("makeCollection", "upb.thesis.unreachablecodeeliminator.SampleClass", "Collection", Collections.emptyList());
//                //.getMethodSignature(classType, "test", Collections.emptyList());
//        System.out.println(methodSignature);
//        System.out.println("==========Tranform Methods=======");
//        Set<? extends JavaSootMethod> sootMethods =  sootClass.getMethods();
//        for (JavaSootMethod jsm: sootMethods){
//            Body.BodyBuilder builder = Body.builder(jsm.getBody(), Collections.emptySet());
//            new UnreachableCodeEliminator().interceptBody(builder, view);
//            System.out.println("========Transformed Body=========");
//            System.out.println(builder.getStmtGraph().toString().trim());
//            System.out.println("========Jimple Body Before=========");
//            System.out.println(jsm.getName());
//            List<Stmt> stmts = jsm.getBody().getStmts();
//            stmts.forEach(System.out::println);
//            System.out.println("=======");
//        }
//    }

}

