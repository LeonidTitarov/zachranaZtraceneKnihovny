package Command_Pattern;

import Hlavni_Herni_Tridy.Hra;
import java.util.HashMap;
import java.util.Map;

public class SeznamPrikazu {
    private Map<String, Prikaz> prikazy;
    private Hra hra;

    public SeznamPrikazu(Hra hra) {
        this.hra = hra;
        prikazy = new HashMap<>();

        // Inicializace příkazů
        prikazy.put("jdi", new PrikazJdi(hra));
        prikazy.put("konec", new PrikazKonec(hra));
        prikazy.put("napoveda", new PrikazNapoveda(hra));
        prikazy.put("mluv", new PrikazMluv(hra));
        prikazy.put("inventar", new PrikazInventar(hra));
        prikazy.put("vezmi", new PrikazVezmi(hra));
        prikazy.put("poloz", new PrikazPoloz(hra));
        prikazy.put("pouzij", new PrikazPouzij(hra));
        prikazy.put("prozkoumat", new PrikazProzkoumat(hra));
        prikazy.put("cas", new PrikazCas(hra));
    }

    public Prikaz najdiPrikaz(String slovo) {
        return prikazy.get(slovo);
    }

    public boolean jePlatnyPrikaz(String slovo) {
        return prikazy.containsKey(slovo);
    }

    public void vypisSeznamPrikazu() {
        for (String prikaz : prikazy.keySet()) {
            System.out.print(prikaz + " ");
        }
        System.out.println();
    }
}
