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
    Predicados predicados = new Predicados();
    Variables Var = new Variables();

    //Function funcion = new Function();
    /**
     * Esta es la funcion mas importante del programa.
     * Recibe como parametro un String, el cual analiza a profundidad para encontrar las claves.
     * Con ellas, redirige el codigo a donde debe para llegar a una conclusion. Es importante
     * mencionar que este metodo solo se correra una vez.
     * */


    public String Process(String expression) {

        String ans = "";
        String ArithmeticAnswer = "";
        String PredicateAnswer = "";

        String[] splitedExpression = expression.split("\\(|\\)");

        boolean foundArithmetic = false;
        boolean foundFunciton = false;
        boolean foundVar = false;
        boolean foundPred = false;

       /* for(int i = 0; i < (Math.min(splitedExpression.length, 3)) ; i++){
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

        for(int i = 0; i < (Math.min(splitedExpression.length, 3)) ; i++){
            if((clean(splitedExpression[i])).equals("atom")||(clean(splitedExpression[i])).equals("equal")||(clean(splitedExpression[i])).equals("list")||(clean(splitedExpression[i])).equals(">")||(clean(splitedExpression[i])).equals("<")||(clean(splitedExpression[i])).equals("quote")||(clean(splitedExpression[i])).equals("`")){
                foundPred = true;
            }
        }*/
        for(int i = 0; i < (Math.min(splitedExpression.length, 3)) ; i++){
            if(operators.contains(clean(splitedExpression[i]))){
                foundArithmetic = true;
            }
            else if((clean(splitedExpression[i])).equals("defun")){
                foundFunciton = true;
            }
            else if((clean(splitedExpression[i])).equals("setq")){
                foundVar = true;
            }
            else if((clean(splitedExpression[i])).equals("atom")||(clean(splitedExpression[i])).equals("equal")||(clean(splitedExpression[i])).equals("list")||(clean(splitedExpression[i])).equals(">")||(clean(splitedExpression[i])).equals("<")||(clean(splitedExpression[i])).equals("quote")||(clean(splitedExpression[i])).equals("`")){
                foundPred = true;
            }
            else
            {

                for (int j = 0; j < Function.funciones.keySet().toArray().length; j++) {
                    if(splitedExpression.equals(Function.funciones.keySet().toArray()[j]))
                    {
                        System.out.println(j);
                    }

                }
            }
        }

/////////////////////////////////////////////////////////////////////////////////////
        if(foundArithmetic){ans = String.valueOf(operations.Process(expression));}

        if(foundFunciton && FunctionCounting(expression)){

            ans = "";

        }

        if(foundFunciton && !FunctionCounting(expression)){
            ans = "";
        }

        if(foundVar){
            System.out.println("VARIABLE");
        }

        if(foundPred){
            ans = predicados.Process(expression);
        }

        System.out.println(ans);

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

    public static HashMap<String, String> getUsing(){
        return  using;
    }

}
