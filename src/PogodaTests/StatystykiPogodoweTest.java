package PogodaTests;

import Pogoda.Pogoda;
import Pogoda.StatystykiPogodowe;
import Pogoda.rodzajWielkosciFizycznej;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StatystykiPogodoweTest {
    private StatystykiPogodowe statystykiPogodowe = new StatystykiPogodowe();
    private List<Pogoda> listaOdczytow = new ArrayList<>();

    private List<Pogoda> dane(){
        Pogoda p1 = new Pogoda(Optional.of(21.0),Optional.of(54.0),Optional.of(987.0));
        Pogoda p2 = new Pogoda(Optional.of(19.0),Optional.of(0.0),Optional.of(1012.0));
        Pogoda p3 = new Pogoda(Optional.of(-21.0),Optional.of(22.0),Optional.of(1001.0));
        Pogoda p4 = new Pogoda(Optional.of(36.0),Optional.of(67.0),Optional.of(1011.9));
        listaOdczytow.add(p1);
        listaOdczytow.add(p2);
        listaOdczytow.add(p3);
        listaOdczytow.add(p4);
        return listaOdczytow;
    }

    @Test
    public void pobierzTemperatureNumerJeden(){
        Assert.assertEquals(Optional.of(21.0),statystykiPogodowe.pobierzWartosc(dane(), rodzajWielkosciFizycznej.TEMPERATURA,0));
    }
    @Test
    public void pobierzWilgotnoscNumerCztery(){
        Assert.assertEquals(Optional.of(67.0),statystykiPogodowe.pobierzWartosc(dane(), rodzajWielkosciFizycznej.WILGOTNOSC,3));
    }
    @Test
    public void pobierzCisnienieNumerTrzy(){
        Assert.assertEquals(Optional.of(1001.0),statystykiPogodowe.pobierzWartosc(dane(), rodzajWielkosciFizycznej.CISNIENIE,2));
    }

    @Test
    public void policzSredniaTemperature(){
        Assert.assertEquals(13.75,statystykiPogodowe.policzSrednia(Optional.of(21.0),dane(), rodzajWielkosciFizycznej.TEMPERATURA),0.001);
    }
    @Test
    public void policzSredniaWilgotnosc(){
        Assert.assertEquals(35.75,statystykiPogodowe.policzSrednia(Optional.of(54.0),dane(), rodzajWielkosciFizycznej.WILGOTNOSC),0.001);
    }
    @Test
    public void policzSrednieCisnienie(){
        Assert.assertEquals(1002.975,statystykiPogodowe.policzSrednia(Optional.of(987.0),dane(), rodzajWielkosciFizycznej.CISNIENIE),0.001);
    }
    @Test
    public void minMaxTemp(){
        Assert.assertEquals(-21.0,statystykiPogodowe.minMax(Optional.of(21.0),dane(), rodzajWielkosciFizycznej.TEMPERATURA)[0],0.001);
        Assert.assertEquals(36.0,statystykiPogodowe.minMax(Optional.of(21.0),dane(), rodzajWielkosciFizycznej.TEMPERATURA)[1],0.001);
    }

    @Test
    public void minMaxWilg(){
        Assert.assertEquals(0.0,statystykiPogodowe.minMax(Optional.of(54.0),dane(), rodzajWielkosciFizycznej.WILGOTNOSC)[0],0.001);
        Assert.assertEquals(67.0,statystykiPogodowe.minMax(Optional.of(54.0),dane(), rodzajWielkosciFizycznej.WILGOTNOSC)[1],0.001);
    }

    @Test
    public void minMaxCisn(){
        Assert.assertEquals(987.0,statystykiPogodowe.minMax(Optional.of(987.0),dane(), rodzajWielkosciFizycznej.CISNIENIE)[0],0.001);
        Assert.assertEquals(1012.0,statystykiPogodowe.minMax(Optional.of(987.0),dane(), rodzajWielkosciFizycznej.CISNIENIE)[1],0.001);
    }

    @Test
    public void minSredniaMaxT(){
        Assert.assertEquals(-21.0,statystykiPogodowe.minSredniaMaxWartosc(dane(), rodzajWielkosciFizycznej.TEMPERATURA).get(0).get(),0.001);
        Assert.assertEquals(13.75,statystykiPogodowe.minSredniaMaxWartosc(dane(), rodzajWielkosciFizycznej.TEMPERATURA).get(1).get(),0.001);
        Assert.assertEquals(36.0,statystykiPogodowe.minSredniaMaxWartosc(dane(), rodzajWielkosciFizycznej.TEMPERATURA).get(2).get(),0.001);
    }

    @Test
    public void statystykiTestTemp(){
        String s1 = "Srednia temperatura:  13,8 ℃";
        String s2 = "Minimalna temperatura:  -21,0 ℃";
        String s3 = "Maksymalna temperatura:  36,0 ℃";
        Assert.assertEquals(s1,statystykiPogodowe.statystykiPogodowe(dane(), rodzajWielkosciFizycznej.TEMPERATURA)[0]);
        Assert.assertEquals(s2,statystykiPogodowe.statystykiPogodowe(dane(), rodzajWielkosciFizycznej.TEMPERATURA)[1]);
        Assert.assertEquals(s3,statystykiPogodowe.statystykiPogodowe(dane(), rodzajWielkosciFizycznej.TEMPERATURA)[2]);
    }

    @Test
    public void statystykiTestWilg(){
        String s1 = "Srednia wilgotność:  35,8 %";
        String s2 = "Minimalna wilgotność:  0,0 %";
        String s3 = "Maksymalna wilgotność:  67,0 %";
        Assert.assertEquals(s1,statystykiPogodowe.statystykiPogodowe(dane(), rodzajWielkosciFizycznej.WILGOTNOSC)[0]);
        Assert.assertEquals(s2,statystykiPogodowe.statystykiPogodowe(dane(), rodzajWielkosciFizycznej.WILGOTNOSC)[1]);
        Assert.assertEquals(s3,statystykiPogodowe.statystykiPogodowe(dane(), rodzajWielkosciFizycznej.WILGOTNOSC)[2]);
    }

    @Test
    public void statystykiTestCisn(){
        String s1 = "Srednie ciśnienie:  1003,0 hPa";
        String s2 = "Minimalne ciśnienie:  987,0 hPa";
        String s3 = "Maksymalne ciśnienie:  1012,0 hPa";
        Assert.assertEquals(s1,statystykiPogodowe.statystykiPogodowe(dane(), rodzajWielkosciFizycznej.CISNIENIE)[0]);
        Assert.assertEquals(s2,statystykiPogodowe.statystykiPogodowe(dane(), rodzajWielkosciFizycznej.CISNIENIE)[1]);
        Assert.assertEquals(s3,statystykiPogodowe.statystykiPogodowe(dane(), rodzajWielkosciFizycznej.CISNIENIE)[2]);
    }
}
