package Pogoda;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StatystykiPogodowe {

    public Optional<Double> pobierzWartosc(List<Pogoda> listaOdczytow, rodzajWielkosciFizycznej wielkoscFizyczna, int indeks){
        switch (wielkoscFizyczna){
            case TEMPERATURA:{
                return listaOdczytow.get(indeks).getTemperatura().getWartosc();
            }
            case WILGOTNOSC:{
                return listaOdczytow.get(indeks).getWilgotnosc().getWartosc();
            }
            default: break;
        }
        return listaOdczytow.get(indeks).getCisnienie().getWartosc();
    }

    public double policzSrednia(Optional<Double> wartosc1, List<Pogoda> listaOdczytow,
                                rodzajWielkosciFizycznej wielkoscFizyczna){
        double suma = wartosc1.get();
        for (int i=1; i<listaOdczytow.size(); i++){
            switch (wielkoscFizyczna){
                case TEMPERATURA: {
                    suma = suma + listaOdczytow.get(i).getTemperatura().getWartosc().get();
                    break;
                }
                case WILGOTNOSC: {
                    suma = suma + listaOdczytow.get(i).getWilgotnosc().getWartosc().get();
                    break;
                }
                case CISNIENIE: {
                    suma = suma + listaOdczytow.get(i).getCisnienie().getWartosc().get();
                    break;
                }
            }
        }
        return (suma)/(listaOdczytow.size());
    }


    public List<Optional<Double>> minSredniaMaxWartosc(List<Pogoda> listaOdczytow, rodzajWielkosciFizycznej wielkoscFizyczna){
        List<Optional<Double>> minSredniaMax = new ArrayList<>();
        Optional<Double> wartosc1;
        if (listaOdczytow.size()==0 || !(wartosc1 = pobierzWartosc(listaOdczytow, wielkoscFizyczna,0)).isPresent()){
            minSredniaMax.add(Optional.ofNullable(null));
            minSredniaMax.add(Optional.ofNullable(null));
            minSredniaMax.add(Optional.ofNullable(null));
            return minSredniaMax;
        }

        if (wartosc1.isPresent()){
            minSredniaMax.add(Optional.of(minMax(wartosc1,listaOdczytow,wielkoscFizyczna)[0]));
            minSredniaMax.add(Optional.of(policzSrednia(wartosc1,listaOdczytow,wielkoscFizyczna)));
            minSredniaMax.add(Optional.of(minMax(wartosc1,listaOdczytow,wielkoscFizyczna)[1]));
        }
        return minSredniaMax;
    }

    public Double[] minMax(Optional<Double> wartosc1, List<Pogoda> listaOdczytow,
                      rodzajWielkosciFizycznej wielkoscFizyczna){
        Double [] t = new Double[2];
        t[0] = wartosc1.get();
        t[1] = wartosc1.get();
        for (int i=1; i<listaOdczytow.size(); i++){
            if (pobierzWartosc(listaOdczytow,wielkoscFizyczna,i).get()<t[0])
                t[0] = pobierzWartosc(listaOdczytow,wielkoscFizyczna,i).get();
            if (pobierzWartosc(listaOdczytow,wielkoscFizyczna,i).get()>t[1])
                t[1] = pobierzWartosc(listaOdczytow,wielkoscFizyczna,i).get();
        }
        return t;
    }

    public String[] statystykiPogodowe(List<Pogoda> listaOdczytow, rodzajWielkosciFizycznej rodzaj){
        String[] statystyki = new String[3];
        switch (rodzaj){
            case TEMPERATURA:{
                List<Optional<Double>> wartosci = minSredniaMaxWartosc(listaOdczytow,rodzajWielkosciFizycznej.TEMPERATURA);
                Temperatura srednia = new Temperatura(wartosci.get(1));
                Temperatura min = new Temperatura(wartosci.get(0));
                Temperatura max = new Temperatura(wartosci.get(2));
                statystyki[0] = "Srednia temperatura:  " + srednia;
                statystyki[1] = "Minimalna temperatura:  " + min;
                statystyki[2] = "Maksymalna temperatura:  " + max;
                return statystyki;
            }
            case WILGOTNOSC:{
                List<Optional<Double>> wartosci = minSredniaMaxWartosc(listaOdczytow,rodzajWielkosciFizycznej.WILGOTNOSC);
                Wilgotnosc srednia = new Wilgotnosc(wartosci.get(1));
                Wilgotnosc min = new Wilgotnosc(wartosci.get(0));
                Wilgotnosc max = new Wilgotnosc(wartosci.get(2));
                statystyki[0] = "Srednia wilgotność:  " + srednia;
                statystyki[1] = "Minimalna wilgotność:  " + min;
                statystyki[2] = "Maksymalna wilgotność:  " + max;
                return statystyki;
            }
            case CISNIENIE:{
                List<Optional<Double>> wartosci = minSredniaMaxWartosc(listaOdczytow,rodzajWielkosciFizycznej.CISNIENIE);
                Cisnienie srednia = new Cisnienie(wartosci.get(1));
                Cisnienie min = new Cisnienie(wartosci.get(0));
                Cisnienie max = new Cisnienie(wartosci.get(2));
                statystyki[0] = "Srednie ciśnienie:  " + srednia;
                statystyki[1] = "Minimalne ciśnienie:  " + min;
                statystyki[2] = "Maksymalne ciśnienie:  " + max;
                return statystyki;
            }
        }
        return statystyki;
    }
}
