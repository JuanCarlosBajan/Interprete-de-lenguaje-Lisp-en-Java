import org.junit.jupiter.api.Test;

class PredicadosTest {
    Predicados predicados=new Predicados();

    @Test
    public void Operate(){
        System.out.println(predicados.Process("(equal 123 123)"));
    }
}