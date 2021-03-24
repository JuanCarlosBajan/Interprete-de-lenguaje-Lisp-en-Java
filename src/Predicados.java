import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Predicados {
    ArrayList<String> values = new ArrayList<>();

    public String Process(String expression){
        OpAritmeticas aritmetics = new OpAritmeticas();
        String res="";

        boolean atom=false;
        boolean equal=false;
        boolean list=false;
        boolean mas=false;
        boolean menos=false;


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
        }
        if (expression.charAt(expression.length()-1) == ')') {
            expression = expression.substring(0,expression.length()-1);
        }

        boolean init = false;
        int inInd = 0;
        int par = 0;

        for(int i= 0; i<expression.length(); i++){
            if(expression.charAt(i) == '('){
                if (init == false) {init = true; inInd = i;}
                par++;

            } if (expression.charAt(i) == ')'){
                par--;
            }
            if(par == 0 && init == true){
                values.add(expression.substring(inInd,i+1));
                init = false;
            }
        }

        for(int i = 0; i<values.size();i++){
            System.out.println(values.get(i));
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
            if(values.size() == 0){
                String[] splitesp=expsplit.split(" ");
                if(splitesp.length<=2){
                    System.out.println("No hay suficientes parámetros");
                }
                else {
                    if(analyzequote(splitesp[1]).equals(analyzequote(splitesp[2]))){
                        res="t";
                    }
                    else{
                        res="nil";
                    }
                }
            } else if(values.size() >= 2) {
                Control control = new Control();
                if(control.Process(values.get(0)) == control.Process(values.get(1))){
                    res = "t";
                } else {
                    res = "nil";
                }

            } else {
                if(expression.substring(5,inInd).length() > 2){}
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



    private String analyzequote(String expression){
        boolean found = false;
        for(int i=0; i< expression.length(); i++){
            if(expression.charAt(i) == '`') found = true;
        }
        if (found = true){
            String local = expression.substring(1);
            return local;
        } else {
            if(isNumeric(expression)){
                return expression;
            } else {
                if (Control.getVars().containsKey(expression)){
                    return Control.getVars().get(expression);
                } else {return expression;}
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

}
