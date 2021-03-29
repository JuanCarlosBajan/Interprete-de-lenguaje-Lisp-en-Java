import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Cond {

    public String Process(String expression){

        ArrayList<String> tests = new ArrayList<>();
        String res = "";

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

        int initialSize = indexes.size();

        for (int x = 0; x< initialSize/2; x++){
            int end = indexes.peek();
            indexes.pop();
            int start = indexes.peek();
            indexes.pop();
            tests.add(expression.substring(start,end+1));
        }

        Collections.reverse(tests);


        for(String s: tests){
            res = SubProcess(s);
            if (res != "") {
                break;
            }
        }

        return  res;

    }


    private String SubProcess(String expression){

        String ans = "";
        Control control = new Control();
        ArrayList<String> values = new ArrayList<>();
        expression = expression.substring(1,expression.length()-1);
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

        if(expression.length() - indexes.peek() > 1){
            values.add(expression.substring(indexes.peek()+1));
            expression = expression.substring(0,indexes.peek()+1);
        }

        int initialSize = indexes.size();

        for (int x = 0; x < initialSize/2; x++){
            int end = indexes.peek();
            indexes.pop();
            int start = indexes.peek();
            indexes.pop();


            values.add(expression.substring(start,end+1));

            if(indexes.size() > 1 && start - indexes.peek() > 2){
                values.add(expression.substring(indexes.peek()+1,start));
            }

            if (indexes.size() == 0 && start > 1){
                values.add(expression.substring(0, start));
            }
        }

        Collections.reverse(values);


        if(!clean(values.get(0)).equals("t")){
            if(control.Process(values.get(0)) == "t"){
                ans = control.Process(values.get(1));
            }
        } else if (clean(values.get(0)).equals("t")) {
            ans = control.Process(values.get(1));
        }
        return ans;

    }

    private String clean(String value){
        return value.replace(" ","");
    }

}
