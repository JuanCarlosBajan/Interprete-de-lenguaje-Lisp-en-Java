import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OpAritmeticasTest {

    OpAritmeticas aritmetics = new OpAritmeticas();
    Variables variables=new Variables();

    @Test
    void operate() {
        variables.Process("(setq x 6)");
        System.out.println(aritmetics.Process("(+ (/ (* x 9) 5) 32)"));

    }
}
