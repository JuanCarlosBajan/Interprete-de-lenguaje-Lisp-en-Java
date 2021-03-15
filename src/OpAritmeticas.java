import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OpAritmeticas {

    private final List<String> operators = Arrays.asList("+", "-", "*", "/");
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
            left = expression.substring(FirstIndex,localIndex);
            right = expression.substring(localIndex);
            if(right.length()<2) {
                right = left;
                left = expression.substring(2,FirstIndex);
            }
            ans = Calculate(expression.substring(0,2),String.valueOf(Process(left)),String.valueOf(Process(right)));

        } catch (StringIndexOutOfBoundsException e){
            if(CountBeforeFirstParenthesis > 2){
                left = expression.substring(2,CountBeforeFirstParenthesis);
                right = expression.substring(CountBeforeFirstParenthesis);
                ans = Calculate(expression.substring(0,2),String.valueOf(Process(left)),String.valueOf(Process(right)));
            }
            if(CountAfterLastParenthesis > 2){
                left = expression.substring(2,CountAfterLastParenthesis);
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


    private int Calculate(String op, String l, String r){
        int ans = 0;
        switch (clean(op)) {
            case "+":
                ans = Integer.parseInt(clean(l)) + Integer.parseInt(clean(r));
                break;
            case "-":
                ans = Integer.parseInt(clean(l)) - Integer.parseInt(clean(r));
                break;
            case "*":
                ans = Integer.parseInt(clean(l)) * Integer.parseInt(clean(r));
                break;
            case  "/":
                ans = Integer.parseInt(clean(l)) / Integer.parseInt(clean(r));
                Math.round(ans);
                break;
        }

        return  ans;
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
