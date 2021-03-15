import javax.lang.model.util.Elements;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Control {

    public static HashMap<String, String> vars = new HashMap<>();
    private List<String> operators = Arrays.asList(new String[]{"+", "-", "*", "/"});
    private List<String> instructions = Arrays.asList(new String[]{"defun", "setq", "if"});
    OpAritmeticas operations = new OpAritmeticas();

    /**
     * Esta es la funcion mas importante del programa.
     * Recibe como parametro un String, el cual analiza a profundidad para encontrar las claves.
     * Con ellas, redirige el codigo a donde debe para llegar a una conclusion. Es importante
     * mencionar que este metodo solo se correra una vez ya que, las proximas veces que se
     * solicita este mismo metodo, se recibe como parametro una Lista, por lo que debe haber
     * un Override del mismo.
     * */
    public void Process(String expression) {

        String ans = "";

        String[] splitedExpression = expression.split("");
        boolean foundArithmetic = false;
        for(int i = 0; i < 4 ; i++){
            if(operators.contains(splitedExpression[i])){
                foundArithmetic = true;
            }
        }

        if(foundArithmetic){
            ans = String.valueOf(operations.Process(expression));
        }

        System.out.println(ans);

    }


    public static HashMap<String, String> getVars(){
        return  vars;
    }

}
