package upb.thesis.bodyinterceptor;

import sootup.core.inputlocation.AnalysisInputLocation;
import sootup.core.jimple.common.constant.IntConstant;
import sootup.core.jimple.common.stmt.JAssignStmt;
import sootup.core.model.SootClass;
import sootup.core.model.SootMethod;
import sootup.core.signatures.MethodSignature;
import sootup.core.types.ClassType;
import sootup.core.util.ImmutableUtils;
import sootup.java.bytecode.inputlocation.JavaClassPathAnalysisInputLocation;
import sootup.java.core.JavaIdentifierFactory;
import sootup.java.core.JavaSootClass;
import sootup.java.core.interceptors.DeadAssignmentEliminator;
import sootup.java.core.types.JavaClassType;
import sootup.java.core.views.JavaView;
import sootup.java.sourcecode.inputlocation.JavaSourcePathAnalysisInputLocation;

import java.util.Collections;
import java.util.HashSet;

public abstract class BaseSetup {
    public void setUp() {
        AnalysisInputLocation inputLocation =
                new JavaSourcePathAnalysisInputLocation(null,
                        ImmutableUtils.immutableSet("src/main/java/upb/thesis/bodyinterceptor/"),
                        null,
                        Collections.singletonList(new DeadAssignmentEliminator()));

        JavaView view = new JavaView(inputLocation);
        view.getClasses().forEach(javaSootClass -> {
            javaSootClass.getName();
        });

        JavaClassType classType = JavaIdentifierFactory.getInstance().getClassType("upb.thesis.bodyinterceptor.SampleClass");
        //ClassType classType = view.getIdentifierFactory().getClassType("upb.thesis.bodyinterceptor.SampleClass");
        System.out.println(classType.getClassName());

        MethodSignature methodSignature = view.getIdentifierFactory()
                .getMethodSignature(classType, "testDeadAssignmentEliminator", "String", Collections.emptyList());
        System.out.println(methodSignature.getName());

//        if (!view.getClass(classType).isPresent()) {
//            System.out.println("Class not found.");
//        }

        /*

        SootClass sootClass = view.getClass(classType).get();
        System.out.println(sootClass.getName());

        if (!view.getMethod(methodSignature).isPresent()) {
            System.out.println("Method not found.");
            return;
        }

        SootMethod method = view.getMethod(methodSignature).get();
        System.out.println(method.getBody());

        // Check if l1 = 3 is not present, i.e., body interceptor worked
        boolean interceptorWorked =
                method.getBody().getStmts().stream()
                        .noneMatch(
                                stmt ->
                                        stmt instanceof JAssignStmt
                                                && ((JAssignStmt) stmt).getRightOp().equivTo(IntConstant.getInstance(3)));

        if (interceptorWorked) {
            System.out.println("Interceptor worked as expected.");
        } else {
            System.out.println("Interceptor did not work as expected.");
        }
         */
    }

}

