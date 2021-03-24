import static org.junit.jupiter.api.Assertions.*;

class ControlTest {

    Control control = new Control();

    @org.junit.jupiter.api.Test
    void process() {

        control.Process("(setq hola (+ 5 5))");

    }
}