package AplikacjaTests;

import Aplikacja.Uzytkownik;
import Pogoda.Pogoda;
import Pogoda.StacjaMeteo;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class UzytkownikTest {
    private StacjaMeteo s1 = new StacjaMeteo("Wrocław",true,true,true);
    private StacjaMeteo s2 = new StacjaMeteo("Warszawa",false,true,true);
    private StacjaMeteo s3 = new StacjaMeteo("Wałbrzych",false,true,false);
    private Uzytkownik u = new Uzytkownik("UserDefault");

    public List<StacjaMeteo> koncoweStacjeMeteo(){
        List<StacjaMeteo> lista = new ArrayList<>();
        lista.add(s1);
        lista.add(s2);
        lista.add(s3);
        return lista;
    }

    public List<StacjaMeteo> koncoweStacjeMeteoPoUsunieciu(){
        List<StacjaMeteo> lista = new ArrayList<>();
        lista.add(s1);
        lista.add(s3);
        return lista;
    }

    public Map<StacjaMeteo,List<Pogoda>> koncowaHistoriaOdczytow(){
        Map<StacjaMeteo,List<Pogoda>> mapa = new HashMap<>();
        mapa.put(s1, new ArrayList<>());
        mapa.get(s1).add(new Pogoda(Optional.of(10.0),Optional.of(15.0),Optional.of(1010.0)));
        mapa.get(s1).add(new Pogoda(Optional.of(15.0),Optional.of(20.0),Optional.of(1015.0)));
        mapa.put(s2, new ArrayList<>());
        mapa.get(s2).add(new Pogoda(Optional.ofNullable(null),Optional.of(60.0),Optional.of(980.0)));
        mapa.get(s2).add(new Pogoda(Optional.ofNullable(null),Optional.of(70.0),Optional.of(1020.0)));
        mapa.get(s2).add(new Pogoda(Optional.ofNullable(null),Optional.of(80.0),Optional.of(1000.0)));
        mapa.put(s3, new ArrayList<>());
        mapa.get(s3).add(new Pogoda(Optional.ofNullable(null),Optional.of(15.0),Optional.ofNullable(null)));
        return mapa;
    }

    @Test
    public void getNazwaTest(){
        Assert.assertEquals("UserDefault",u.getNazwaUzytkownika());
    }

    @Test
    public void dodawanieStacjiTest(){
        u.dodajStacje(s1);
        u.dodajStacje(s2);
        u.dodajStacje(s3);
        Assert.assertEquals(koncoweStacjeMeteo(),u.getSubskrybowaneStacje());
    }

    @Test
    public void noweOdczytyStacji(){
        u.dodajStacje(s1);
        u.dodajStacje(s2);
        u.dodajStacje(s3);
        u.nowyOdczytStacji(s1,new Pogoda(Optional.of(10.0),Optional.of(15.0),Optional.of(1010.0)));
        u.nowyOdczytStacji(s1,new Pogoda(Optional.of(15.0),Optional.of(20.0),Optional.of(1015.0)));
        u.nowyOdczytStacji(s2,new Pogoda(Optional.ofNullable(null),Optional.of(60.0),Optional.of(980.0)));
        u.nowyOdczytStacji(s2,new Pogoda(Optional.ofNullable(null),Optional.of(70.0),Optional.of(1020.0)));
        u.nowyOdczytStacji(s2,new Pogoda(Optional.ofNullable(null),Optional.of(80.0),Optional.of(1000.0)));
        u.nowyOdczytStacji(s3,new Pogoda(Optional.ofNullable(null),Optional.of(15.0),Optional.ofNullable(null)));
        Assert.assertEquals(koncowaHistoriaOdczytow(),u.getHistoriaOdczytow());
    }

    @Test
    public void usuniecieStacjiTest(){
        u.dodajStacje(s1);
        u.dodajStacje(s2);
        u.dodajStacje(s3);
        u.usunStacje(s2);
        Assert.assertEquals(koncoweStacjeMeteoPoUsunieciu(),u.getSubskrybowaneStacje());
    }
}
