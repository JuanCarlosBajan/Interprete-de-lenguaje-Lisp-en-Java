import static org.junit.jupiter.api.Assertions.*;

class ControlTest {

    Control control = new Control();

    @org.junit.jupiter.api.Test
    void process() {

        Variables.variables.put("algo",2);
        Control.using.put("t","3");
        System.out.println(control.Process("(cond ((= x 1.0) (1.0)) ((= x 0.0) (1.0)) (t(*(factorial (- x 1)) x )))"));

    }
}