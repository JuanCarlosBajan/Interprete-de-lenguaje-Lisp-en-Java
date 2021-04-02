import java.util.Scanner;

/*
* Clase main del interprete Lisp
*
* @author Juan Carlos Bajan
* @author Jessica Pamela Ortiz
* @author Jose Mariano Reyes
*
* */

public class Main {
    /*
    * @param args Parametro de la clase main
    * */
    public static void main(String[] args){

        Control control = new Control();
        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenido a un interprete de LISP\n Por favor ingrese el codigo que desea procesar\n(ingrese 'SALIR', para salir):");

        boolean run = true;
        while (run){
            String ans = sc.nextLine();
            if(ans.toLowerCase().equals("salir")){
                System.out.println("Saliendo...");
                run = false;
            }
            if (Analyze(ans) && run){
                System.out.println(control.Process(ans));
            }

        }
    }

    private static boolean Analyze(String expression){
        Boolean ans = false;

        int opening = 0;

        for(int x = 0; x < expression.length(); x++){
            if(expression.charAt(x) == '('){
                opening++;
            } else if (expression.charAt(x) == ')'){
                opening--;
            }
        }

        if (opening == 0){
            ans = true;
        }

        String[] verif = expression.split(" ");

        for (String s: verif){
            if(s.equals("")){
                ans = false;
            }
        }

        if (!ans){
            System.out.println("\nSe ha encontrado un problema de escritura en su codigo.");
        }

        return ans;
    }
}
