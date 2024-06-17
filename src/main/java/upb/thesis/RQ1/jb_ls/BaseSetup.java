package upb.thesis.RQ1.jb_ls;

import sootup.core.inputlocation.AnalysisInputLocation;
import sootup.core.model.Body;
import sootup.core.signatures.MethodSignature;
import sootup.core.types.ClassType;
import sootup.java.bytecode.inputlocation.JavaClassPathAnalysisInputLocation;
import sootup.java.core.JavaSootClass;
import sootup.java.core.JavaSootMethod;
import sootup.java.core.interceptors.ConditionalBranchFolder;
import sootup.java.core.interceptors.CopyPropagator;
import sootup.java.core.interceptors.LocalSplitter;
import sootup.java.core.views.JavaView;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class BaseSetup {
    public void setUp() {

        String classPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes";
        //String pathToBinary = "src/main/java/upb/thesis/latestsootup/example.jar";
        AnalysisInputLocation inputLocation = new JavaClassPathAnalysisInputLocation(classPath, null,
                List.of(new ConditionalBranchFolder()));
        JavaView view = new JavaView(inputLocation);
        Collection<JavaSootClass> viewClasses = view.getClasses();
        System.out.println(viewClasses);
        ClassType classType =
                view.getIdentifierFactory().getClassType("upb.thesis.RQ1.jb_ls.SampleClass");
        System.out.println(classType);
        JavaSootClass sootClass = view.getClass(classType).get();
        System.out.println(sootClass);

        MethodSignature methodSignature = view.getIdentifierFactory()
                .getMethodSignature("upb.thesis.RQ1.jb_ls.SampleClass", "localSplitterTest", "void", Collections.emptyList());
        System.out.println(methodSignature.getName());

        Set<? extends JavaSootMethod> sootMethods =  sootClass.getMethods();
        for (JavaSootMethod jsm: sootMethods){
            boolean condition = jsm.getSignature().getSubSignature().getName().equals(methodSignature.getSubSignature().getName());
            if (condition){
                System.out.println("========Before Jimple Body=========");
                System.out.println(jsm.getBody());
                System.out.println("========Transformed Body=========");
                Body.BodyBuilder builder = Body.builder(jsm.getBody(), Collections.emptySet());
                new LocalSplitter().interceptBody(builder, view);
                //new CopyPropagator().interceptBody(builder, view);
                //new ConditionalBranchFolder().interceptBody(builder, view);
                //System.out.println(builder.getStmtGraph().toString().trim());
                System.out.println(builder.getStmtGraph().toString());
            }
        }
    }

}

