package JSON;

import Pogoda.StacjaMeteo;

public class StacjaMeteoSerializable {
    private String lokalizacja;
    private boolean czyTemperatura = false;
    private boolean czyWilgotnosc = false;
    private boolean czyCisnienie = false;

    public StacjaMeteoSerializable(StacjaMeteo stacja){
        lokalizacja = stacja.getLokalizacja();
        if (stacja.getPogoda().getTemperatura().getWartosc().isPresent())
            czyTemperatura = true;
        if (stacja.getPogoda().getWilgotnosc().getWartosc().isPresent())
            czyWilgotnosc = true;
        if (stacja.getPogoda().getCisnienie().getWartosc().isPresent())
            czyCisnienie = true;
    }

    public String getLokalizacja() {
        return lokalizacja;
    }

    public boolean isCzyTemperatura() {
        return czyTemperatura;
    }

    public boolean isCzyWilgotnosc() {
        return czyWilgotnosc;
    }

    public boolean isCzyCisnienie() {
        return czyCisnienie;
    }
}
