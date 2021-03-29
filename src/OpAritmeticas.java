import java.util.*;

public class OpAritmeticas {

    //private final Control control = new Control();

    private List<Integer> vals = new ArrayList<>();


    public int Process(String expression) {
        ArrayList<String> exps = new ArrayList<>();
        //System.out.println(expression);

        while (true){
            if(expression.charAt(0) == ' '){
                expression = expression.substring(1);
            } else {
                break;
            }
        }
        while (true){
            if(expression.charAt(expression.length()-1) == ' '){
                expression = expression.substring(0,expression.length()-1);
            } else {
                break;
            }
        }

        expression=expression.substring(1,expression.length()-1);
        Stack<Integer> indexes = new Stack<>();
        int opening = 0;


        for(int x = 0; x< expression.length(); x++){
            if(expression.charAt(x) == '(' && opening == 0){
                opening++;
                indexes.add(x);
            } else if(expression.charAt(x) == '(' && opening != 0){
                opening++;
            } else if (expression.charAt(x) == ')' && opening == 1){
                opening--;
                indexes.add(x);
            } else if (expression.charAt(x) == ')' && opening != 1){
                opening--;
            }
        }

        Control control = new Control();
        Collections.reverse(exps);
        String paramsModified = "";
        ArrayList<String> par = new ArrayList<>();

        if (indexes.size() > 0){

            int initialIndex = indexes.size();

            paramsModified = expression.substring(indexes.peek()+1);
            String[] pf = paramsModified.split(" ");
            for(int i = pf.length-1; i>=0; i-- ){
                if(pf[i] != "" && pf[i] != " "){
                    par.add(pf[i]);
                }
            }



            for(int x = 0; x < initialIndex/2; x++){
                int end = indexes.peek();
                indexes.pop();
                int start = indexes.peek();
                indexes.pop();


                par.add(control.Process(expression.substring(start,end+1)));


                if(indexes.size() != 0){
                    paramsModified = expression.substring(indexes.peek()+1, start);
                    String[] p = paramsModified.split(" ");
                    for(int i = p.length-1; i>=0; i-- ){
                        if(p[i] != "" && p[i] != " "){
                            par.add(p[i]);
                        }
                    }
                } else {
                    paramsModified = expression.substring(0, start);
                    String[] p = paramsModified.split(" ");
                    for(int i = p.length-1; i>=0; i-- ){
                        if(p[i] != "" && p[i] != " "){
                            par.add(p[i]);
                        }
                    }
                }
            }
        } else {
            String[] p = expression.split(" ");
            for(int i = p.length-1; i>=0; i-- ){
                if(p[i] != "" && p[i] != " "){
                    par.add(p[i]);
                }
            }
        }

        Collections.reverse(par);

        int ans = analyze(par.get(1));

        for(int i = par.size()-1; i> 1; i--){
            switch (cleanString(par.get(0))) {
                case "+":
                    ans = ans + analyze(par.get(i));
                    break;
                case "-":
                    ans = ans - analyze(par.get(i));
                    break;
                case "*":
                    ans = ans * analyze(par.get(i));
                    break;
                case  "/":
                    ans = ans / analyze(par.get(i));
                    Math.round(ans);
                    break;
            }
        }

        return ans;

    }


    private int Calculate(String op, String ls, String rs){
        int l = analyze(clean(ls));
        int r = analyze(clean(rs));

        int ans = 0;
        switch (clean(op)) {
            case "+":
                ans = l + r;
                break;
            case "-":
                ans = l - r;
                break;
            case "*":
                ans = l * r;
                break;
            case  "/":
                ans = l / r;
                Math.round(ans);
                break;
        }

        return  ans;
    }

    private String clean(String value){
        String ans = "";

        ans.replace(" ", "");

        String[] splitedValue = value.split("");

        for (int i= 0; i< splitedValue.length; i++){
            if(splitedValue[i].equals(" ")){
                splitedValue[i] = "";
            }
        }
        ans = String.join("", splitedValue);
        return ans;
    }

    private String cleanString(String exp){
        return exp.replace(" ", "");
    }

    private int analyze(String value){
        try{
            return Integer.parseInt(clean(value));
        } catch (Exception e) {

            if(Variables.variables.containsKey(value)){

                return  Variables.variables.get(value);
            }

            if(Control.getUsing().containsKey(value)){
                return Integer.parseInt(Control.using.get(value));
            }

            else {return 0;}

        }

    }

    private String AF(String value){
        String respaldo = value;

        Control control = new Control();

        if(value.charAt(0) == ' ') value = value.substring(1);
        if(value.charAt(0) == '(') value = value.substring(1);
        if(value.charAt(value.length()-1) == ' ') value = value.substring(0, value.length()-1);
        if(value.charAt(value.length()-1) == ')') value = value.substring(0, value.length()-1);
        if(value.charAt(0) == ' ') value = value.substring(1);

        if(value.split(" ").length >= 2 && Function.funciones.containsKey(value.split(" ")[0])){
            return control.Process(respaldo);
        } else {return  respaldo;}
    }



}
