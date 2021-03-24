import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class VariablesTest {
    Variables variables=new Variables();
    @Test
    public void Operate(){
        System.out.println(variables.Process("(setq x 5)"));
    }
}