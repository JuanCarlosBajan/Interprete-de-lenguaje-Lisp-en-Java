import static org.junit.jupiter.api.Assertions.*;

class FunctionTest {

    Function f = new Function("(defun hola (aasdasd sdsasda ddsadsd) (/ (+ 5 (+ 3 3)) (- (- 6 3) (- 6 2)))");


    @org.junit.jupiter.api.Test
    void process() {


        //System.out.println(f.devolverB("hola"));
       // f.asignarP("hola","5 5 5");
        Control.using.put("aasdasd", "6");
        f.Process("hola", "5 5 5");
    }

}