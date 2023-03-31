package InputStacjeMeteo;

import Menu.MenuTools;
import Pogoda.StacjaMeteo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StacjeMeteoFromFile {

    private static List<StacjaMeteo> listaStacji = new ArrayList<>();

    public static void wczytajStacje(String path){
        boolean done = false;
        while (!done){
            try(BufferedReader br = new BufferedReader(new FileReader(path))){
                br.readLine();
                br.readLine();
                br.readLine();
                String input;
                while ((input = br.readLine())!=null && input.length()!=0){
                    String [] t = input.split("\\s+");
                    String lokalizacja = t[0];
                    boolean czyTemp = Boolean.parseBoolean(t[1]);
                    boolean czyWilg = Boolean.parseBoolean(t[2]);
                    boolean czyCisn = Boolean.parseBoolean(t[3]);
                    StacjaMeteo stacja;

                    if (lokalizacja.contains("_")){
                        String nowaLokalizacja = lokalizacja.replace("_"," ");
                        stacja = new StacjaMeteo(nowaLokalizacja,czyTemp,czyWilg,czyCisn);
                    } else {
                        stacja = new StacjaMeteo(lokalizacja,czyTemp,czyWilg,czyCisn);
                    }
                    listaStacji.add(stacja);
                    done = true;
                }
            } catch (IOException e){
                System.out.println();
                System.out.println("Nie można odnaleźć pliku o wskazanej nazwie");
                System.out.print("Wprowadź ponownie nazwę pliku: ");
                path = MenuTools.txtPath(new Scanner(System.in));
            }
        }
    }

    public static List<StacjaMeteo> listaStacjiMeteo(String path){
        wczytajStacje(path);
        return listaStacji;
    }

}
