import static org.junit.jupiter.api.Assertions.*;

class FunctionTest {

    Function f = new Function("(defun hola (aasdasd sdsasda ddsadsd) (/ (+ 5a (+ 3 3)) (- (- 6 3) (- 6 2)))");


    @org.junit.jupiter.api.Test
    void process() {
        f.Process(f.body);
    }

}