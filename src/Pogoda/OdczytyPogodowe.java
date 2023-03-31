package Pogoda;

import java.util.Optional;
import java.util.Random;

public class OdczytyPogodowe {
    private final Random random = new Random();
    private WielkoscFizyczna wF;
    private double nowyOdczyt;

    public WielkoscFizyczna nowaWartosc(WielkoscFizyczna wielkoscFizyczna){
        switch (wielkoscFizyczna.getRodzaj()){
            case TEMPERATURA:{
                if (wielkoscFizyczna.getWartosc().isPresent()){
                    nowyOdczyt = -25.0 + (51.0)*random.nextDouble();
                    return wF = new Temperatura(Optional.of(nowyOdczyt));
                }
                return wF = new Temperatura();
            }
            case WILGOTNOSC:{
                if (wielkoscFizyczna.getWartosc().isPresent()){
                    nowyOdczyt = 0.0 + (100.0)*random.nextDouble();
                    return wF = new Wilgotnosc(Optional.of(nowyOdczyt));
                }
                return wF = new Wilgotnosc();
            }
            case CISNIENIE:{
                if (wielkoscFizyczna.getWartosc().isPresent()){
                    nowyOdczyt = 985.0 + (40.0)*random.nextDouble();
                    return wF = new Cisnienie(Optional.of(nowyOdczyt));
                }
                return wF = new Cisnienie();
            }
            default: return wF = new WielkoscFizyczna(Optional.ofNullable(null));
        }
    }

    public Pogoda nowaPogoda(StacjaMeteo stacja){
        Pogoda staraPogoda = stacja.getPogoda();
        Pogoda nowaPogoda = new Pogoda();

        if (nowaWartosc(staraPogoda.getTemperatura()) instanceof Temperatura){
            Temperatura t = (Temperatura) nowaWartosc(staraPogoda.getTemperatura());
            nowaPogoda.setTemperatura(t);
        }
        if (nowaWartosc(staraPogoda.getWilgotnosc()) instanceof Wilgotnosc){
            Wilgotnosc w = (Wilgotnosc) nowaWartosc(staraPogoda.getWilgotnosc());
            nowaPogoda.setWilgotnosc(w);
        }
        if (nowaWartosc(staraPogoda.getCisnienie()) instanceof Cisnienie){
            Cisnienie c = (Cisnienie) nowaWartosc(staraPogoda.getCisnienie());
            nowaPogoda.setCisnienie(c);
        }

        return nowaPogoda;
    }
}
