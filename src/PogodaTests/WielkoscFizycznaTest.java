package PogodaTests;

import Pogoda.WielkoscFizyczna;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class WielkoscFizycznaTest {
    @Test
    void getWartoscBezNulla(){
        WielkoscFizyczna tester = new WielkoscFizyczna(Optional.of(2.1234));
        Assertions.assertEquals(Optional.of(2.1234),tester.getWartosc());
    }

    @Test
    void getWartoscNull(){
        WielkoscFizyczna tester = new WielkoscFizyczna();
        Assertions.assertEquals(Optional.empty(),tester.getWartosc());
    }

    @Test
    void toStringBezNulla(){
        WielkoscFizyczna tester = new WielkoscFizyczna(Optional.of(2.1234));
        Assertions.assertEquals("2,1",tester.toString());
    }

    @Test
    void toStringBezNulla1(){
        WielkoscFizyczna tester = new WielkoscFizyczna(Optional.of(5.0));
        Assertions.assertEquals("5,0",tester.toString());
    }

    @Test
    void toStringNull(){
        WielkoscFizyczna tester = new WielkoscFizyczna();
        Assertions.assertEquals("Wartość niedostępna",tester.toString());
    }
}
