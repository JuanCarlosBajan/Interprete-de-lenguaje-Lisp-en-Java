import java.util.*;

public class Function {

    String name;
    String body;
    public static LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
    //todas las funciones
    public static LinkedHashMap<String, LinkedHashMap<String, String>> funciones = new LinkedHashMap<>();
    public static LinkedHashMap<String, String> funcionesB = new LinkedHashMap<>();

    boolean Ope = false;

    public Function(String expression) {

        while (true) {
            if (expression.charAt(0) == '(') {
                break;
            } else {
                expression = expression.substring(1);
            }
        }

        while (true) {
            if (expression.charAt(expression.length() - 1) == ')') {
                break;
            } else {
                expression = expression.substring(0, expression.length() - 1);
            }
        }

        int opnP = -1;
        int clsP = -1;
        int indx = 0;

        expression = expression.substring(1, expression.length() - 1);

        for (int i = 0; i < expression.length(); i++) {
            if (opnP == -1 && expression.charAt(i) == '(') {
                opnP = indx;
            }
            if (clsP == -1 && expression.charAt(i) == ')') {
                clsP = indx;
            }
            indx++;
        }

        String l = expression.substring(0, opnP);
        String c = expression.substring(opnP + 1, clsP);
        String r = expression.substring(clsP + 1);

        name = l.split(" ")[1];

        String[] temp = c.split(" ");

        for (String s : temp) {
            params.put(s, "");
        }

        body = r;

        //probando con prints
        /*System.out.println(name);
        for (String s : params.keySet()) {
            System.out.println(s);
        }
        System.out.println(body);*/

        funciones.put(name, params);
        funcionesB.put(name, body);

    }

    // asignando los parametros con su respetiva funcion
    public void asignarP(String nombre) {

        String key = nombre;
        String parametro;
        Scanner scan = new Scanner(System.in);

        //if(funciones.isEmpty() || !funciones.containsKey(key))
        //{
            LinkedHashMap<String, String> inner = new LinkedHashMap<String, String>();
            System.out.println("Porfavor ingrese los valores que tendra cada parametro de la respectiva fncion(se le pedira uno por uno)");
            for(int i = 0; i < params.keySet().toArray().length; i++)
            {
                if(nombre.equals(funciones.keySet().toArray()[i]))
                {
                    System.out.println("Parametro: " + params.keySet().toArray()[i]);
                    System.out.println("Valor:");
                    parametro = scan.nextLine();
                    inner.put(params.keySet().toArray()[i].toString(),parametro);
                }

            }
            funciones.put(key, inner);

            funcionesB.put(key, body);
        //}

        //muestra las llaves
        for (String s : funciones.keySet()) {
            System.out.println(s);
        }
        // muestra los valores
        for (HashMap<String, String> i : funciones.values()) {
            System.out.println(i);

        }


    }

    public String devolverB(String nombre)
    {
        String cuerpo = "";

        for (String s : funciones.keySet()) {
            if(nombre.equals(Function.funciones.keySet().toArray()[s]))
            {
                for (HashMap<String, String> i : funciones.values()) {
                    System.out.println(i);
                    return cuerpo;
                }
            }
        }


    }


}
