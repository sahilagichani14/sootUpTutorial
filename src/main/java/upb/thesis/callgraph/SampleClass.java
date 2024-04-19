package upb.thesis.callgraph;

public class SampleClass extends BaseSetup{
    public static void main(String[] args) {
        generateCallGraph();
    }
    private static void generateCallGraph() {
        //CallGraphExample callGraphExample = new CallGraphExample();
        //callGraphExample.firstcall();
        //callGraphExample.toString();
        new CallGraphExample().firstcall();
    }
}
