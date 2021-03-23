import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Function {

    String name;
    String body;
    HashMap<String,String> params = new HashMap<>();
    //todas las funciones
    HashMap<String,String> funciones = new HashMap<>();

    boolean Ope = false;

    public Function(String expression) {

        while(true){
            if(expression.charAt(0) == '('){
                break;
            }else {expression = expression.substring(1);}
        }

        while (true) {
            if(expression.charAt(expression.length()-1) == ')'){
                break;
            } else { expression = expression.substring(0,expression.length()-1);}
        }

        int opnP = -1;
        int clsP = -1;
        int indx = 0;

        expression = expression.substring(1,expression.length()-1);

        for(int i = 0; i < expression.length(); i++){
            if(opnP == -1 && expression.charAt(i) == '('){opnP = indx;}
            if(clsP == -1 && expression.charAt(i) == ')'){clsP = indx;}
            indx++;
        }

        String l = expression.substring(0,opnP);
        String c = expression.substring(opnP+1,clsP);
        String r = expression.substring(clsP+1);

        name = l.split(" ")[1];

        String[] temp = c.split(" ");

        for(String s:temp){
            params.put(s,"");
        }

        body = r;

        System.out.println(name);
        for(String s: params.keySet()){
            System.out.println(s);
        }
        System.out.println(body);


    }

    public String Process(String body){

        int countA = 0;
        int contC = 0;

        String[] parts = body.split("\\(");
        String[] parts2 = body.split("\\)");
        System.out.println(Arrays.toString(parts2));

        Set<Character> operadores = new HashSet<Character>();
        operadores.add('+');
        operadores.add('-');
        operadores.add('*');
        operadores.add('/');

        for(int i = 0; i < parts.length;i++)
        {

            if (numerico(parts[i]))
                System.out.println(parts[i] + " es un numero");
            else
                System.out.println(parts[i] + " NO es un numero");

        }
        if(Ope)
        {
            OpAritmeticas op = new OpAritmeticas();
            System.out.println(op.Process(body));
        }

        return "";
    }

    private static boolean numerico(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }


}
