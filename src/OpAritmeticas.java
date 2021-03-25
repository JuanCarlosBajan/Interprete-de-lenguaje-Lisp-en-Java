import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OpAritmeticas {

    //private final Control control = new Control();

    private List<Integer> vals = new ArrayList<>();


    public int Process(String expression) {

        int ans = 0;

        int CountBeforeFirstParenthesis = -1;
        int CountAfterLastParenthesis = -1;
        int FirstIndex = -1;
        int ParenthesisCount = 0;
        int ClosedExpression = 0;

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



        int localIndex = 0;

        for(String chr: expression.split("")){
            if (ClosedExpression == 0 && ParenthesisCount > 0){
                CountAfterLastParenthesis = localIndex;
                break;
            }
            if(chr.equals("(")){
                FirstIndex = (FirstIndex == -1)? localIndex:FirstIndex;
                ParenthesisCount ++;
                ClosedExpression ++;
                if(CountBeforeFirstParenthesis == -1){
                    CountBeforeFirstParenthesis = localIndex;
                }

            }
            if (chr.equals(")")){
                ParenthesisCount++;
                ClosedExpression --;
            }
            localIndex++;
        }


        String left = "";
        String right = "";

        try {
            // Esto solo debe darse si hay dos opening parenthesis
            left = AF(expression.substring(FirstIndex,localIndex));
            right = AF(expression.substring(localIndex));
            if(right.length()<2) {
                right = left;
                left = expression.substring(2,FirstIndex);
            }
            ans = Calculate(expression.substring(0,2),String.valueOf(Process(left)),String.valueOf(Process(right)));

        } catch (StringIndexOutOfBoundsException e){
            if(CountBeforeFirstParenthesis > 2){
                left = expression.substring(2,CountBeforeFirstParenthesis);
                right = AF(expression.substring(CountBeforeFirstParenthesis));
                ans = Calculate(expression.substring(0,2),String.valueOf(Process(left)),String.valueOf(Process(right)));
            }
            if(CountAfterLastParenthesis > 2){
                left = AF(expression.substring(2,CountAfterLastParenthesis));
                right = expression.substring(CountAfterLastParenthesis);
                ans = Calculate(expression.substring(0,2),String.valueOf(Process(left)),String.valueOf(Process(right)));
            }
            if (CountBeforeFirstParenthesis == -1){
                if(expression.split(" ").length > 2) {
                    String[] SplitedExpression = expression.split(" ");
                    ans = Calculate(SplitedExpression[0],SplitedExpression[1],SplitedExpression[2]);
                } else {
                    String[] SplitedExpression = expression.split(" ");
                    for(String s: SplitedExpression){
                        try {
                            ans = Integer.parseInt(s);
                        } catch (Exception er) {

                        }
                    }
                }
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
