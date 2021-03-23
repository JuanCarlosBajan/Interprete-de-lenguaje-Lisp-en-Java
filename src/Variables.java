import java.util.HashMap;
import java.util.Map;

public class Variables {
    OpAritmeticas aritmetics = new OpAritmeticas();
    public static HashMap<String, Integer> variables = new HashMap<String , Integer>();

    public String Process(String expression) {

        String res = "";

        //verificar la existencia de una variable
        for (String i : variables.keySet()) {
            if(variables.get(i)==null){

            }
            else {
                res="Ya existe esa variable";
                return res;
            }
        }


        String[] splitedExpression = expression.split("\\(|\\)");
        String expsplit = "";


        //string junto sin parentesis
        expsplit = String.join("", splitedExpression);

        String[] espsplit= expsplit.split(" ");

        //comprueba si el valor de la variable es numero o no
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

        return res;
    }
}