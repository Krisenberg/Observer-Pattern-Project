package Pogoda;

import java.util.Optional;

public class StacjaMeteo {
    private String lokalizacja;
    private Pogoda pogoda;

    public StacjaMeteo(String lokalizacja, Pogoda pogoda){
        this.lokalizacja = lokalizacja;
        this.pogoda = pogoda;
    }

    public StacjaMeteo(String lokalizacja, boolean czyTemperatura,
                       boolean czyWilgotnosc, boolean czyCisnienie){
        this.lokalizacja = lokalizacja;
        pogoda = new Pogoda();
        ustawDomyslne(czyTemperatura,czyWilgotnosc,czyCisnienie);
    }

    public void ustawDomyslne(boolean t, boolean w, boolean c){
        if (t) pogoda.getTemperatura().setWartosc(Optional.ofNullable(18.0));
        if (w) pogoda.getWilgotnosc().setWartosc(Optional.ofNullable(60.0));
        if (c) pogoda.getCisnienie().setWartosc(Optional.ofNullable(1013.25));
    }

    public String getLokalizacja() {
        return lokalizacja;
    }

    public Pogoda getPogoda() {
        return pogoda;
    }
    public void setPogoda(Pogoda pogoda) {
        this.pogoda = pogoda;
    }

    public String toString(){
        String dostepneSkrot = "";
        String dostepne ="(";
        if (pogoda.getTemperatura().getWartosc().isPresent()){
            dostepneSkrot=dostepneSkrot+"T";
            dostepne=dostepne+"temperatura/";
        }
        if (pogoda.getWilgotnosc().getWartosc().isPresent()){
            dostepneSkrot=dostepneSkrot+"W";
            dostepne=dostepne+"wilgotnosc/";
        }
        if (pogoda.getCisnienie().getWartosc().isPresent()){
            dostepneSkrot=dostepneSkrot+"C";
            dostepne=dostepne+"cisnienie/";
        }
        dostepne=dostepne.substring(0,dostepne.length()-1) + ")";
        return lokalizacja + " " + dostepneSkrot + " " + dostepne;
    }

    public String wyswietlStacjeMeteo(){
        String dostepne;
        if (lokalizacja.contains(" ")){
            dostepne = toString().split("\\s+")[3];
        } else {
            dostepne = toString().split("\\s+")[2];
        }
        dostepne=dostepne.substring(1,dostepne.length()-1);
        return "Lokalizacja stacji meteo: " + lokalizacja + ". Dostepne czujniki: " + dostepne;
    }

    public boolean equals(Object st){
        if (this == st) return true;
        if(st == null) return false;
        if(st instanceof StacjaMeteo){
            StacjaMeteo kl = (StacjaMeteo) st;
            if (lokalizacja.equals(kl.getLokalizacja()))
                return true;
        }
        return false;
    }

    public int hashCode() {
        int result = (int) (lokalizacja.hashCode() ^ (lokalizacja.hashCode() >>> 32));
        result = 31 * result + toString().hashCode();
        return result;
    }
}
