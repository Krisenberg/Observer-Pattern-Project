package Menu;

import Aplikacja.Aplikacja;

import java.util.Scanner;

public class Menu {

    private Aplikacja app;
    Scanner scanner = new Scanner(System.in);
    private boolean czyKontynuowac = true;
    private MenuTools tools;

    public Menu (Aplikacja app){
        this.app = app;
        tools = new MenuTools();
    }

    public void naglowek(){
        System.out.println("   ////      ///     //////    ///     ////      ///");
        System.out.println("  /   //   /    /   /  ///   /    /   /   //   /    /");
        System.out.println(" ////     /    /   /    /   /    /   /   //   //////");
        System.out.println("/         ///      ////     ///     ////     /    /");
        System.out.println();
        System.out.println(" __________________________________________________________________________________");
        System.out.println("|  Witaj w aplikacji pogodowej - Katalogu Uniwersalnych Pomiarów Asynchronicznych  |");
        System.out.println("|__________________________________________________________________________________|");
        System.out.println();
    }

    public void wyswietlDostepneOpcje(){
        System.out.printf("%15s%n","MENU");
        System.out.println("[1] Zarejestruj się");
        System.out.println("[2] Zaloguj się");
        System.out.println("[3] Dowiedz się więcej o projekcie");
        System.out.println("[4] Ponownie skonfiguruj stacje meteo w CSI");
        System.out.println("[0] Wyjdź z aplikacji");
        System.out.println();
        System.out.print("Wybrana opcja: ");
    }

    public void wyborUzytkownika(){
        int wybor = tools.wybor(scanner,0,4);
        switch(wybor){
            case 1:{
                zarejestrujSie();
                break;
            }
            case 2:{
                zalogujSie();
                break;
            }
            case 3:{
                dowiedzSieWiecej();
                break;
            }
            case 4:{
                ponownieSkonfigurujCSI();
                break;
            }
            default :{
                koniec();
                break;
            }
        }
    }

    public void dowiedzSieWiecej(){
        tools.czyszczenieKonsoli();
        System.out.println("Aby dowiedzieć się więcej na temat projektu, Katalogu Uniwersalnych " +
                "Pomiarów Asynchronicznych");
        System.out.println("lub Centralnego Systemu Informatycznego, proszę odwiedzić domenę internetową:");
        System.out.println();
        System.out.println("https://katalog.gov.pwr.student.edu.org.com.pl/csi/FAQ");
        System.out.println();
        System.out.print("Proszę wprowadzić [0] aby powrócić do menu: ");
        tools.wybor(scanner,0,0);
        tools.czyszczenieKonsoli();
    }

    public String login(String komunikat){
        System.out.print(komunikat);
        String login = scanner.nextLine();
        if (login == null || login.equals("")){
            System.out.println("Nieprawidłowy format, login musi być niepustym napisem!");
            login(komunikat);
        }
        return login;
    }

    public String haslo(String komunikat){
        System.out.print(komunikat);
        String haslo = scanner.nextLine();
        if (haslo == null || haslo.equals("")){
            System.out.println("Nieprawidłowy format, hasło musi być niepustym napisem!");
            haslo(komunikat);
        }
        return haslo;
    }

    public void zarejestrujSie(){
        tools.wyswietlenieOddzielonegoTekstuWKonsoli("Rejestracja nowego użytkownika w Katalogu");

        String login = login("Podaj login, który będziesz używał: ");
        String haslo = haslo("Podaj nowe hasło do konta: ");

        if (app.utworzNoweKonto(login,haslo)==1){
            tools.wyswietlenieOddzielonegoTekstuWKonsoli("Zarejestrowano pomyślnie użytkownika " + login);
        } else {
            tools.wyswietlenieOddzielonegoTekstuWKonsoli("Nie zarejestrowano użytkownika, konto o takim loginie już istnieje");
        }
    }

    public void zalogujSie(){
        tools.wyswietlenieOddzielonegoTekstuWKonsoli("Ekran logowania do Katalogu");

        String login = login("Login: ");
        String haslo = haslo("Hasło: ");

        if (app.logowanie(login,haslo).containsKey(1)){
            MenuUzytkownika userMenu = new MenuUzytkownika(app, app.logowanie(login,haslo).get(1));
            tools.wyswietlenieOddzielonegoTekstuWKonsoli("");
            userMenu.menuUser();
        } else {
            tools.wyswietlenieOddzielonegoTekstuWKonsoli("Nieprawidłowy login lub hasło");
        }

    }

    public void ponownieSkonfigurujCSI(){
        tools.wyswietlenieOddzielonegoTekstuWKonsoli("Ponowna konfiguracja stacji w ramach CSI");

        String hasloA = haslo("Hasło administratora: ");

        if (hasloA.equals(app.getHasloAdmina())){
            app.ponownaKonfiguracjaCSI();
            tools.wyswietlenieOddzielonegoTekstuWKonsoli("CSI zostało skonfigurowane");
        } else {
            tools.wyswietlenieOddzielonegoTekstuWKonsoli("Błędne hasło administratora");
        }
    }

    public void uruchomAplikacje(){
        tools.wyswietlenieOddzielonegoTekstuWKonsoli("Ukończono konfigurację CSI");

        while (czyKontynuowac){
            naglowek();
            wyswietlDostepneOpcje();
            wyborUzytkownika();
        }
    }

    public void koniec(){
        czyKontynuowac = false;
    }
}
