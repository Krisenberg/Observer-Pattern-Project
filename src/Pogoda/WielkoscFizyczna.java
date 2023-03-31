package Pogoda;

import java.text.DecimalFormat;
import java.util.Optional;

public class WielkoscFizyczna {
    private static final DecimalFormat df = new DecimalFormat("0.0");
    private rodzajWielkosciFizycznej rodzaj;
    private Optional<Double> wartosc;

    public WielkoscFizyczna(){
        wartosc = Optional.ofNullable(null);
    }

    public WielkoscFizyczna(Optional<Double> wartosc) {
        this.wartosc = wartosc;
    }

    public Optional<Double> getWartosc() {
        return wartosc;
    }
    public void setWartosc(Optional<Double> wartosc) {
        this.wartosc = wartosc;
    }

    public rodzajWielkosciFizycznej getRodzaj() {
        return rodzaj;
    }
    public void setRodzaj(rodzajWielkosciFizycznej rodzaj) {
        this.rodzaj = rodzaj;
    }

    public String toString(){
        if (wartosc.isPresent()){
            double w = wartosc.get();
            return df.format(w);
        }
        return "Wartość niedostępna";
    }

    public boolean equals(Object ob){
        if(ob == null) return false;
        if(ob instanceof WielkoscFizyczna){
            WielkoscFizyczna wielkosc = (WielkoscFizyczna) ob;
            if (rodzaj.equals(wielkosc.getRodzaj()) && wartosc.equals(wielkosc.getWartosc()))
                return true;
        }
        return false;
    }
}
