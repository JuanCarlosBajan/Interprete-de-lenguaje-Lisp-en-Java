public class Function {

    String name;

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

        System.out.println(l + ",  " + c + ",  " + r);


    }


}
