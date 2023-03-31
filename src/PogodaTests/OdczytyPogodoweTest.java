package PogodaTests;

//import static org.junit.jupiter.api.Assertions.*;

import Pogoda.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
//import org.junit.BeforeClass;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OdczytyPogodoweTest {

    private OdczytyPogodowe klasaTestowana;

    @Mock
    private Temperatura temperaturaMock;

    @Mock
    private Wilgotnosc wilgotnoscMock;

    @Mock
    private Cisnienie cisnienieMock;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        klasaTestowana = new OdczytyPogodowe();
    }

    @Test
     public void nowaWartoscTempNotNull(){
        when(temperaturaMock.getRodzaj()).thenReturn(rodzajWielkosciFizycznej.TEMPERATURA);
        when(temperaturaMock.getWartosc()).thenReturn(Optional.of(22.2));
        Assertions.assertTrue(klasaTestowana.nowaWartosc(temperaturaMock).getWartosc().isPresent()
                            && klasaTestowana.nowaWartosc(temperaturaMock).getWartosc().get()>=-25
                            && klasaTestowana.nowaWartosc(temperaturaMock).getWartosc().get()<=36);
    }

    @Test
    public void nowaWartoscWilgNotNull(){
        when(wilgotnoscMock.getRodzaj()).thenReturn(rodzajWielkosciFizycznej.WILGOTNOSC);
        when(wilgotnoscMock.getWartosc()).thenReturn(Optional.of(72.5));
        Assertions.assertTrue(klasaTestowana.nowaWartosc(wilgotnoscMock).getWartosc().isPresent()
                && klasaTestowana.nowaWartosc(wilgotnoscMock).getWartosc().get()>=0
                && klasaTestowana.nowaWartosc(wilgotnoscMock).getWartosc().get()<=100);
    }

    @Test
    public void nowaWartoscCisnNotNull(){
        when(cisnienieMock.getRodzaj()).thenReturn(rodzajWielkosciFizycznej.CISNIENIE);
        when(cisnienieMock.getWartosc()).thenReturn(Optional.of(1008.25));
        Assertions.assertTrue(klasaTestowana.nowaWartosc(cisnienieMock).getWartosc().isPresent()
                && klasaTestowana.nowaWartosc(cisnienieMock).getWartosc().get()>=985
                && klasaTestowana.nowaWartosc(cisnienieMock).getWartosc().get()<=1025);
    }

//    @Test
//    void nowaPogodaNotNull(){
//
//    }
}
