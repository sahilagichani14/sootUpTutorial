package upb.thesis.bodyinterceptor;

public class SampleClass {

    String testDeadAssignmentEliminator(){
        String x = "init";
        int a;
        Boolean dead;
        return x + " ";
    }

    void conditionalBranchFolderTest2(){
        int x = 0;
        if ((x + 15) - 3 * 5 > 0){
            x++;
        } else if (x * 20 < 21){
            x--;
        } else {
            System.out.println("Do Nothing");
        }
    }

}
