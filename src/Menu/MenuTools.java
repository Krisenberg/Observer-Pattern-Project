package Menu;

import java.io.File;
import java.nio.file.Files;
import java.util.Scanner;

public class MenuTools {

    public static boolean isNumeric(String str) {
        return str.matches("\\d+?");
    }

    public static String txtPath(Scanner scanner){
        String path = scanner.nextLine();
        if (!path.endsWith(".txt")){
            path = path + ".txt";
        }
        return path;
    }

    public static String jsonPath(Scanner scanner){
        String path = scanner.nextLine();
        if (!path.endsWith(".json")){
            path = path + ".json";
        }
        return path;
    }

    public int wybor(Scanner scanner, int poczatek, int koniec){
        String wybor = scanner.nextLine();
        if (isNumeric(wybor)){
            if (Integer.parseInt(wybor)>=poczatek && Integer.parseInt(wybor)<=koniec)
                return Integer.parseInt(wybor);
        }
        while(!isNumeric(wybor) || Integer.parseInt(wybor)<poczatek || Integer.parseInt(wybor)>koniec){
            System.out.print("Błędna wartość. Należy wybrać numer dostępny na liście. Nowy wybór: ");
            wybor = scanner.nextLine();
        }
        return Integer.parseInt(wybor);
    }

    public void czyszczenieKonsoli(){
        for (int i=0; i<25; i++){
            System.out.println();
        }
    }

    public void oddzielenieTekstuWKonsoli(){
        System.out.println(" __________________________________________________________________________________");
    }

    public void wyswietlenieOddzielonegoTekstuWKonsoli(String tekstDoWyswietlenia){
        czyszczenieKonsoli();
        System.out.println(tekstDoWyswietlenia);
        oddzielenieTekstuWKonsoli();
    }
}
