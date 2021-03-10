import java.util.ArrayList;
import java.util.List;

public class OpAritmeticas {

    Control control = new Control();

    String[] values = new String[]{""};


    public int Operate(List<String> expression, String operator) {

        for(int x = 0; x< expression.size(); x++){
            if(expression.get(x).equals("") || expression.get(x).equals(" ")){
                expression.remove(x);
            }
        }

        List<String> values = new ArrayList<>();

        String[] temp = expression.get(0).split("\\(|\\)");

        return 502;

    }

    private boolean isNumeric(String value) {

        boolean numeric = true;

        try {
            Double num = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            numeric = false;
        }

        return  numeric;
    }

    private String[] Cut(String[] exp, int start) {
        String[] resp = new String[]{""};
        if (exp.length > 1){
            int index = 0;
            for(int x = start; x < exp.length; x++){
                resp[index] = exp[x];
                index++;
            }
        } else {
            String[] pivot = exp[start].split(" ");
            for(int i =0; i< pivot.length-1; i++){
                resp[i] = pivot[i+1];

            }


        }


        return resp;
    }



}
