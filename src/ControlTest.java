import static org.junit.jupiter.api.Assertions.*;

class ControlTest {

    Control control = new Control();

    @org.junit.jupiter.api.Test
    void process() {

        control.Process("(cond ((equal (atom `(- 9 9)) (listp `(+ (+ 9 9) 5))) (+ 8 (+ 9 (- 6 2))) (> 8 9)))");

    }
}