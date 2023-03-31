package PogodaTests;
import Pogoda.Cisnienie;
import Pogoda.rodzajWielkosciFizycznej;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Optional;

class CisnienieTest {

    @Test
    void cisnienieToStringBezNulla(){
        Cisnienie tester = new Cisnienie(Optional.of(976.845));
        Assertions.assertEquals("976,8 hPa",tester.toString());
    }

    @Test
    void cisnienieToStringBezNulla1(){
        Cisnienie tester = new Cisnienie(Optional.of(976.854));
        Assertions.assertEquals("976,9 hPa",tester.toString());
    }
    @Test
    void cisnienieToStringNull(){
        Cisnienie tester = new Cisnienie();
        Assertions.assertEquals("Wartość niedostępna",tester.toString());
    }

    @Test
    void cisnienieRodzajWielkosciFizycznejNull(){
        Cisnienie tester = new Cisnienie();
        Assertions.assertEquals(rodzajWielkosciFizycznej.CISNIENIE,tester.getRodzaj());
    }

    @Test
    void cisnienieRodzajWielkosciFizycznejBezNulla(){
        Cisnienie tester = new Cisnienie(Optional.of(1000.25));
        Assertions.assertEquals(rodzajWielkosciFizycznej.CISNIENIE,tester.getRodzaj());
    }

}
