package org.example;

import sootup.core.inputlocation.AnalysisInputLocation;
import sootup.java.core.views.JavaView;
import sootup.java.sourcecode.inputlocation.JavaSourcePathAnalysisInputLocation;

/**
 * Hello world!
 *
 */
public class App
{
    public App(){
    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        App app = new App();
        app.appFunc();

        AnalysisInputLocation inputLocation =
                new JavaSourcePathAnalysisInputLocation("org.example.App");

        JavaView view = new JavaView(inputLocation);
    }

    public void appFunc(){
    }
}
