import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OpAritmeticasTest {

    OpAritmeticas aritmetics = new OpAritmeticas();

    @Test
    void operate() {

        System.out.println(aritmetics.Process("(/ (+ 5 (+ 3 3)) (- (- 6 3) (- 6 2)))"));

    }
}