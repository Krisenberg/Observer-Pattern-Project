package PogodaTests;

import Pogoda.Pogoda;
import Pogoda.StacjaMeteo;
import Pogoda.Wilgotnosc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;

public class StacjaMeteoTest {

    @Test
    void equalsStacjeMeteoTaSamaStacja(){
        StacjaMeteo tester = new StacjaMeteo("Wrocław",true,false,true);
        Assertions.assertEquals(tester,tester);
    }

    @Test
    void equalsStacjeMeteoNowaStacja(){
        StacjaMeteo tester = new StacjaMeteo("Wrocław",true,false,true);
        Assertions.assertEquals(new StacjaMeteo("Wrocław",true,false,true),tester);
    }

    @Test
    void equalsStacjeMeteoPogoda(){
        StacjaMeteo tester = new StacjaMeteo("Wrocław",true,false,true);
        Assertions.assertEquals(new StacjaMeteo("Wrocław",true,false,true),tester);
    }

    @Test
    void notEqualsStacjeMeteoNull(){
        StacjaMeteo tester = new StacjaMeteo("Wrocław",true,false,true);
        Assertions.assertNotEquals(null, tester);
    }

    @Test
    void notEqualsStacjeMeteoInnyObiekt(){
        StacjaMeteo tester = new StacjaMeteo("Wrocław",true,false,true);
        Assertions.assertNotEquals(new Wilgotnosc(),tester);
    }

    @Test
    void notEqualsStacjeMeteoInnyObiekt1(){
        StacjaMeteo tester = new StacjaMeteo("Wrocław",true,false,true);
        Assertions.assertEquals(false,tester.equals(new Wilgotnosc()));
    }

    @Test
    void StacjaMeteoToStringBezNulli(){
        StacjaMeteo tester = new StacjaMeteo("Wrocław", true, true, true);
        String s = "Wrocław TWC (temperatura/wilgotnosc/cisnienie)";
        Assertions.assertEquals(s,tester.toString());
    }

    @Test
    void StacjaMeteoToStringJedenNullWilgotnosc(){
        StacjaMeteo tester = new StacjaMeteo("Wrocław", true, false, true);
        String s = "Wrocław TC (temperatura/cisnienie)";
        Assertions.assertEquals(s,tester.toString());
    }

    @Test
    void StacjeMeteoToStringDwaNulle(){
        StacjaMeteo tester = new StacjaMeteo("Jelenia Góra", false, true, false);
        String s = "Jelenia Góra W (wilgotnosc)";
        Assertions.assertEquals(s,tester.toString());
    }

    @Test
    void wyswietlStacjeMeteoBezNulli(){
        StacjaMeteo tester = new StacjaMeteo("Wrocław", true, true, true);
        String s = "Lokalizacja stacji meteo: Wrocław. Dostepne czujniki: temperatura/wilgotnosc/cisnienie";
        Assertions.assertEquals(s,tester.wyswietlStacjeMeteo());
    }

    @Test
    void wyswietlStacjeMeteoNulle(){
        StacjaMeteo tester = new StacjaMeteo("Jelenia Góra", false, true, false);
        String s = "Lokalizacja stacji meteo: Jelenia Góra. Dostepne czujniki: wilgotnosc";
        Assertions.assertEquals(s,tester.wyswietlStacjeMeteo());
    }

    @Test
    void nowaStacja(){
        Pogoda pogoda = new Pogoda(Optional.of(1.1),Optional.of(3.4),Optional.of(99.9));
        StacjaMeteo tester = new StacjaMeteo("Warszawa",pogoda);
        StacjaMeteo tester2 = new StacjaMeteo("Warszawa",new Pogoda(Optional.of(1.1),Optional.of(3.4),Optional.of(99.9)));
        Assertions.assertEquals(true,tester.equals(tester2));
    }

    @Test
    void getPogoda(){
        Pogoda pogoda = new Pogoda();
        StacjaMeteo tester = new StacjaMeteo("Jelenia Góra", pogoda);
        Assertions.assertEquals(true,tester.getPogoda().equals(pogoda));
    }
}
