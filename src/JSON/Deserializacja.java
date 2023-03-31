package JSON;

import Menu.MenuTools;
import Pogoda.StacjaMeteo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Deserializacja {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(OptionalTypeAdapter.FACTORY).create();

    public static String jsonStacjeMeteo(String path){
        boolean done = false;
        String json = "";
        String input;
        while (!done){
            try (BufferedReader br = new BufferedReader(new FileReader(path))){
                while((input = br.readLine())!=null && input.length()!=0){
                    json = json + input;
                }
                done = true;
            } catch (IOException e){
                System.out.println();
                System.out.println("Nie można odnaleźć pliku o wskazanej nazwie");
                System.out.print("Wprowadź ponownie nazwę pliku: ");
                path = MenuTools.jsonPath(new Scanner(System.in));
            }
        }
        return json;
    }

    public static List<StacjaMeteoSerializable> deserializacjaStacjiMeteo(String path){
        Type typ = new TypeToken<ArrayList<StacjaMeteoSerializable>>(){}.getType();
        List<StacjaMeteoSerializable> stacjeMeteoDeserializowane = gson.fromJson(jsonStacjeMeteo(path),typ);
        return stacjeMeteoDeserializowane;
    }

    public static List<StacjaMeteo> listaStacjiMeteo(String path){
        List<StacjaMeteo> listaStacji = new ArrayList<>();
        for (StacjaMeteoSerializable stacjaSerializable : deserializacjaStacjiMeteo(path)){
            StacjaMeteo stacja = new StacjaMeteo(stacjaSerializable.getLokalizacja(),stacjaSerializable.isCzyTemperatura(),
                    stacjaSerializable.isCzyWilgotnosc(), stacjaSerializable.isCzyCisnienie());
            listaStacji.add(stacja);
        }
        return listaStacji;
    }
}
