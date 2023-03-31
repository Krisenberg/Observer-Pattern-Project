package Pogoda;

import java.util.Optional;

public class Wilgotnosc extends WielkoscFizyczna {

    public Wilgotnosc(){
        super();
        setRodzaj(rodzajWielkosciFizycznej.WILGOTNOSC);
    }

    public Wilgotnosc(Optional<Double> wilgotnosc) {
        super(wilgotnosc);
        setRodzaj(rodzajWielkosciFizycznej.WILGOTNOSC);
    }

    public String toString(){
        String s = super.toString();
        if (!s.equals("Wartość niedostępna"))
            s=s+" %";
        return s;
    }
}
