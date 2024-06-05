package upb.thesis.modifiedjimple;

import sootup.core.inputlocation.AnalysisInputLocation;
import sootup.core.jimple.common.constant.IntConstant;
import sootup.core.jimple.common.stmt.JAssignStmt;
import sootup.core.model.SootClass;
import sootup.core.model.SootMethod;
import sootup.core.signatures.MethodSignature;
import sootup.core.types.ClassType;
import sootup.java.core.interceptors.DeadAssignmentEliminator;
import sootup.java.core.interceptors.EmptySwitchEliminator;
import sootup.jimple.parser.JimpleAnalysisInputLocation;
import sootup.jimple.parser.JimpleView;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.Optional;

public abstract class BaseSetup {

    public void setUp() {
        // Create a AnalysisInputLocation, which points to a directory. All jimple files will be loaded from the directory
        final String resourceDir = "E:\\germany_details\\Uni-assist LOM\\paderborn admit\\Thesis\\sootUpTutorial\\src\\main\\java\\resources";
        AnalysisInputLocation inputLocation =
                new JimpleAnalysisInputLocation(Paths.get(resourceDir), null,
                        Collections.singletonList(new EmptySwitchEliminator()));

        // Create a new JimpleView based on the input location
        JimpleView view = new JimpleView(inputLocation);

        // Create a signature for the class we want to analyze
        ClassType classType = view.getIdentifierFactory().getClassType("Target");
        final Optional<SootClass> classSource = view.getClass(classType);
        SootClass sootClass = view.getClass(classType).get();

        // Create a signature for the method we want to analyze
        MethodSignature methodSignature =
                view.getIdentifierFactory()
                        .getMethodSignature(classType, "testEmptySwitchEliminator", "void", Collections.emptyList());

        // Check if class is present
        if (!classSource.isPresent()) {
            System.out.println("Class not found.");
            return;
        }

        // Retrieve method
        if (!view.getMethod(methodSignature).isPresent()) {
            System.out.println("Method not found.");
            return;
        }

        SootMethod method = view.getMethod(methodSignature).get();

        System.out.println(method.getBody());

//        // DeadAssignmentEliminator -> Check if l1 = 3 is not present, i.e., body interceptor worked
//        boolean interceptorWorked =
//                method.getBody().getStmts().stream()
//                        .noneMatch(
//                                stmt ->
//                                        stmt instanceof JAssignStmt
//                                                && ((JAssignStmt) stmt).getRightOp().equivTo(IntConstant.getInstance(3)));

                // DeadAssignmentEliminator -> Check if l1 = 3 is not present, i.e., body interceptor worked

//        boolean interceptorWorked =
//                method.getBody().getStmts().stream()
//                        .noneMatch(
//                                stmt ->
//                                        stmt instanceof JAssignStmt
//                                                && ((JAssignStmt) stmt).getRightOp().equivTo(IntConstant.getInstance(3)));
//
//        if (interceptorWorked) {
//            System.out.println("Interceptor worked as expected.");
//        } else {
//            System.out.println("Interceptor did not work as expected.");
//        }

    }

    /*
    protected JavaView view;
    public void setUp() {
        String classPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "resources";
        String jimpleFileContent = "";
        System.out.println(classPath);

        try {
            File myObj = new File(classPath + File.separator + "Target.jimple");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                jimpleFileContent = myReader.nextLine();
                System.out.println(jimpleFileContent);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred, File Not Found Exception.");
            e.printStackTrace();
        }

        Path pathToJimple = Paths.get("src/main/java/resources");
        AnalysisInputLocation inputLocation = PathBasedAnalysisInputLocation.create(pathToJimple, null);
        View view = new JavaView(inputLocation);

        ClassType classType =
                view.getIdentifierFactory().getClassType("upb.thesis.callgraph.SampleClass");
        System.out.println("Class Type:" + classType);
     */

}

