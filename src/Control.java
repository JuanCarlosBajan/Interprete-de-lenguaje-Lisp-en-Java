import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Control {

    public static HashMap<String, String> vars = new HashMap<>();
    private List<String> operators = Arrays.asList(new String[]{"+", "-", "*", "/"});
    private List<String> instructions = Arrays.asList(new String[]{"defun", "setq", "if"});

    public void Process(String expression) {

        List<String> values = new ArrayList<>();

        String[] temp = expression.split("\\(|\\)");

        for (int s = 0; s < temp.length; s++)
        {if(temp[s].length() > 0)
        {values.add(temp[s]);}
        }

        boolean analyzing = false;

        for (int x = 0; x < values.size(); x++){

            String actual = values.get(x);

            String replacedValues = actual.replace(" ","");

            if(replacedValues.length() == 1 && operators.contains(replacedValues) && !analyzing){

                analyzing = true;

                List<String> subvalues = new ArrayList<>();

                for(int i = 1; i < values.size(); i++){
                    subvalues.add(values.get(i));
                }

                OpAritmeticas aritmeticas = new OpAritmeticas();

                int ans = aritmeticas.Operate(subvalues, replacedValues);

                System.out.println(ans);



            }

            if(replacedValues.length() > 1 && !analyzing){

                List<String> pivot = Arrays.asList(actual.split(" "));

                int somethingIndex = 0;
                boolean pased = false;

                for(int i = 0; i< pivot.size(); i++){

                    if(pivot.get(i).length() > 1){
                        pased = true;
                    }
                    if (pivot.get(i).equals("") && pased == false){
                        somethingIndex++;
                    }
                }

                if (operators.contains(pivot.get(somethingIndex))){

                    String result = actual.substring(2, actual.length());
                    System.out.println(actual + " +*/- 2");

                    analyzing = true;

                }

                if (instructions.contains(pivot.get(somethingIndex))){

                    System.out.println(actual + "INSTRUCTION");

                    analyzing = true;

                }
            }
        }
    }

    public void Process(List<String> values){

        for (int x = 0; x < values.size(); x++){

            if (operators.contains(values.get(x))) {
                System.out.println(values.get(x));
            } if (values.get(x).contains("defun")){
                System.out.println("FUNCION");
            }
        }

    }


    public static HashMap<String, String> getVars(){
        return  vars;
    }

}
