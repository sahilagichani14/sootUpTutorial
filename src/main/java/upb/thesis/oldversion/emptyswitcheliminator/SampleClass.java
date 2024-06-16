package upb.thesis.oldversion.emptyswitcheliminator;

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
