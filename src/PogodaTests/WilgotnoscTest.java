package PogodaTests;

//import org.junit.Test;
import Pogoda.Wilgotnosc;
import Pogoda.rodzajWielkosciFizycznej;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//import static org.junit.assertEquals;
import java.util.Optional;

import static org.mockito.Mockito.mock;

class WilgotnoscTest {

    @Test
    void wilgotnoscToStringBezNulla(){
        Wilgotnosc tester = new Wilgotnosc(Optional.of(0.0));
        Assertions.assertEquals("0,0 %",tester.toString());
    }

    @Test
    void wilgotnoscToStringBezNulla1(){
        Wilgotnosc tester = new Wilgotnosc(Optional.of(59.99999));
        Assertions.assertEquals("60,0 %",tester.toString());
    }
    @Test
    void wilgotnoscToStringNull(){
        Wilgotnosc tester = new Wilgotnosc();
        Assertions.assertEquals("Wartość niedostępna",tester.toString());
    }

    @Test
    void wilgotnoscRodzajWielkosciFizycznejNull(){
        Wilgotnosc tester = new Wilgotnosc();
        Assertions.assertEquals(rodzajWielkosciFizycznej.WILGOTNOSC,tester.getRodzaj());
    }

    @Test
    void wilgotnoscRodzajWielkosciFizycznejBezNulla(){
        Wilgotnosc tester = new Wilgotnosc(Optional.of(0.01));
        Assertions.assertEquals(rodzajWielkosciFizycznej.WILGOTNOSC,tester.getRodzaj());
    }

}