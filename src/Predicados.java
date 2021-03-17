import java.util.Arrays;
import java.util.List;

public class Predicados {
    private final List<String> operators = Arrays.asList("atom", "equal", "list", ">", "<");

    public String Process(String expression){

        String res="";

        boolean atom=false;
        boolean equal=false;
        boolean list=false;
        boolean mas=false;
        boolean menos=false;

        String[] splitedExpression = expression.split("\\(|\\)");
        String expsplit="";



        //string junto sin parentesis
        expsplit=String.join("",splitedExpression);

        //tipo de predicado
        String letra=expsplit.substring(0,1);

        if(letra.equals("a")){atom=true;}
        if(letra.equals("e")){equal=true;}
        if(letra.equals("l")){list=true;}
        if(letra.equals(">")){mas=true;}
        if(letra.equals("<")){menos=true;}

        //atom
        if(atom==true){
            String[] splitesp=expsplit.split(" ");

            if(splitesp.length<=2){
                res="t";
            }
            else{
                res="nil";
            }
        }

        //equal
        if(equal==true){
            String[] splitesp=expsplit.split(" ");
            if(splitesp.length<=2){
                System.out.println("No hay suficientes parámetros");
            }
            else {
                if(splitesp[1].equals(splitesp[2])){
                    res="t";
                }
                else{
                    res="nil";
                }
            }
        }

        //list
        if(list==true){
            String[] palabras=splitedExpression[2].split(" ");
            if(palabras.length>1){
                res="t";
            }
            else{
                res="nil";
            }
        }

        //>
        if(mas==true){
            String[] splitesp=expsplit.split(" ");
            if(splitesp.length<=2){
                System.out.println("No hay suficientes parámetros");
            }
            else {
                try{
                    int num1 = Integer.parseInt(splitesp[1]);
                    int num2 = Integer.parseInt(splitesp[2]);

                    if (num1 > num2) {
                        res = "t";
                    } else {
                        res = "nil";
                    }
                }
                catch (Exception e){
                    System.out.println("No se pueden comparar letras");
                }
            }
        }

        //<
        if(menos==true){
            String[] splitesp=expsplit.split(" ");
            if(splitesp.length<=2){
                System.out.println("No hay suficientes parámetros");
            }
            else {
                try{
                    int num1 = Integer.parseInt(splitesp[1]);
                    int num2 = Integer.parseInt(splitesp[2]);

                    if (num1 < num2) {
                        res = "t";
                    } else {
                        res = "nil";
                    }
                }
                catch (Exception e){
                    System.out.println("No se pueden comparar letras");
                }
            }
        }

        return res;
    }

}
