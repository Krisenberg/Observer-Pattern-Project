package JSON;

import Pogoda.Pogoda;
import Pogoda.StacjaMeteo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Serializacja {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(OptionalTypeAdapter.FACTORY).create();

    public static Map<String,List<PogodaSerializable>> historiaWzgledemLokalizacji(Map<StacjaMeteo,List<Pogoda>> historiaOdczytow){
        Map<String,List<PogodaSerializable>> nowaMapaWzgledemNazwyStacji = new HashMap<>();
        for (StacjaMeteo stacja : historiaOdczytow.keySet()){
            List<PogodaSerializable> nowaListaOdczytow = new ArrayList<>();
            for (Pogoda pogoda : historiaOdczytow.get(stacja)){
                nowaListaOdczytow.add(new PogodaSerializable(pogoda));
            }
            nowaMapaWzgledemNazwyStacji.put(stacja.getLokalizacja(),nowaListaOdczytow);
        }
        return nowaMapaWzgledemNazwyStacji;
    }

    public static String gsonHistoriaOdczytow(Map<StacjaMeteo,List<Pogoda>> historiaOdczytow){
        return gson.toJson(historiaWzgledemLokalizacji(historiaOdczytow));
    }

    public static void serializacjaDoPlikuHistoriiOdczytow(String path, Map<StacjaMeteo,List<Pogoda>> historiaOdczytow){
        String doSerializacji = gsonHistoriaOdczytow(historiaOdczytow);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            bw.write(doSerializacji);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static List<StacjaMeteoSerializable> nowaListaStacji(List<StacjaMeteo> staraListaStacji){
        List<StacjaMeteoSerializable> nowaLista = new ArrayList<>();
        for (StacjaMeteo stacja : staraListaStacji){
            nowaLista.add(new StacjaMeteoSerializable(stacja));
        }
        return nowaLista;
    }

    public static String gsonStacjeMeteo(List<StacjaMeteo> listaStacji){
        return gson.toJson(nowaListaStacji(listaStacji));
    }

    public static void serializacjaDoPlikuListyStacjiMeteo(String path, List<StacjaMeteo> listaStacji){
        String doSerializacji = gsonStacjeMeteo(listaStacji);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            bw.write(doSerializacji);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
