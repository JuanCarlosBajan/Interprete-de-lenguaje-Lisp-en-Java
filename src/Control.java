import javax.lang.model.util.Elements;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Control {

    public static HashMap<String, String> vars = new HashMap<>();
    public static HashMap<String, String> using = new HashMap<>();
    private List<String> operators = Arrays.asList(new String[]{"+", "-", "*", "/"});
    OpAritmeticas operations = new OpAritmeticas();

    /**
     * Esta es la funcion mas importante del programa.
     * Recibe como parametro un String, el cual analiza a profundidad para encontrar las claves.
     * Con ellas, redirige el codigo a donde debe para llegar a una conclusion. Es importante
     * mencionar que este metodo solo se correra una vez.
     * */


    public boolean Process(String expression) {

        boolean ans = true;
        String ArithmeticAnswer = "";

        String[] splitedExpression = expression.split("\\(|\\)");

        boolean foundArithmetic = false;
        boolean foundFunciton = false;
        boolean foundVar = false;

        for(int i = 0; i < (Math.min(splitedExpression.length, 3)) ; i++){
            if(operators.contains(clean(splitedExpression[i]))){
                foundArithmetic = true;
            }
        }

        for(int i = 0; i < (Math.min(splitedExpression.length, 3)) ; i++){

            if((clean(splitedExpression[i])).equals("defun")){
                foundFunciton = true;
            }
        }

        for(int i = 0; i < (Math.min(splitedExpression.length, 3)) ; i++){
            if((clean(splitedExpression[i])).equals("setq")){
                foundVar = true;
            }
        }


        if(foundArithmetic){ArithmeticAnswer = String.valueOf(operations.Process(expression));}

        if(foundFunciton && FunctionCounting(expression)){



        }

        if(foundFunciton && !FunctionCounting(expression)){
            ans = false;
        }


        if(foundVar){System.out.println("VARIABLE");}



        System.out.println(ArithmeticAnswer);

        return ans;

    }

    private String clean(String value){
        String ans = "";

        String[] splitedValue = value.split(" ");
        boolean found = false;

        for (int i= 0; i< splitedValue.length; i++){
            String[] doubleSplitedValue = value.split("");
            for(int ii= 0; ii < doubleSplitedValue.length; ii++){
                if(splitedValue[i].equals(" ")){
                    splitedValue[i] = "";
                }
                if(operators.contains(splitedValue[i])){
                    ans = "+";
                    found= true;
                }
            }

            if(!found){
                ans = splitedValue[0];
            }

        }
        return ans;

    }

    private boolean FunctionCounting(String expression){

        boolean ans = true;

        int CountParenthesis = 0;

        for(int i = 0; i<expression.length();i++){
            if (expression.charAt(i) == '('){CountParenthesis++;}
            if (expression.charAt(i) == ')'){CountParenthesis--;}
        }

        if (CountParenthesis > 0) ans = false;

        return ans;

    }

    public static HashMap<String, String> getVars(){
        return  vars;
    }

}
