package PogodaTests;

import Pogoda.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;

public class OdczytyPogodoweTest2 {
    private OdczytyPogodowe odczytyPogodowe = new OdczytyPogodowe();
    @Test
    public void nowaTemperaturaSrodekPrzedzialu() {
        Temperatura temperatura = new Temperatura(Optional.of(20.2));
        Assert.assertTrue(odczytyPogodowe.nowaWartosc(temperatura).getWartosc().isPresent()
                        && odczytyPogodowe.nowaWartosc(temperatura).getWartosc().get()>=-25
                        && odczytyPogodowe.nowaWartosc(temperatura).getWartosc().get()<=36);

    }

    @Test
    public void nowaTemperaturaDolnaWartosc() {
        Temperatura temperatura = new Temperatura(Optional.of(-25.0));
        Assert.assertTrue(odczytyPogodowe.nowaWartosc(temperatura).getWartosc().isPresent()
                && odczytyPogodowe.nowaWartosc(temperatura).getWartosc().get()>=-25
                && odczytyPogodowe.nowaWartosc(temperatura).getWartosc().get()<=36);

    }

    @Test
    public void nowaTemperaturaNull() {
        Temperatura temperatura = new Temperatura(Optional.ofNullable(null));
        Assert.assertEquals(new Temperatura(),odczytyPogodowe.nowaWartosc(temperatura));

    }

    @Test
    public void nowaWilgotnoscSrodekPrzedzialu() {
        Wilgotnosc wilgotnosc = new Wilgotnosc(Optional.of(58.7));
        Assert.assertTrue(odczytyPogodowe.nowaWartosc(wilgotnosc).getWartosc().isPresent()
                && odczytyPogodowe.nowaWartosc(wilgotnosc).getWartosc().get()>=0
                && odczytyPogodowe.nowaWartosc(wilgotnosc).getWartosc().get()<=100);

    }

    @Test
    public void nowaWilgotnoscNull() {
        Wilgotnosc wilgotnosc = new Wilgotnosc(Optional.ofNullable(null));
        Assert.assertEquals(new Wilgotnosc(),odczytyPogodowe.nowaWartosc(wilgotnosc));

    }

    @Test
    public void noweCisnienieSrodekPrzedzialu() {
        Cisnienie cisnienie = new Cisnienie(Optional.of(998.0));
        Assert.assertTrue(odczytyPogodowe.nowaWartosc(cisnienie).getWartosc().isPresent()
                && odczytyPogodowe.nowaWartosc(cisnienie).getWartosc().get()>=985
                && odczytyPogodowe.nowaWartosc(cisnienie).getWartosc().get()<=1025);

    }

    @Test
    public void noweCisnienieNull() {
        Cisnienie cisnienie = new Cisnienie(Optional.ofNullable(null));
        Assert.assertEquals(new Cisnienie(),odczytyPogodowe.nowaWartosc(cisnienie));

    }

    @Test
    public void nowaPogodaWszystkieWartosciDostepne(){
        StacjaMeteo stacjaMeteo = new StacjaMeteo("Wrocław",true,true,true);
        Assert.assertTrue(odczytyPogodowe.nowaPogoda(stacjaMeteo).getTemperatura().getWartosc().isPresent()
                        && odczytyPogodowe.nowaPogoda(stacjaMeteo).getTemperatura().getWartosc().get() >=-25
                        && odczytyPogodowe.nowaPogoda(stacjaMeteo).getTemperatura().getWartosc().get() <=36
                        && odczytyPogodowe.nowaPogoda(stacjaMeteo).getWilgotnosc().getWartosc().isPresent()
                        && odczytyPogodowe.nowaPogoda(stacjaMeteo).getWilgotnosc().getWartosc().get()>=0
                        && odczytyPogodowe.nowaPogoda(stacjaMeteo).getWilgotnosc().getWartosc().get()<=100
                        && odczytyPogodowe.nowaPogoda(stacjaMeteo).getCisnienie().getWartosc().isPresent()
                        && odczytyPogodowe.nowaPogoda(stacjaMeteo).getCisnienie().getWartosc().get() >=985
                        && odczytyPogodowe.nowaPogoda(stacjaMeteo).getCisnienie().getWartosc().get() <=1025);
    }

    @Test
    public void nowaPogodaWszystkieWartosciNull(){
        StacjaMeteo stacjaMeteo = new StacjaMeteo("Warszawa",false,false,false);
        Assert.assertTrue(!odczytyPogodowe.nowaPogoda(stacjaMeteo).getTemperatura().getWartosc().isPresent()
                && !odczytyPogodowe.nowaPogoda(stacjaMeteo).getWilgotnosc().getWartosc().isPresent()
                && !odczytyPogodowe.nowaPogoda(stacjaMeteo).getCisnienie().getWartosc().isPresent());
    }

    @Test
    public void nowaPogodaWybraneWartosciDostepne(){
        StacjaMeteo stacjaMeteo = new StacjaMeteo("Jawor",false,true,true);
        Assert.assertTrue(!odczytyPogodowe.nowaPogoda(stacjaMeteo).getTemperatura().getWartosc().isPresent()
                && odczytyPogodowe.nowaPogoda(stacjaMeteo).getWilgotnosc().getWartosc().isPresent()
                && odczytyPogodowe.nowaPogoda(stacjaMeteo).getWilgotnosc().getWartosc().get()>=0
                && odczytyPogodowe.nowaPogoda(stacjaMeteo).getWilgotnosc().getWartosc().get()<=100
                && odczytyPogodowe.nowaPogoda(stacjaMeteo).getCisnienie().getWartosc().isPresent()
                && odczytyPogodowe.nowaPogoda(stacjaMeteo).getCisnienie().getWartosc().get() >=985
                && odczytyPogodowe.nowaPogoda(stacjaMeteo).getCisnienie().getWartosc().get() <=1025);
    }




}
