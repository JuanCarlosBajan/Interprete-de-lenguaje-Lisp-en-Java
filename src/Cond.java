import java.util.ArrayList;

public class Cond {

    public String Process(String expression){

        ArrayList<String> values = new ArrayList<>();

        String res = "";



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

        expression = expression.substring(6,expression.length()-1);

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

        Control control = new Control();



        // NO NECESARIAMENTE LAS DOS ACCIONES ESTAN ENCERRADAS EN PARENTESIS ENTONCES TENES QUE VER ESO

        if(values.size() > 2){
            if (control.Process(values.get(0)) == "t"){
                res =control.Process(values.get(1));
            } else {
                res = control.Process(values.get(2));
            }
        } else if(values.size() == 2){

        if(expression.substring(firslasttind +1 ,inInd).length() >= 2){
            if (control.Process(values.get(0)) == "t"){
                res =control.Process(clean(expression.substring(firslasttind +1 ,inInd)));
            } else {
                res = control.Process(values.get(1));
            }
        } else if (expression.substring(llastind).length() >=2){
            if (control.Process(values.get(0)) == "nil"){
                res =control.Process(clean(expression.substring(firslasttind +1 ,inInd)));
            } else {
                res = control.Process(values.get(1));
            }
        } else {
            res = "ERROR";
        }

        } else if(values.size() == 1){
            String local = expression.substring(firslasttind+1);
            if (local.charAt(0) == ' '){local = local.substring(1);}
            if (control.Process(values.get(0)) == "t"){
                res =control.Process(clean(local.split(" ")[0]));
            } else {
                res = control.Process(control.Process(clean(local.split(" ")[1])));
            }

        }
        return  res;

    }

    private String clean(String value){
        return value.replace(" ","");
    }

}
