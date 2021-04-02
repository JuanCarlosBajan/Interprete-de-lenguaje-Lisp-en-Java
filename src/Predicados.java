import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Clase main del interprete Lisp
 *
 * @author Juan Carlos Bajan
 * @author Jessica Pamela Ortiz
 * @author Jose Mariano Reyes
 *
 * */

public class Predicados {

    //Metodo encargado de procesar los predicados
    public String Process(String expression){
        ArrayList<String> values = new ArrayList<>();

        OpAritmeticas aritmetics = new OpAritmeticas();
        String res="";


        boolean atom=false;
        boolean equal=false;
        boolean list=false;
        boolean mas=false;
        boolean menos=false;
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

        boolean init = false;
        int inInd = 0;
        int par = 0;
        int lastInd = 0;

        for(int i= 0; i<expression.length(); i++){
            if(expression.charAt(i) == '('){
                if (init == false) {init = true; inInd = i;}
                par++;

            } if (expression.charAt(i) == ')'){
                par--;
            }
            if(par == 0 && init){
                values.add(expression.substring(inInd,i+1));
                init = false;
                lastInd = i;
            }
        }





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
            Control control = new Control();
            if(values.size() > 0){
                if(expression.substring(4,inInd).contains("`")){
                    res = "nil";
                } else {
                    if(isNumeric(control.Process(values.get(0))) || control.Process(values.get(0)) == "t" || control.Process(values.get(0)) == "nil"){
                        res = "t";
                    } else res = "nil";
                }
            } else {

                String[] splitesp = expsplit.split(" ");

                if (splitesp.length <= 2 && isNumeric(clean(splitesp[1])) || splitesp[1].contains("`")) {
                    res = "t";
                } else if (splitesp.length <= 2 && isNumeric(control.Process(clean(splitesp[1]))) || control.Process(clean(splitesp[1])) == "t" || control.Process(clean(splitesp[1])) == "nil"){
                    res = "t";
                } else res = "nil";
            }
        }

        //equal
        if(equal==true){
            Control control = new Control();
            if(values.size() == 0){
                if (expsplit.charAt(0) == ' ')expsplit = expsplit.substring(1);
                String[] splitesp=expsplit.split(" ");

                if(splitesp.length<=2){
                    System.out.println("No hay suficientes parámetros");
                }
                else {
                    if(control.Process(analyzequote(splitesp[1])).equals(control.Process(analyzequote(splitesp[2])))){
                        res="t";
                    }
                    else{
                        res="nil";
                    }
                }
            } else if(values.size() >= 2) {
                if(control.Process(values.get(0)).equals(control.Process(values.get(1)))){
                    res = "t";
                } else {
                    res = "nil";
                }

            } else {
                if(expression.substring(5,inInd).length() > 2){
                    String local = expression.substring(5,inInd).split(" ")[1];
                    if(control.Process(analyzequote(clean(local))).equals(control.Process(values.get(0)))){
                        res="t";
                    }
                    else{
                        res="nil";
                    }
                } else if(lastInd != 0 && expression.substring(lastInd).length() > 2){

                    String local = expression.substring(lastInd);
                    if(control.Process(analyzequote(clean(local))).equals(control.Process(values.get(0)))){
                        res="t";
                    }
                    else{
                        res="nil";
                    }
                }
            }

        }

        //list
        if(list==true){
            if(values.size() > 0){
                if(expression.substring(4,inInd).contains("`")){
                    res = "t";
                } else {
                    Control control = new Control();
                    if(isNumeric(control.Process(values.get(0))) || control.Process(values.get(0)) == "t" || control.Process(values.get(0)) == "nil"){
                        res = "nil";
                    } else res = "t";
                }
            } else {
                Control control = new Control();
                String[] splitesp = expsplit.split(" ");

                if (splitesp.length <= 2 && isNumeric(clean(splitesp[1]))) {
                    res = "nil";
                } else if (splitesp.length <= 2 && isNumeric(control.Process(clean(splitesp[1]))) || control.Process(clean(splitesp[1])) == "t" || control.Process(clean(splitesp[1])) == "nil"){
                    res = "nil";
                } else res = "t";
            }
        }

        //>
        if(mas==true){
            Control control = new Control();
            if(values.size() > 1){
                if(Integer.parseInt(control.Process(values.get(0))) > Integer.parseInt(control.Process(values.get(1)))){
                    res = "t";
                } if (Integer.parseInt(control.Process(values.get(0))) < Integer.parseInt(control.Process(values.get(1)))){
                    res = "nil";
                }
            } else if (values.size() == 1){
                if(expression.substring(1,inInd).length() > 2){
                    String local = expression.substring(1,inInd).split(" ")[1];
                    if(Integer.parseInt(control.Process(clean(local))) > Integer.parseInt(control.Process(values.get(0)))){
                        res="t";
                    } else{
                        res="nil";
                    }
                } else if(lastInd != 0 && expression.substring(lastInd).length() > 2){
                    String local = expression.substring(lastInd+1);
                    if(Integer.parseInt(control.Process(clean(local))) < Integer.parseInt(control.Process(values.get(0)))){
                        res="t";
                    } else{
                        res="nil";
                    }
                }
            } else if (values.size() == 0){
                String[] splitesp=expsplit.split(" ");
                if(splitesp.length<=2){
                    System.out.println("No hay suficientes parámetros");
                }
                else {
                    try{
                        int num1 = Integer.parseInt(control.Process(clean(splitesp[1])));
                        int num2 = Integer.parseInt(control.Process(clean(splitesp[2])));

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
        }

        //<
        if(menos==true){
            Control control = new Control();
            if(values.size() > 1){
                if(Integer.parseInt(control.Process(values.get(0))) < Integer.parseInt(control.Process(values.get(1)))){
                    res = "t";
                } if (Integer.parseInt(control.Process(values.get(0))) > Integer.parseInt(control.Process(values.get(1)))){
                    res = "nil";
                }
            } else if (values.size() == 1){
                if(expression.substring(1,inInd).length() > 2){
                    String local = expression.substring(1,inInd).split(" ")[1];
                    if(Integer.parseInt(control.Process(clean(local))) < Integer.parseInt(control.Process(values.get(0)))){
                        res="t";
                    } else{
                        res="nil";
                    }
                } else if(lastInd != 0 && expression.substring(lastInd).length() > 2){
                    String local = expression.substring(lastInd+1);
                    if(Integer.parseInt(control.Process(clean(local))) > Integer.parseInt(control.Process(values.get(0)))){
                        res="t";
                    } else{
                        res="nil";
                    }
                }
            } else if (values.size() == 0){
                String[] splitesp=expsplit.split(" ");
                if(splitesp.length<=2){
                    System.out.println("No hay suficientes parámetros");
                }
                else {
                    try{
                        int num1 = Integer.parseInt(control.Process(clean(splitesp[1])));
                        int num2 = Integer.parseInt(control.Process(clean(splitesp[2])));

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
        }

        return res;
    }



    private String analyzequote(String expression){
        boolean found = false;
        if (expression.contains("`")){found = true;}
        if (found == true){
            String local = expression.substring(1);
            return local;
        } else {
            if(isNumeric(expression)){
                return expression;
            } else {
                return Control.getVars().getOrDefault(expression, expression);
                //FALTA if de FUNCIONES
            }
        }
    }


    private boolean isNumeric(String expression){
        try{
            Integer.parseInt(expression);
            return  true;
        } catch (Exception e){
            return  false;
        }
    }

    private String clean(String value){
        String ans = "";

        String[] splitedValue = value.split("");

        for (int i= 0; i< splitedValue.length; i++){
            if(splitedValue[i].equals(" ")){
                splitedValue[i] = "";
            }
        }
        ans = String.join("", splitedValue);
        return ans;
    }

}
