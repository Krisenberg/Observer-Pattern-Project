package Aplikacja;

import CSI.CSI;
import CSI.KonfiguracjaCSI;
import Menu.Menu;
import Pogoda.Pogoda;
import Pogoda.StacjaMeteo;

import java.util.*;

public class Aplikacja {
    private Map<Uzytkownik, String> listaKont;
    private Set<String> listaLoginow;
    private CSI csi;
    private final String hasloAdmina = "studentpwr";
    private KonfiguracjaCSI konfig;

    public Aplikacja(){
        listaKont = new HashMap<>();
        listaLoginow = new HashSet<>();
        csi = new CSI();
        konfig = new KonfiguracjaCSI(csi);
    }

    public List<StacjaMeteo> getStacjeMeteo() {
        return csi.getStacjeMeteo();
    }

    public Object getSemafor(){
        return csi.getSemafor();
    }

    public String getHasloAdmina(){return hasloAdmina;}

    public int utworzNoweKonto(String login, String haslo){
        if (!listaLoginow.contains(login)){
            Uzytkownik u = new Uzytkownik(login);
            listaKont.put(u,haslo);
            listaLoginow.add(login);
            csi.register(u);
            return 1;
        }
        return -1;
    }

    public Map<Integer,Uzytkownik> logowanie(String login, String haslo){
        Map<Integer,Uzytkownik> map = new HashMap<>();
        if (listaLoginow.contains(login)){
            for (Uzytkownik user : listaKont.keySet()){
                if (login.equals(user.getNazwaUzytkownika())
                    && haslo.equals(listaKont.get(user))){
                    map.put(1,user);
                    return map;
                }
            }
        }
        map.put(0,new Uzytkownik("Default"));
        return map;
    }

    public void usunUzytkownika(Uzytkownik u){
        listaKont.remove(u);
        listaLoginow.remove(u);
        csi.deregister(u);
    }

    public void serializacjaHistoriiOdczytow(String path, Map<StacjaMeteo,List<Pogoda>> dane){
        csi.serializacjaDanychUzytkownikaDoPliku(path,dane);
    }

    public void serializacjaStacjiMeteo(String path){
        csi.serializacjaStacjiMeteoDoPliku(path);
    }

    public void ponownaKonfiguracjaCSI(){
        konfig.konfiguracja(1);
        csi.korektaSubskrybowanychStacji();
    }

    public void start(){
        konfig.konfiguracja(0);
        csi.startWatek();

        Menu menu = new Menu(this);
        menu.uruchomAplikacje();
        csi.stopWatek();
        csi.poczekajWatek();

        System.out.println("KONIEC");
    }

}
