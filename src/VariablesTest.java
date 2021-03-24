import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class VariablesTest {
    Variables variables=new Variables();
    Control control = new Control();
    @Test
    public void Operate(){
        variables.Process("(setq x 5)");
        variables.Process("(setq b (+ 5 (/ 8 8)))");
        variables.Process("(setq a (cond ((equal x 4) (+ x 1) (+ 1 1))))");

    }
}