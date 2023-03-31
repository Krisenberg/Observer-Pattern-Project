package InputStacjeMeteo;

import Menu.MenuTools;
import Pogoda.StacjaMeteo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StacjeMeteoFromKeyboard {
    private List<StacjaMeteo> listaStacji;
    Scanner scanner = new Scanner(System.in);
    private boolean czyKontynuowac = true;
    private MenuTools tools;


    public StacjeMeteoFromKeyboard(){
        listaStacji = new ArrayList<>();
        tools = new MenuTools();
    }

    public void instrukcja(){
        tools.czyszczenieKonsoli();
        System.out.println("Aby utworzyć nową stację meteo należy wprowadzić 4 wartości : lokalizację stacji");
        System.out.println("oraz 3 wartości logiczne, które oznaczają obecność (lub jej brak) następujących");
        System.out.println("czujników pogodowych - temperatury, wilgotności i ciśnienia. Wartości logiczne");
        System.out.println("należy wprowadzać tekstowo jako wartości 'true' lub 'false'.");
        System.out.println();
        System.out.println("PRZYKŁAD - wprowadzenie odpowiednio wartości");
        System.out.println("Wrocław     true    true    false");
        System.out.println();
        System.out.println("Będzie równoznaczne z utworzeniem stacji meteo we Wrocławiu, która będzie miała");
        System.out.println("możliwość mierzenia temperatury i wilgotności, ale nie będzie mierzyła ciśnienia.");

    }

    public void zapytanie(){
        System.out.println();
        System.out.println("Czy dodać następną stację?");
        System.out.println("[1] Tak");
        System.out.println("[2] Nie");
        System.out.print("Wybór: ");
    }

    public boolean czyCzujnik(String wielkoscFizyczna){
        System.out.print("Czy " + wielkoscFizyczna + ": ");
        String s = scanner.nextLine();
        while(!(s.equals("true") || s.equals("false"))){
            System.out.println("Nieprawidłowa wartość. Należy wprowadzić jedną z dwóch opcji: true/false");
            System.out.print("Czy " + wielkoscFizyczna + ": ");
            s=scanner.nextLine();
        }
        return Boolean.parseBoolean(s);
    }

    public StacjaMeteo nowaStacja(){
        System.out.print("Lokalizacja: ");
        String lokalizacja = scanner.nextLine();
        boolean czyTemp = czyCzujnik("temperatura");
        boolean czyWilg = czyCzujnik("wilgotność");
        boolean czyCisn = czyCzujnik("ciśnienie");
        return new StacjaMeteo(lokalizacja,czyTemp,czyWilg,czyCisn);
    }

    public void akcjaNaZapytanie(){
        int wybor = tools.wybor(scanner,1,2);

        switch(wybor){
            case 1:{
                listaStacji.add(nowaStacja());
                break;
            }
            case 2:{
                stop();
                break;
            }
        }
    }

    public void wczytajStacje(){
        instrukcja();

        while (czyKontynuowac){
            zapytanie();
            akcjaNaZapytanie();
        }
    }

    public void stop(){
        czyKontynuowac = false;
    }

    public List<StacjaMeteo> listaStacjiMeteo(){
        wczytajStacje();
        return listaStacji;
    }

}
