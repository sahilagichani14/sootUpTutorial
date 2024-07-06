package upb.thesis.CG;

import java.util.ArrayList;

public class SampleClass {
    public static void main(String[] args) {
        generateCallGraph();
    }
    private static void generateCallGraph() {
        CallGraphExample callGraphExample = new CallGraphExample();
        callGraphExample.firstcall();
        //callGraphExample.firstcall();
        new ArrayList<>();
    }
}
