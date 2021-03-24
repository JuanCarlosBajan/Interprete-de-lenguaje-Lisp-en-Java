import static org.junit.jupiter.api.Assertions.*;

class ControlTest {

    Function f = new Function("(defun hola (aasdasd sdsasda ddsadsd) (atom asd)");
    Control control = new Control();

    @org.junit.jupiter.api.Test
    void process() {

        control.Process("hola");

    }
}