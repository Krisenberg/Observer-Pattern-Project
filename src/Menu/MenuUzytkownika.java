package Menu;

import Aplikacja.Aplikacja;
import Pogoda.StacjaMeteo;
import Aplikacja.Uzytkownik;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuUzytkownika {
    private Uzytkownik user;
    private boolean czyKontynuowac = true;
    private MenuTools tools;
    private Aplikacja app;
    Scanner scanner = new Scanner(System.in);
    private Object semafor;

    public MenuUzytkownika(Aplikacja app, Uzytkownik user){
        this.app = app;
        this.user = user;
        tools = new MenuTools();
        semafor = app.getSemafor();
    }

    public void wyswietlOpcje(){
        System.out.println("MENU UŻYTKOWNIKA " + user.getNazwaUzytkownika());
        System.out.println("[1] Wyświetl wszystkie dostępne lokalizacje z czujnikami");
        System.out.println("[2] Wyświetl subskrybowane czujniki pogodowe");
        System.out.println("[3] Zasubskrybuj nowy czujnik");
        System.out.println("[4] Analiza danych z konkretnego czujnika - statystyki");
        System.out.println("[5] Wyświetl aktualny odczyt z konkretnego czujnika");
        System.out.println("[6] Wyświetl historię odczytów danej stacji");
        System.out.println("[7] Anuluj subskrypcję czujnika");
        System.out.println("[8] Zapisz wszystkie zgromadzone dane do pliku .json");
        System.out.println("[9] Pobierz listę dostępnych stacji meteo w CSI do pliku .json");
        System.out.println("[10] Usuń konto z systemu");
        System.out.println();
        System.out.println("[0] Wyloguj się");
        System.out.println();
        System.out.print("Wybrana opcja: ");
    }

    public void wyswietlWszystkieLokalizacje(){
        tools.czyszczenieKonsoli();
        System.out.println("Lista wszystkich dostępnych lokalizacji z czujnikami pogodowymi:");
        for (int i=0; i<app.getStacjeMeteo().size(); i++){
            System.out.println(i+1 + ". " + app.getStacjeMeteo().get(i).wyswietlStacjeMeteo());
        }
        tools.oddzielenieTekstuWKonsoli();
    }

    public void wyswietlSubskrybowaneCzujniki(){
        tools.czyszczenieKonsoli();
        if (user.getSubskrybowaneStacje().size()>0){
            user.wyswietlSubskrybowaneStacje();
            tools.oddzielenieTekstuWKonsoli();
        } else {
            tools.wyswietlenieOddzielonegoTekstuWKonsoli("Użytkownik nie subskrybuje aktualnie żadnej stacji");
        }
    }

    public List <StacjaMeteo> wolneStacje(){
        List<StacjaMeteo> wolne = new ArrayList<>();
        for (StacjaMeteo stacja : app.getStacjeMeteo()){
            if (!user.getSubskrybowaneStacje().contains(stacja)){
                wolne.add(stacja);
            }
        }
        return wolne;
    }

    public void wyswietlWolneStacje(List<StacjaMeteo> wolneStacje){
        System.out.println("Lista wolnych stacji");
        for (int i=0; i<wolneStacje.size(); i++){
            System.out.println((i+1) + ". " + wolneStacje.get(i).wyswietlStacjeMeteo());
        }
    }

    public void zasubskrybujNowyCzujnik(){
        tools.czyszczenieKonsoli();
        List<StacjaMeteo> wolneStacje = wolneStacje();
        if (wolneStacje.size()>0){
            wyswietlWolneStacje(wolneStacje);

            System.out.println();
            System.out.print("Numer stacji, którą chcesz zasubskrybować: ");
            int wybor = tools.wybor(scanner,1,wolneStacje.size());

            synchronized (semafor){
                user.dodajStacje(wolneStacje.get(wybor-1));
            }

            tools.wyswietlenieOddzielonegoTekstuWKonsoli("Stacja została dodana");
        } else {
            tools.wyswietlenieOddzielonegoTekstuWKonsoli("Użytkownik subskrybuje już wszystkie dostępne stacje");
        }
    }

    public void dzialanieNaSubskrybowanychStacjach(int wybor){
        wyswietlSubskrybowaneCzujniki();
        if (user.getSubskrybowaneStacje().size()>0){
            synchronized (semafor){
                switch(wybor){
                    case 4:{
                        analizaDanychCzujnika();
                        break;
                    }
                    case 5:{
                        aktualnyOdczytCzujnika();
                        break;
                    }
                    case 6:{
                        historiaCzujnika();
                        break;
                    }
                    case 7:{
                        anulujSubskrypcjeCzujnika();
                        break;
                    }
                }
            }
            tools.oddzielenieTekstuWKonsoli();
        }
    }

    public void analizaDanychCzujnika(){
        System.out.print("Wybierz stację, z której mają zostać wyświetlone statystyki: ");
        int wybor = tools.wybor(scanner,1,user.getSubskrybowaneStacje().size());

        tools.czyszczenieKonsoli();
        wyborStatystyk(wybor-1);
    }

    public void wyborStatystyk(int indeks){
        StacjaMeteo stacja = user.getSubskrybowaneStacje().get(indeks);
        wyswietlDostepneStatystyki(stacja.getLokalizacja());

        int wybor = tools.wybor(scanner,0,4);
        tools.czyszczenieKonsoli();

        switch(wybor){
            case 1:{
                user.wyswietlStatystykiTemperatura(stacja);
                break;
            }
            case 2:{
                user.wyswietlStatystykiWilgotnosc(stacja);
                break;
            }
            case 3:{
                user.wyswietlStatystykiCisnienie(stacja);
                break;
            }
            case 4:{
                user.wyswietlWszystkieStatystyki(stacja);
                break;
            }
        }
    }

    public void wyswietlDostepneStatystyki(String lokalizacja){
        System.out.println("MENU - statystyki stacji " + lokalizacja);
        System.out.println("[1] Wyświetl statystyki temperatury");
        System.out.println("[2] Wyświetl statystyki wilgotności");
        System.out.println("[3] Wyświetl statystyki ciśnienia");
        System.out.println("[4] Wyświetl wszystkie statystyki");
        System.out.println();
        System.out.println("[0] Powrót");
        System.out.println();
        System.out.print("Wybrana opcja: ");
    }

    public void aktualnyOdczytCzujnika(){
        System.out.print("Wybierz stację, z której chcesz wyświetlić aktualny odczyt: ");
        int wybor = tools.wybor(scanner,1,user.getSubskrybowaneStacje().size());
        tools.czyszczenieKonsoli();
        user.wyswietlAktualnyOdczytStacji(user.getSubskrybowaneStacje().get(wybor-1));
    }

    public void historiaCzujnika(){
        System.out.print("Wybierz stację, z której chcesz wyświetlić historię odczytów: ");
        int wybor = tools.wybor(scanner,1,user.getSubskrybowaneStacje().size());
        tools.czyszczenieKonsoli();
        user.wyswietlHistorieStacji(user.getSubskrybowaneStacje().get(wybor-1));
    }

    public void anulujSubskrypcjeCzujnika(){
        System.out.print("Numer stacji, którą chcesz odsubskrybować: ");
        int wybor = tools.wybor(scanner,1,user.getSubskrybowaneStacje().size());

        user.usunStacje(user.getSubskrybowaneStacje().get(wybor-1));
        tools.czyszczenieKonsoli();
        System.out.println("Stacja została usunięta");
    }

    public void usunKonto(){
        tools.czyszczenieKonsoli();
        System.out.println("Czy na pewno chcesz usunąć konto z systemu razem ze wszystkimi zgromadzonymi danymi?");
        System.out.println("[1] Tak");
        System.out.println("[2] Nie");
        System.out.print("Wybór: ");
        int wybor = tools.wybor(scanner,1,2);

        if (wybor == 1){
            app.usunUzytkownika(user);
            koniec();
            tools.wyswietlenieOddzielonegoTekstuWKonsoli("Usunięto użytkownika " + user.getNazwaUzytkownika() + " z systemu");
        } else {
            tools.wyswietlenieOddzielonegoTekstuWKonsoli("Anulowano operację usunięcia użytkownika z systemu");
        }
    }

    public void zapisDoPlikuJSON(){
        tools.czyszczenieKonsoli();
        System.out.println("-- SERIALIZACJA WSZYSTKICH ZGROMADZONYCH DANYCH DO PLIKU JSON --");
        System.out.print("Wprowadź nazwę pliku, do którego zostaną zapisane dane (bez rozszerzenia): ");
        String path = scanner.nextLine() + ".json";

        app.serializacjaHistoriiOdczytow(path,user.getHistoriaOdczytow());

        tools.wyswietlenieOddzielonegoTekstuWKonsoli("Serializacja zakończona");
    }

    public void zapisStacjiDoPlikuJSON(){
        tools.czyszczenieKonsoli();
        System.out.println("-- SERIALIZACJA STACJI METEO DOSTĘPNYCH W CSI DO PLIKU JSON --");
        System.out.println("Serializacja polega na zapisaniu do pliku .json wszystkich stacji meteo dostępnych");
        System.out.println("w ramach CSI. Z każdej stacji zostanie zapisana nazwa jej lokalizacji oraz trzy wartości");
        System.out.println("logiczne rezprezentujące dostępność poszczególnych czujników w danej stacji.");
        System.out.println();
        System.out.print("Wprowadź nazwę pliku, do którego zostaną zapisane stacje meteo (bez rozszerzenia): ");
        String path = scanner.nextLine() + ".json";

        app.serializacjaStacjiMeteo(path);

        tools.wyswietlenieOddzielonegoTekstuWKonsoli("Serializacja zakończona");
    }

    public void wybor(){
        int wybor = tools.wybor(scanner,0,10);
        switch(wybor){
            case 1:{
                wyswietlWszystkieLokalizacje();
                break;
            }
            case 2:{
                wyswietlSubskrybowaneCzujniki();
                break;
            }
            case 3:{
                zasubskrybujNowyCzujnik();
                break;
            }
            case 4:{
                dzialanieNaSubskrybowanychStacjach(4);
                break;
            }
            case 5:{
                dzialanieNaSubskrybowanychStacjach(5);
                break;
            }
            case 6:{
                dzialanieNaSubskrybowanychStacjach(6);
                break;
            }
            case 7:{
                dzialanieNaSubskrybowanychStacjach(7);
                break;
            }
            case 8:{
                zapisDoPlikuJSON();
                break;
            }
            case 9:{
                zapisStacjiDoPlikuJSON();
                break;
            }
            case 10:{
                usunKonto();
                break;
            }
            case 0:{
                koniec();
                break;
            }
        }
    }

    public void menuUser(){
        while (czyKontynuowac){
            wyswietlOpcje();
            wybor();
        }
    }

    public void koniec(){
        czyKontynuowac = false;
        tools.wyswietlenieOddzielonegoTekstuWKonsoli("Wylogowano użytkownika " + user.getNazwaUzytkownika());
    }
}
