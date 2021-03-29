import javax.swing.table.TableRowSorter;
import java.util.*;

public class Function {

    String name;
    String body;
    public LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
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
        String r = expression.substring(clsP + 2);

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

    public Function() {

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

    public void asignarP(String nombre, String params){

        Control control = new Control();
        ArrayList<String> par = new ArrayList<>();
        Stack<Integer> indexes = new Stack<>();
        int opening = 0;

        for(int x = 0; x< params.length(); x++){
            if(params.charAt(x) == '(' && opening == 0){
                opening++;
                indexes.add(x);
            } else if(params.charAt(x) == '(' && opening != 0){
                opening++;
            } else if (params.charAt(x) == ')' && opening == 1){
                opening--;
                indexes.add(x);
            } else if (params.charAt(x) == ')' && opening != 1){
                opening--;
            }
        }

        String paramsModified = "";

        if (params.charAt(0) == ' '){
            params = params.substring(1);
        }

        if (indexes.size() > 0) {
            int initialIndex = indexes.size();
            paramsModified = params.substring(indexes.peek()+1);
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


                par.add(control.Process(params.substring(start,end+1)));


                if(indexes.size() != 0){
                    paramsModified = params.substring(indexes.peek()+1, start);
                    String[] p = paramsModified.split(" ");
                    for(int i = p.length-1; i>=0; i-- ){
                        if(p[i] != "" && p[i] != " "){
                            par.add(p[i]);
                        }
                    }
                } else {
                    paramsModified = params.substring(0, start);
                    String[] p = paramsModified.split(" ");
                    for(int i = p.length-1; i>=0; i-- ){
                        if(p[i] != "" && p[i] != " "){
                            par.add(p[i]);
                        }
                    }
                }
            }
        } else {
            String[] p = params.split(" ");
            for(int i = p.length-1; i>=0; i-- ){
                if(p[i] != "" && p[i] != " "){
                    par.add(p[i]);
                }
            }
        }

        Collections.reverse(par);

        for(String s: par){
            //System.out.println(s);
        }


        for(int i = 0; i<funciones.get(nombre).keySet().size(); i++){
            funciones.get(nombre).put((String) (funciones.get(nombre).keySet().toArray())[i],par.get(i)) ;
        }
        for(int i = 0; i<funciones.get(nombre).keySet().size(); i++){
            //System.out.println((funciones.get(nombre).keySet().toArray())[i] + ", " + (funciones.get(nombre).values().toArray())[i]);
        }



    }

    public String devolverB(String nombre)
    {
        String cuerpo = "";
        boolean found = false;

        for (String s : funciones.keySet()) {
            if(s.equals(nombre))
            {
                found = true;
                cuerpo = funcionesB.get(nombre);
            }
        }

        if (found == false){
            return "Not found";
        } else {
            return cuerpo;
        }

    }

    public String Process(String n, String par){

        Control c = new Control();
        String ans = "ERROR";
        LinkedHashMap<String, String> usingBefore = new LinkedHashMap<>();

        if(funciones.containsKey(n)){
            asignarP(n, par);
            for(int i = 0; i<funciones.get(n).keySet().size(); i++){
                String currentvar = (String) (funciones.get(n).keySet().toArray())[i];
                if(Control.using.containsKey(currentvar)) {
                    usingBefore.put(currentvar, Control.using.get(currentvar));
                    Control.using.replace(currentvar, (String) (funciones.get(n).values().toArray())[i]);
                } else {
                    Control.using.put(currentvar, (String) (funciones.get(n).values().toArray())[i]);
                }
            }
            ans = c.Process(funcionesB.get(n));
        }

        if(!usingBefore.isEmpty()){
            for(String s: usingBefore.keySet()){
                Control.using.put(s, usingBefore.get(s));
            }
        }

        for(String s: funciones.get(n).keySet()){
            if(!usingBefore.keySet().contains(s)){
                    Control.using.remove(s);
            }
        }

        for(String s: Control.using.keySet()){
            //System.out.println(s+ ", " + Control.using.get(s));
        }


        return ans;
    }


}
