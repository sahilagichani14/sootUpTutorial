package upb.thesis.emptyswitcheliminator;

public class SampleClass {

    String testEmptySwitchEliminator(){
        String x = "init";
        switch (x){
            default: {
                x = new String("Test");
            }
        }
        return x;
    }

}
