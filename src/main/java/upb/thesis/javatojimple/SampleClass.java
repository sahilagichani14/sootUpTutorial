package upb.thesis.javatojimple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class SampleClass {

    /*
    public static void main(String[] args) {
        Collection c = makeCollection(args[0]);
        c.add("whatever");
    }

    static Collection makeCollection(String s) {
        if (true) {
            return new ArrayList();
        } else {
            return new HashSet();
        }
    }
     */

//    void testConstantPropagation(){
//        int a = 0;
//        return;
//        a = 1;
//        a = 2;
//    }
    Boolean randomMethod() {
        if (10 > 0)
            return true;
        return false;
    }

    void testUnreachableCode() {
        int a, b;
        a = 0;
        b = 10;
        new String("ABC");
//        String s = testEmptySwitchEliminator();
//        if (s.equals("init"))
//            a=20;
//        else
//            a=60;
        System.out.println(a);
        return;
    }

    String testEmptySwitchEliminator() {
        String x = "init";
        switch (x) {
            default: {
                x = new String("Test");
            }
        }
        return x;
    }

    String testUnreach() {
        if (true) {
            System.out.println();
        }
        return "";
    }

//    public void instanceMethod(){
//        // instance method
//    }
//
//    public static void staticMethod(){
//        // static method
//    }
//
//    public void voidMethod(){
//        int a = 3;
//        int b = 4;
//        int c = a + b;
//    }
//
//    public int intMethod(){
//        int a = 0;
//        return a;
//    }
//
//    public int paramIncrementMethod(int param1){
//        return param1++;
//    }
//
//    public void branchingMethod(boolean param1){
//        int a = 0;
//        if(param1){
//            a = 1;
//        }
//    }
//
//    public void loopingMethod(){
//        int i = 0;
//        int max = 10;
//        while(i<max){
//            i++;
//        }
//    }

}
