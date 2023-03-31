package JSON;

import Pogoda.Pogoda;

import java.util.Optional;

public class PogodaSerializable {
    Optional<Double> temperatura;
    Optional<Double> wilgotnosc;
    Optional<Double> cisnienie;
    public PogodaSerializable(Pogoda pogoda){
        this.temperatura = pogoda.getTemperatura().getWartosc();
        this.wilgotnosc = pogoda.getWilgotnosc().getWartosc();
        this.cisnienie = pogoda.getCisnienie().getWartosc();
    }
}
