package upb.thesis.RQ1.jb_cbf;

public class SampleClass {

    String testDeadAssignmentEliminator(){
        String x = "init";
        int a;
        Boolean dead;
        return x + " ";
    }

    void conditionalBranchFolderTest2(){
        int x = 0;
        if (x > 0){
            x++;
        } else if (x + 10 > 21){
            x--;
        } else {
            System.out.println("Do Nothing");
        }

        if (10 > 5){
            x++;
        }
    }

    void conditionalBranchFolderTest3(){
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
