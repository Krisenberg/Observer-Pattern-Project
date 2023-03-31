package CSI;

import Aplikacja.Uzytkownik;
import InputStacjeMeteo.StacjeMeteoFromFile;
import InputStacjeMeteo.StacjeMeteoFromKeyboard;
import JSON.Deserializacja;
import JSON.Serializacja;
import Pogoda.OdczytyPogodowe;
import Pogoda.Pogoda;
import Pogoda.StacjaMeteo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSI {
    private List<Uzytkownik> uzytkownicy;
    private List<StacjaMeteo> stacjeMeteo;
    private boolean czyKontynuowac = true;
    private Thread watek;
    private Object semafor = new Object();

    public CSI(){
        uzytkownicy = new ArrayList<>();
        stacjeMeteo = new ArrayList<>();
        watek = new Thread(() -> run());
    }

    public List<StacjaMeteo> getStacjeMeteo() {
        return stacjeMeteo;
    }

    public Object getSemafor() {
        return semafor;
    }

    public void setStacjeMeteoForTest(List<StacjaMeteo> stacjeMeteo) {
        this.stacjeMeteo = stacjeMeteo;
    }

    public void run(){
        OdczytyPogodowe oP = new OdczytyPogodowe();
        while (czyKontynuowac){
            synchronized (semafor) {
                update(oP);
            }
            try{
                Thread.sleep(5000);
            } catch (InterruptedException e){
                System.out.println("Blad!");
                e.printStackTrace();
            }
        }
    }

    public void update(OdczytyPogodowe oP){
        for (StacjaMeteo stacja : stacjeMeteo){
            stacja.setPogoda(oP.nowaPogoda(stacja));
        }
        noweOdczyty();
    }


    //Lepiej jest uzytkownikowi wysylac od razu caly zestaw danych np w postaci ArrayListy, niz kazdorazowo
    //go pingowac o nowym odczycie

    public void noweOdczyty(){
        for (Uzytkownik u : uzytkownicy){
            for (StacjaMeteo stacja : stacjeMeteo){
                if (u.getSubskrybowaneStacje().contains(stacja)){
                    u.nowyOdczytStacji(stacja,stacja.getPogoda());
                }
            }
        }
    }

    public List<StacjaMeteo> kopiaSubskrybowanychStacji(List<StacjaMeteo> stareStacje){
        List<StacjaMeteo> noweStacje = new ArrayList<>();
        for (StacjaMeteo stacja : stareStacje){
            noweStacje.add(stacja);
        }
        return noweStacje;
    }

    public void korektaSubskrybowanychStacji(){
        synchronized (semafor){
            for (Uzytkownik u : uzytkownicy){
                List<StacjaMeteo> subskrybowaneStacje = kopiaSubskrybowanychStacji(u.getSubskrybowaneStacje());
                for (StacjaMeteo stacja : subskrybowaneStacje){
                    if (!stacjeMeteo.contains(stacja)){
                        u.usunStacje(stacja);
                    }
                }
            }
        }
    }

    public void register(Uzytkownik u){
        synchronized (semafor){
            uzytkownicy.add(u);
        }
    }

    public void deregister(Uzytkownik u){
        synchronized (semafor){
            uzytkownicy.remove(u);
        }
    }

    public void startWatek(){
        watek.start();
    }

    public void stopWatek(){
        czyKontynuowac = false;
    }

    public void poczekajWatek(){
        try{
            watek.join();
        } catch (InterruptedException e){
            System.out.println("Blad!");
            e.printStackTrace();
        }
    }

    public void serializacjaDanychUzytkownikaDoPliku(String path, Map<StacjaMeteo,List<Pogoda>> dane){
        synchronized (semafor){
            Serializacja.serializacjaDoPlikuHistoriiOdczytow(path,dane);
        }
    }

    public void serializacjaStacjiMeteoDoPliku(String path){
        synchronized (semafor){
            Serializacja.serializacjaDoPlikuListyStacjiMeteo(path,stacjeMeteo);
        }
    }

    public void wczytajStacjeMeteoZPliku(String path){
        synchronized (semafor){
            stacjeMeteo = StacjeMeteoFromFile.listaStacjiMeteo(path);
        }
    }

    public void wczytajStacjeMeteoZKlawiatury(){
        synchronized (semafor){
            StacjeMeteoFromKeyboard sMFK = new StacjeMeteoFromKeyboard();
            stacjeMeteo = sMFK.listaStacjiMeteo();
        }
    }

    public void wczytajStacjeMeteoJSON(String path){
        synchronized (semafor){
            stacjeMeteo = Deserializacja.listaStacjiMeteo(path);
        }
    }
}
