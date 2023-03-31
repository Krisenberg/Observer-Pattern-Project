package CSITests;

import Aplikacja.Uzytkownik;
import CSI.CSI;
import Pogoda.OdczytyPogodowe;
import Pogoda.Pogoda;
import Pogoda.StacjaMeteo;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CSITest {

//    private CSI klasaTestowana = new CSI();
    @Test
    public void testUpdate(){
        CSI csiMock = mock(CSI.class);
        doCallRealMethod().when(csiMock).setStacjeMeteoForTest(data());
        doCallRealMethod().when(csiMock).getStacjeMeteo();
        csiMock.setStacjeMeteoForTest(data());

        OdczytyPogodowe odczytyPogodoweMock = mock(OdczytyPogodowe.class);
        when(odczytyPogodoweMock.nowaPogoda(csiMock.getStacjeMeteo().get(0))).thenReturn(new Pogoda(Optional.of(10.0),Optional.of(35.0),Optional.of(988.0)));
        when(odczytyPogodoweMock.nowaPogoda(csiMock.getStacjeMeteo().get(1))).thenReturn(new Pogoda(Optional.ofNullable(null),Optional.of(70.0),Optional.ofNullable(null)));
        when(odczytyPogodoweMock.nowaPogoda(csiMock.getStacjeMeteo().get(2))).thenReturn(new Pogoda(Optional.of(27.0),Optional.ofNullable(null),Optional.of(1021.0)));

        doCallRealMethod().when(csiMock).update(odczytyPogodoweMock);
        doNothing().when(csiMock).noweOdczyty();
        csiMock.update(odczytyPogodoweMock);

        Assert.assertTrue(csiMock.getStacjeMeteo().get(0).getPogoda().equals(new Pogoda(Optional.of(10.0),Optional.of(35.0),Optional.of(988.0))));
        Assert.assertTrue(csiMock.getStacjeMeteo().get(1).getPogoda().equals(new Pogoda(Optional.ofNullable(null),Optional.of(70.0),Optional.ofNullable(null))));
        Assert.assertTrue(csiMock.getStacjeMeteo().get(2).getPogoda().equals(new Pogoda(Optional.of(27.0),Optional.ofNullable(null),Optional.of(1021.0))));

    }

    @Test
    public void testNoweOdczyty(){
        CSI csi = new CSI();
        Uzytkownik u = userData();

        csi.register(u);
        csi.setStacjeMeteoForTest(stacjeData());
        csi.noweOdczyty();

        Assert.assertEquals(new Pogoda(Optional.of(10.0),Optional.of(35.0),Optional.of(988.0)),u.getSubskrybowaneStacje().get(0).getPogoda());
    }

    @Test
    public void testKorektaStacjiMeteo(){
        CSI csi = new CSI();
        Uzytkownik u = userData();
        csi.register(u);
        csi.setStacjeMeteoForTest(dataBezStacjiTrzeciej());
        List<StacjaMeteo> expectedList = data().subList(0,1);
        csi.korektaSubskrybowanychStacji();
        Assert.assertEquals(expectedList,u.getSubskrybowaneStacje());

    }



    public List<StacjaMeteo> data(){
        List<StacjaMeteo> lista = new ArrayList<>();
        StacjaMeteo s1 = new StacjaMeteo("ABC",true,true,true);
        StacjaMeteo s2 = new StacjaMeteo("DEF",false, true,false);
        StacjaMeteo s3 = new StacjaMeteo("XYZ",true,false,true);
        lista.add(s1);
        lista.add(s2);
        lista.add(s3);
        return lista;
    }

    public List<StacjaMeteo> dataBezStacjiTrzeciej(){
        List<StacjaMeteo> lista = new ArrayList<>();
        StacjaMeteo s1 = new StacjaMeteo("ABC",true,true,true);
        StacjaMeteo s2 = new StacjaMeteo("DEF",false, true,false);
        lista.add(s1);
        lista.add(s2);
        return lista;
    }

    public List<StacjaMeteo> stacjeData(){
        List<StacjaMeteo> lista = new ArrayList<>();
        StacjaMeteo s1 = new StacjaMeteo("ABC", new Pogoda(Optional.of(10.0),Optional.of(35.0),Optional.of(988.0)));
        StacjaMeteo s2 = new StacjaMeteo("DEF",new Pogoda(Optional.ofNullable(null),Optional.of(70.0),Optional.ofNullable(null)));
        StacjaMeteo s3 = new StacjaMeteo("XYZ",new Pogoda(Optional.of(27.0),Optional.ofNullable(null),Optional.of(1021.0)));
        lista.add(s1);
        lista.add(s2);
        lista.add(s3);
        return lista;
    }

    public Uzytkownik userData(){
        Uzytkownik user = new Uzytkownik("user");
        user.dodajStacje(data().get(0));
        user.dodajStacje(data().get(2));
        return user;
    }
}
