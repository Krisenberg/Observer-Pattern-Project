package PogodaTests;

import Pogoda.Pogoda;
import Pogoda.Temperatura;
import Pogoda.Wilgotnosc;
import Pogoda.Cisnienie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class PogodaTest {

    @Test
    void getTemperaturaNull(){
        Pogoda tester = new Pogoda();
        Assertions.assertEquals(new Temperatura(), tester.getTemperatura());
    }

    @Test
    void getTemperaturaBezNulla(){
        Pogoda tester = new Pogoda(Optional.of(4.5),Optional.ofNullable(null), Optional.ofNullable(null));
        Assertions.assertEquals(new Temperatura(Optional.of(4.5)), tester.getTemperatura());
    }

    @Test
    void getWilgotnoscNull(){
        Pogoda tester = new Pogoda();
        Assertions.assertEquals(new Wilgotnosc(), tester.getWilgotnosc());
    }

    @Test
    void getWilgotnoscBezNulla(){
        Pogoda tester = new Pogoda(Optional.of(4.5),Optional.of(2.4567), Optional.ofNullable(null));
        Assertions.assertEquals(new Wilgotnosc(Optional.of(2.4567)), tester.getWilgotnosc());
    }

    @Test
    void getWilgotnoscBezNulla1(){
        Pogoda tester = new Pogoda(Optional.of(4.5),Optional.of(2.4567), Optional.ofNullable(null));
        Assertions.assertNotEquals(new Wilgotnosc(Optional.of(2.46)), tester.getWilgotnosc());
    }

    @Test
    void getCisnienieNull(){
        Pogoda tester = new Pogoda();
        Assertions.assertEquals(new Cisnienie(), tester.getCisnienie());
    }

    @Test
    void getCisnienieBezNulla(){
        Pogoda tester = new Pogoda(Optional.ofNullable(null),Optional.of(2.4567), Optional.ofNullable(1.34));
        Assertions.assertEquals(new Cisnienie(Optional.of(1.34)), tester.getCisnienie());
    }

    @Test
    void pogodaToStringNulle(){
        Pogoda tester = new Pogoda();
        String s = "Temperatura:  Wartość niedostępna" + "\n";
        s = s + "Wilgotność:  Wartość niedostępna" + "\n";
        s = s + "Ciśnienie:  Wartość niedostępna";
        Assertions.assertEquals(s,tester.toString());
    }

    @Test
    void pogodaToStringWartosci(){
        Pogoda tester = new Pogoda(Optional.of(3.124),Optional.of(-1.237),Optional.of(999.95));
        String s = "Temperatura:  3,1 ℃" + "\n";
        s = s + "Wilgotność:  -1,2 %" + "\n";
        s = s + "Ciśnienie:  1000,0 hPa";
        Assertions.assertEquals(s,tester.toString());
    }

    @Test
    void pogodaEqualsNull(){
        Pogoda tester = new Pogoda();
        Assertions.assertEquals(new Pogoda(), tester);
    }

    @Test
    void pogodaEqualsNotNull(){
        Pogoda tester = new Pogoda(Optional.of(3.124),Optional.of(-1.237),Optional.of(999.95));
        Assertions.assertEquals(new Pogoda(Optional.of(3.124),Optional.of(-1.237),Optional.of(999.95)), tester);
    }

    @Test
    void pogodaNotEqualsNotNull(){
        Pogoda tester = new Pogoda(Optional.of(3.124),Optional.of(-1.237),Optional.of(999.95));
        Assertions.assertNotEquals(new Pogoda(Optional.of(3.123),Optional.of(-1.237),Optional.of(999.95)), tester);
    }



}
