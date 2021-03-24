import org.junit.jupiter.api.Test;

class PredicadosTest {
    Predicados predicados=new Predicados();
    Variables variables=new Variables();
    @Test
    public void Operate(){

        variables.Process("(setq x 5)");
        System.out.println(predicados.Process("(atom x)"));
    }
}