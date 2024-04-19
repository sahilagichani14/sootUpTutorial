package upb.thesis.unreachablecodeeliminator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class SampleClass {

    public void main(String[] args) {
        Collection c = this.makeCollection(args[0]);
        c.add("whatever");
    }

    public Collection makeCollection(String s) {
        if (s.equals("list")) {
            return new ArrayList();
        } else {
            return new HashSet();
        }
    }

}
