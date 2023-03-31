package CSI;

import Menu.MenuTools;

import java.util.Scanner;

public class KonfiguracjaCSI{
    Scanner scanner = new Scanner(System.in);
    CSI csi;
    MenuTools tools;

    public KonfiguracjaCSI(CSI csi){
        this.csi = csi;
        tools = new MenuTools();
    }

    public void wyswietlOpcjeDodaniaStacji(){
        System.out.println("Wybierz sposób, w jaki wprowadzisz stacje meteo do CSI");
        System.out.println("[1] Z pliku tekstowego");
        System.out.println("[2] Z klawiatury");
        System.out.println("[3] Z pliku JSON");
        System.out.println();
        System.out.print("Wybór: ");
    }
    public void wyswietlOpcjeDodaniaStacjiPonownaKonfiguracja(){
        System.out.println("Wybierz sposób, w jaki wprowadzisz stacje meteo do CSI");
        System.out.println("[1] Z pliku tekstowego");
        System.out.println("[2] Z klawiatury");
        System.out.println("[3] Z pliku JSON");
        System.out.println("[0] Powrót");
        System.out.println();
        System.out.print("Wybór: ");
    }


    public void stacjeMeteoPlik(){
        System.out.print("Podaj nazwę pliku ze stacjami meteo: ");
        String path = MenuTools.txtPath(scanner);
        csi.wczytajStacjeMeteoZPliku(path);
    }

    public void stacjeMeteoKlawiatura(){
        csi.wczytajStacjeMeteoZKlawiatury();
    }

    public void stacjeMeteoPlikJSON(){
        System.out.print("Podaj nazwę pliku (JSON) ze stacjami meteo: ");
        String path = MenuTools.jsonPath(scanner);
        csi.wczytajStacjeMeteoJSON(path);
    }

    public void konfiguracja(int numerKonfiguracji){
        tools.wyswietlenieOddzielonegoTekstuWKonsoli("Konfiguracja CSI - wprowadzenie do systemu dostępnych stacji z czujnikami");
        int wybor;
        if (numerKonfiguracji == 0){
            wyswietlOpcjeDodaniaStacji();
            wybor = tools.wybor(scanner,1,3);
        } else {
            wyswietlOpcjeDodaniaStacjiPonownaKonfiguracja();
            wybor = tools.wybor(scanner,0,3);
        }
        switch(wybor){
            case 1: {
                stacjeMeteoPlik();
                break;
            }
            case 2: {
                stacjeMeteoKlawiatura();
                break;
            }
            case 3:{
                stacjeMeteoPlikJSON();
                break;
            }
            case 0:{
                break;
            }
        }
    }
}
