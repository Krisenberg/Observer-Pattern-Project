package Aplikacja;

import Pogoda.Pogoda;
import Pogoda.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Uzytkownik {
    private String nazwaUzytkownika;
    private List<StacjaMeteo> subskrybowaneStacje;
    private Map<StacjaMeteo,List<Pogoda>> historiaOdczytow;
    private StatystykiPogodowe statystyki;

    public Uzytkownik(String nazwaUzytkownika){
        this.nazwaUzytkownika=nazwaUzytkownika;
        subskrybowaneStacje=new ArrayList<>();
        historiaOdczytow=new HashMap<>();
        statystyki=new StatystykiPogodowe();
    }

    public String getNazwaUzytkownika() {
        return nazwaUzytkownika;
    }

    public List<StacjaMeteo> getSubskrybowaneStacje() {
        return subskrybowaneStacje;
    }

    public Map<StacjaMeteo, List<Pogoda>> getHistoriaOdczytow() {
        return historiaOdczytow;
    }

    public boolean equals(Object ob){
        if(ob == null) return false;
        if(ob instanceof Uzytkownik){
            Uzytkownik user = (Uzytkownik) ob;
            if (nazwaUzytkownika.equals(user.getNazwaUzytkownika()))
                return true;
        }
        return false;
    }

    public void dodajStacje(StacjaMeteo stacja){
        StacjaMeteo st = new StacjaMeteo(stacja.getLokalizacja(),stacja.getPogoda());
        subskrybowaneStacje.add(st);
        historiaOdczytow.put(st,new ArrayList<>());
    }
    public void usunStacje(StacjaMeteo stacja){
        subskrybowaneStacje.remove(stacja);
        historiaOdczytow.remove(stacja);
    }

    public void nowyOdczytStacji(StacjaMeteo stacja, Pogoda pogoda){
        subskrybowaneStacje.get(subskrybowaneStacje.indexOf(stacja)).setPogoda(pogoda);
        historiaOdczytow.get(stacja).add(pogoda);
    }

    public void wyswietlAktualnyOdczytStacji(StacjaMeteo stacja){
        if (subskrybowaneStacje.contains(stacja)){
            System.out.println("Aktualny odczyt stacji - " + stacja.getLokalizacja());
            System.out.println(stacja.getPogoda().toString());
        }
    }

    public void wyswietlHistorieStacji(StacjaMeteo stacja){
        if (subskrybowaneStacje.contains(stacja)){
            List<Pogoda> listaOdczytowStacji = historiaOdczytow.get(stacja);
            System.out.println("Historia odczytow ze stacji - " + stacja.getLokalizacja()
                    + " uzytkownika: " + nazwaUzytkownika);
            for (int i=0; i<listaOdczytowStacji.size(); i++){
                System.out.println(i+1 + ". " + listaOdczytowStacji.get(i).toString());
                System.out.println();
            }
        }
    }

    public void wyswietlSubskrybowaneStacje(){
        System.out.println("Lista stacji, ktore subskrybuje uzytkownik: " + nazwaUzytkownika);
        for (int i=0; i<subskrybowaneStacje.size(); i++){
            System.out.println(i+1 + ". " + subskrybowaneStacje.get(i).wyswietlStacjeMeteo());
        }
    }

    public void wyswietlStatystykiTemperatura(StacjaMeteo stacja){
        if (subskrybowaneStacje.contains(stacja)){
            System.out.println("STATYSTYKI - temperatura - DLA STACJI: " + stacja.getLokalizacja());
            System.out.println(statystyki.statystykiPogodowe(historiaOdczytow.get(stacja),rodzajWielkosciFizycznej.TEMPERATURA)[0]);
            System.out.println(statystyki.statystykiPogodowe(historiaOdczytow.get(stacja),rodzajWielkosciFizycznej.TEMPERATURA)[1]);
            System.out.println(statystyki.statystykiPogodowe(historiaOdczytow.get(stacja),rodzajWielkosciFizycznej.TEMPERATURA)[2]);
        }
    }

    public void wyswietlStatystykiWilgotnosc(StacjaMeteo stacja){
        if (subskrybowaneStacje.contains(stacja)){
            System.out.println("STATYSTYKI - wilgotnosc - DLA STACJI: " + stacja.getLokalizacja());
            System.out.println(statystyki.statystykiPogodowe(historiaOdczytow.get(stacja),rodzajWielkosciFizycznej.WILGOTNOSC)[0]);
            System.out.println(statystyki.statystykiPogodowe(historiaOdczytow.get(stacja),rodzajWielkosciFizycznej.WILGOTNOSC)[1]);
            System.out.println(statystyki.statystykiPogodowe(historiaOdczytow.get(stacja),rodzajWielkosciFizycznej.WILGOTNOSC)[2]);
        }
    }

    public void wyswietlStatystykiCisnienie(StacjaMeteo stacja){
        if (subskrybowaneStacje.contains(stacja)){
            System.out.println("STATYSTYKI - cisnienie - DLA STACJI: " + stacja.getLokalizacja());
            System.out.println(statystyki.statystykiPogodowe(historiaOdczytow.get(stacja),rodzajWielkosciFizycznej.CISNIENIE)[0]);
            System.out.println(statystyki.statystykiPogodowe(historiaOdczytow.get(stacja),rodzajWielkosciFizycznej.CISNIENIE)[1]);
            System.out.println(statystyki.statystykiPogodowe(historiaOdczytow.get(stacja),rodzajWielkosciFizycznej.CISNIENIE)[2]);
        }
    }

    public void wyswietlWszystkieStatystyki(StacjaMeteo stacja){
        if (subskrybowaneStacje.contains(stacja)) {
            System.out.println();
            System.out.println("WSZYSTKIE STATYSTYKI POGODOWE DLA STACJI: " + stacja.getLokalizacja());
            System.out.printf("%-45s%-45s%-45s\n","TEMPERATURA","WILGOTNOŚĆ","CIŚNIENIE");

            System.out.printf("%-45s%-45s%-45s\n",statystyki.statystykiPogodowe(historiaOdczytow.get(stacja),rodzajWielkosciFizycznej.TEMPERATURA)[0],
                    statystyki.statystykiPogodowe(historiaOdczytow.get(stacja),rodzajWielkosciFizycznej.WILGOTNOSC)[0],
                    statystyki.statystykiPogodowe(historiaOdczytow.get(stacja),rodzajWielkosciFizycznej.CISNIENIE)[0]);

            System.out.printf("%-45s%-45s%-45s\n",statystyki.statystykiPogodowe(historiaOdczytow.get(stacja),rodzajWielkosciFizycznej.TEMPERATURA)[1],
                    statystyki.statystykiPogodowe(historiaOdczytow.get(stacja),rodzajWielkosciFizycznej.WILGOTNOSC)[1],
                    statystyki.statystykiPogodowe(historiaOdczytow.get(stacja),rodzajWielkosciFizycznej.CISNIENIE)[1]);

            System.out.printf("%-45s%-45s%-45s\n",statystyki.statystykiPogodowe(historiaOdczytow.get(stacja),rodzajWielkosciFizycznej.TEMPERATURA)[2],
                    statystyki.statystykiPogodowe(historiaOdczytow.get(stacja),rodzajWielkosciFizycznej.WILGOTNOSC)[2],
                    statystyki.statystykiPogodowe(historiaOdczytow.get(stacja),rodzajWielkosciFizycznej.CISNIENIE)[2]);
        }
    }
}
