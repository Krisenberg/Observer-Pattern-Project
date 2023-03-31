package Pogoda;

import java.util.Optional;

public class Temperatura extends WielkoscFizyczna {

    public Temperatura(){
        super();
        setRodzaj(rodzajWielkosciFizycznej.TEMPERATURA);
    }

    public Temperatura(Optional<Double> temperatura) {
        super(temperatura);
        setRodzaj(rodzajWielkosciFizycznej.TEMPERATURA);
    }

    public String toString(){
        String s = super.toString();
        if (!s.equals("Wartość niedostępna"))
            s=s+" ℃";
        return s;
    }
}
