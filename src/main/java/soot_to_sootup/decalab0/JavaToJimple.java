package soot_to_sootup.decalab0;

import sootup.core.inputlocation.AnalysisInputLocation;
import sootup.core.jimple.common.stmt.Stmt;
import sootup.core.signatures.MethodSignature;
import sootup.core.util.printer.JimplePrinter;
import sootup.java.bytecode.inputlocation.JavaClassPathAnalysisInputLocation;
import sootup.java.core.JavaSootClass;
import sootup.java.core.JavaSootMethod;
import sootup.java.core.types.JavaClassType;
import sootup.java.core.views.JavaView;
import sootup.java.sourcecode.inputlocation.JavaSourcePathAnalysisInputLocation;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class JavaToJimple {
    public static void main(String[] args) {
        JavaToJimple.setUpSootUp();
    }

    public static void setUpSootUp(){
        String sootUpCp = "E:\\java_projects\\sootUpTutorial\\src\\main\\java\\soot_to_sootup\\decalab0\\analysepkg";
        System.out.println(sootUpCp);

        AnalysisInputLocation inputLocation =
                new JavaSourcePathAnalysisInputLocation(sootUpCp);

        JavaView view = new JavaView(inputLocation);
        System.out.println(view.getClasses());

        JavaClassType classType =
                view.getIdentifierFactory().getClassType("soot_to_sootup.decalab0.analysepkg.SampleClass");
        System.out.println(classType);

        JavaSootClass sootClass = view.getClass(classType).get();
        System.out.println(sootClass);

        MethodSignature methodSignature = view.getIdentifierFactory()
                .getMethodSignature(classType, "randomMethod", "Boolean", Collections.emptyList());
        System.out.println(methodSignature);

        Set<JavaSootMethod> sootMethods =  sootClass.getMethods();
        for (JavaSootMethod jsm: sootMethods){
            System.out.println(jsm.getName());
            List<Stmt> stmts = jsm.getBody().getStmts();
            stmts.forEach(System.out::println);
            System.out.println("=======");
        }
    }
}
