package PogodaTests;
import Pogoda.Temperatura;
import Pogoda.rodzajWielkosciFizycznej;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Optional;

class TemperaturaTest {

    @Test
    void temperaturaToStringBezNulla(){
        Temperatura tester = new Temperatura(Optional.of(-21.34));
        Assertions.assertEquals("-21,3 ℃",tester.toString());
    }

    @Test
    void temperaturaToStringBezNulla1(){
        Temperatura tester = new Temperatura(Optional.of(-59.99999));
        Assertions.assertEquals("-60,0 ℃",tester.toString());
    }
    @Test
    void temperaturaToStringNull(){
        Temperatura tester = new Temperatura();
        Assertions.assertEquals("Wartość niedostępna",tester.toString());
    }

    @Test
    void temperaturaRodzajWielkosciFizycznejNull(){
        Temperatura tester = new Temperatura();
        Assertions.assertEquals(rodzajWielkosciFizycznej.TEMPERATURA,tester.getRodzaj());
    }

    @Test
    void temperaturaRodzajWielkosciFizycznejBezNulla(){
        Temperatura tester = new Temperatura(Optional.of(-1.67));
        Assertions.assertEquals(rodzajWielkosciFizycznej.TEMPERATURA,tester.getRodzaj());
    }

}
