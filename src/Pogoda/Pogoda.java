package Pogoda;

import java.util.Optional;

public class Pogoda{
    private Temperatura temperatura;
    private Wilgotnosc wilgotnosc;
    private Cisnienie cisnienie;

    public Pogoda(){
        temperatura = new Temperatura();
        wilgotnosc = new Wilgotnosc();
        cisnienie = new Cisnienie();
    }

    public Pogoda(Optional<Double> tempertura, Optional<Double> wilgotnosc, Optional<Double> cisnienie){
        this.temperatura=new Temperatura(tempertura);
        this.wilgotnosc=new Wilgotnosc(wilgotnosc);
        this.cisnienie=new Cisnienie(cisnienie);
    }

    public Temperatura getTemperatura() {
        return temperatura;
    }
    public void setTemperatura(Temperatura temperatura) {
        this.temperatura = temperatura;
    }

    public Wilgotnosc getWilgotnosc() {
        return wilgotnosc;
    }
    public void setWilgotnosc(Wilgotnosc wilgotnosc) {
        this.wilgotnosc = wilgotnosc;
    }

    public Cisnienie getCisnienie() {
        return cisnienie;
    }
    public void setCisnienie(Cisnienie cisnienie) {
        this.cisnienie = cisnienie;
    }

    public String toString(){
        String s = String.format("%s %s","Temperatura: ",temperatura.toString() + "\n");
        s = s+String.format("%s %s","Wilgotność: ",wilgotnosc.toString() + "\n");
        s = s+String.format("%s %s","Ciśnienie: ",cisnienie.toString());
        return s;
    }

    public boolean equals(Object pogoda){
        if (this == pogoda) return true;
        if(pogoda == null) return false;
        if(pogoda instanceof Pogoda){
            Pogoda pogoda1 = (Pogoda) pogoda;
            if (temperatura.getWartosc().equals(pogoda1.getTemperatura().getWartosc())
                && wilgotnosc.getWartosc().equals(pogoda1.getWilgotnosc().getWartosc())
                && cisnienie.getWartosc().equals(pogoda1.getCisnienie().getWartosc()))
                return true;
        }
        return false;
    }

    public int hashCode() {
        int result = (int) (temperatura.hashCode() ^ (temperatura.hashCode() >>> 32));
        result = 31 * result + wilgotnosc.hashCode();
        result = 31 * result + cisnienie.hashCode();
        return result;
    }
}
