package Pogoda;

import java.util.Optional;

public class Cisnienie extends WielkoscFizyczna {

    public Cisnienie(){
        super();
        setRodzaj(rodzajWielkosciFizycznej.CISNIENIE);
    }

    public Cisnienie(Optional<Double> cisnienie){
        super(cisnienie);
        setRodzaj(rodzajWielkosciFizycznej.CISNIENIE);
    }

    public String toString(){
        String s = super.toString();
        if (!s.equals("Wartość niedostępna"))
            s=s+ " hPa";
        return s;
    }
}
