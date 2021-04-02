import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * Clase main del interprete Lisp
 *
 * @author Juan Carlos Bajan
 * @author Jessica Pamela Ortiz
 * @author Jose Mariano Reyes
 *
 * */

public class Variables {
    OpAritmeticas aritmetics = new OpAritmeticas();
    //Todas las variables
    public static HashMap<String, Integer> variables = new HashMap<String , Integer>();

    //Metodo encargado de crear las variables y almacenarlas
    public void Process(String expression) {
        ArrayList<String> values = new ArrayList<>();

        String[] splitedExpression = expression.split("\\(|\\)");
        boolean openingpar = false;
        boolean found = false;
        for(int i= 0; i<expression.length(); i++){
            if(expression.charAt(i) == ' ' && !found){
                expression = expression.substring(1);
            }
            if(expression.charAt(i) == '('){
                break;
            }
            if(expression.charAt(i) != '(' && expression.charAt(i) != ' '){
                break;
            }
        }


        if (expression.charAt(0) == '('){
            expression = expression.substring(1);
            openingpar = true;
        }
        if (expression.charAt(expression.length()-1) == ')' && openingpar) {
            expression = expression.substring(0,expression.length()-1);
        }

        expression = expression.substring(5);
        boolean init = false;
        int firslasttind = 0;
        int llastind = 0;
        int inInd = 0;
        int par = 0;



        for(int i= 0; i<expression.length(); i++){
            if(expression.charAt(i) == '('){
                if (init == false) {init = true; inInd = i;}
                par++;

            } if (expression.charAt(i) == ')'){
                par--;
            }
            if(par == 0 && init){
                if (firslasttind == 0){
                    firslasttind = i;
                }
                values.add(expression.substring(inInd,i+1));
                init = false;
                llastind = i;
            }
        }



        String res = "";

        boolean exists = false;

        //verificar la existencia de una variable
        for (String i : variables.keySet()) {
            if(variables.get(i)==null){

            }
            else {
                res="Ya existe esa variable";
                break;
            }
        }

        if (!exists){


            String expsplit = "";


            //string junto sin parentesis
            expsplit = String.join("", splitedExpression);

            String[] espsplit= expsplit.split(" ");

            //comprueba si el valor de la variable es numero o no
            if(values.size() == 0){
                try{
                    variables.put(espsplit[1],Integer.parseInt(espsplit[2]));
                    System.out.println("\nVariable ingresada con exito");
                }
                catch (Exception e){
                    //aqui verificar una funcion

                    String letra=espsplit[2].substring(0,1);

                    //instruccion quote
                    if(letra.equals("'")){
                        try{
                            espsplit[2].replace("'","");
                            variables.put(espsplit[1],Integer.parseInt(espsplit[2]));
                        }
                        catch (Exception d){
                            System.out.println("No se puede dar ese valor a la variable");
                        }
                    }
                    //si no hay quote
                    //se verifica la funcion
                    else{

                    }
                }
            } else {
                Control control = new Control();
                variables.put(espsplit[1],Integer.parseInt(control.Process(values.get(0))));

            }
        }


    }
}