import javax.lang.model.util.Elements;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/*
 * Clase de control que se encargara que hacer con la expresion ingresada
 *
 * @author Juan Carlos Bajan
 * @author Jessica Pamela Ortiz
 * @author Jose Mariano Reyes
 *
 * */

public class Control {

    //Creacion de las variables que se usaran
    public static HashMap<String, String> vars = new HashMap<>();
    public static HashMap<String, String> using = new HashMap<>();

    //Posibles Operadores aritmeticos
    private List<String> operators = Arrays.asList(new String[]{"+", "-", "*", "/"});

    //Creacion de las clases de las posibles acciones del interprete
    OpAritmeticas operations = new OpAritmeticas();
    Predicados predicados = new Predicados();
    Variables variables = new Variables();
    Function func = new Function();
    Cond cond = new Cond();
    Variables Var = new Variables();

    /**
     * Esta es la funcion mas importante del programa.
     * Recibe como parametro un String, el cual analiza a profundidad para encontrar las claves.
     * Con ellas, redirige el codigo a donde debe para llegar a una conclusion. Es importante
     * mencionar que este metodo solo se correra una vez.
     * */

    public String Process(String expression) {


        String ans = "";

        String[] splitedExpression = expression.split("\\(|\\)");

        boolean foundArithmetic = false;
        boolean foundFunciton = false;
        boolean foundVar = false;
        boolean foundPred = false;
        boolean foundCond = false;
        boolean foundFunDefined = false;
        boolean current = false;

        for(int i = 0; i < (Math.min(splitedExpression.length, 2)) ; i++){
            if(operators.contains(clean(splitedExpression[i]))){
                foundArithmetic = true;
                break;
            }
            if((clean(splitedExpression[i])).equals("defun")){
                foundFunciton = true;
                break;
            }
            if((clean(splitedExpression[i])).equals("setq")){
                foundVar = true;
                break;
            }
            if((clean(splitedExpression[i])).equals("atom")||(clean(splitedExpression[i])).equals("equal")||(clean(splitedExpression[i])).equals("listp")||(clean(splitedExpression[i])).equals(">")||(clean(splitedExpression[i])).equals("<")||(clean(splitedExpression[i])).equals("quote")||(clean(splitedExpression[i])).equals("`")){
                foundPred = true;
                break;
            }
            if((clean(splitedExpression[i])).equals("cond")){
                foundCond = true;
                break;
            }
            if(Function.funciones.containsKey(clean(splitedExpression[i]))){
                foundFunDefined = true;
                break;

            }
        }



        if(foundArithmetic){ans = String.valueOf(operations.Process(expression));}

        if(foundFunciton && FunctionCounting(expression)){
            Function f = new Function(expression);
        }

        if(Variables.variables.containsKey(cleanString(expression))){
            ans = String.valueOf(Variables.variables.get(cleanString(expression)));
        }

        if(using.containsKey(cleanString(expression))){
            ans = String.valueOf(using.get(cleanString(expression)));
        }


        if(isNumeric(cleanString(expression))){
            ans = cleanString(expression);
        }

        if(cleanString(expression) == "t" || cleanString(expression) == "nil"){
            return cleanString(expression);
        }


        if(foundFunciton && !FunctionCounting(expression)){
            ans = "";
        }

        if(foundVar){
            variables.Process(expression);
        }

        if(foundPred){
            ans = predicados.Process(expression);
        }

        if(foundCond){
            ans = cond.Process(expression);
        }

        if(foundFunDefined){
            if(expression.charAt(0) == '('){
                expression= expression.substring(1);
            }
            if(expression.charAt(expression.length()-1) == ')'){
                expression= expression.substring(0,expression.length()-1);
            }
            int i = 0;
            for(int x = 0; x< expression.length();x++){
                if(expression.charAt(x) == ' '){
                    i = x;
                    break;
                }
            }
            ans = func.Process(expression.substring(0,i), expression.substring(i+1));
        }


        return ans;

    }

    //funcion encargada de limpiar los espacios
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

    //funcion encargada de limpiar los parentesis
    private String cleanString(String expression){
        expression.replace("(", "");
        expression.replace(")","");
        return expression.replace(" ", "");
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
    private boolean isNumeric(String expression){
        try{
            Integer.parseInt(expression);
            return  true;
        } catch (Exception e){
            return  false;
        }
    }

    public static HashMap<String, String> getVars(){
        return  vars;
    }

    public static HashMap<String, String> getUsing(){
        return  using;
    }

}
