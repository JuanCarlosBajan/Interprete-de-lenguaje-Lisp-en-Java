import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class FunctionTest {

    Control control = new Control();
    Function f = new Function("(defun halo (algo) (cond ((equal 6 5) (+ t 5) (+ algo 6))))");
    //Function f2 = new Function("(defun halo2 (algo) (cond ((equal 5 5) (+ (halo 10) 5) (+ 6 6))))");

    OpAritmeticas aritmetics = new OpAritmeticas();

    @org.junit.jupiter.api.Test
    void process() {

        //LinkedHashMap<String, String> lh = new LinkedHashMap<>();
        //lh.put("algo","");
        //Function.funciones.put("halo", lh);
        //Function.funcionesB.put("halo", "(cond ((equal 5 5) (+ t 5) (+ 6 6)))");
        //System.out.println(f.devolverB("hola"));
        // f.asignarP("hola","5 5 5");
        Variables.variables.put("t", 2);
        //System.out.println(f.Process("halo", "10"));
        System.out.println(control.Process("(halo 5)"));
    }

}