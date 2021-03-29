import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CondTest {

    Cond cond = new Cond();

    @Test
    void process() {

        Variables.variables.put("x",0);
        Variables.variables.put("c",6);
        System.out.println(cond.Process("(cond ((equal x 4) 22) ((equal x 0) c) (t (* (factorial (- x 1)) x )))"));
    }
}