import static org.junit.jupiter.api.Assertions.*;

class ControlTest {

    Control control = new Control();

    @org.junit.jupiter.api.Test
    void process() {

        control.Process("(+ (+ 9 8) (+ 8 7))");

    }
}