package MenuTests;

import Menu.MenuTools;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MenuToolsTest {
    @Test
    void isNumericTestText(){
        String s = "ashbfif";
        Assertions.assertEquals(false, MenuTools.isNumeric(s));
    }
    @Test
    void isNumericTestEmpty(){
        String s = "";
        Assertions.assertEquals(false, MenuTools.isNumeric(s));
    }
    @Test
    void isNumericTestDouble(){
        String s = "4.1";
        Assertions.assertEquals(false, MenuTools.isNumeric(s));
    }
    @Test
    void isNumericTestNegativeDouble(){
        String s = "-0.1";
        Assertions.assertEquals(false, MenuTools.isNumeric(s));
    }
    @Test
    void isNumericTestNegativeInt(){
        String s = "-5";
        Assertions.assertEquals(false, MenuTools.isNumeric(s));
    }
    @Test
    void isNumericTestIntGreaterThan9(){
        String s = "10";
        Assertions.assertEquals(true, MenuTools.isNumeric(s));
    }
    @Test
    void isNumericTestInt(){
        String s = "7";
        Assertions.assertEquals(true, MenuTools.isNumeric(s));
    }
}
