package upb.thesis.callgraph;

public class SampleClass extends BaseSetup{
    public static void main(String[] args) {
        generateCallGraph();
    }
    private static void generateCallGraph() {
        new CallGraphExample().firstcall();
    }
}