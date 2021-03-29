import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class FunctionTest {

    Control control = new Control();
    Function f = new Function("(defun factorial (x) (cond ((equal x 1) 1) ((equal x 0) 1) (t (* (factorial (- x 1)) x ))))");
    Function h = new Function("(defun fibo (x) (cond ((equal x 0) 0) ((equal x 1) 1) (t (+ (fibo (- x 1)) (fibo (- x 2))) )))");

    OpAritmeticas aritmetics = new OpAritmeticas();

    @org.junit.jupiter.api.Test
    void process() {

        Control.using.put("c", "6");
        //System.out.println(control.Process("(halo 5)"));
        //System.out.println(f.Process("asd", "(halo 5) 6 (+ (cond ((equal x 4) 22) ((equal x 0) 1) (t (* (factorial (- x 1)) x ))) (+ (* c 3) x (+ 5 5 5)) 7) 12"));

        System.out.println(control.Process("(fibo 21)"));

    }

}