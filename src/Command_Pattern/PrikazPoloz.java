package Command_Pattern;

import Hlavni_Herni_Tridy.Hra;
import Predmety.prenosnyPredmet;

public class PrikazPoloz implements Prikaz {
    private Hra hra;

    public PrikazPoloz(Hra hra) {
        this.hra = hra;
    }

    @Override
    public boolean proved(String[] parametry) {
        if (parametry.length == 0) {
            System.out.println("Co mám položit? Zadej název předmětu.");
            return true;
        }

        String nazevPredmetu = parametry[0];
        prenosnyPredmet predmet = hra.getInventar().najdiPredmet(nazevPredmetu);

        if (predmet == null) {
            System.out.println("Takový předmět nemáš v inventáři.");
            return true;
        }

        hra.getInventar().odeberPredmet(predmet);
        hra.pridejPredmetDoMistnosti(predmet);
        System.out.println("Položil(a) jsi: " + predmet.getNazev());

        // Speciální případ pro vrácení knihy na podstavec
        // pokud se hra dohraje na konci se vypise vyhodnot hru
        if (nazevPredmetu.equalsIgnoreCase("kniha") &&
                hra.getSvet().getAktualniMistnost().getNazev().equals("HLAVNI_ARCHIV")) {
            hra.vyhodnotHru();
        }

        hra.getCasovySystem().odpocetTahu();
        return true;
    }

    @Override
    public String getNazev() {
        return "poloz";
    }

    @Override
    public String getPopis() {
        return "poloz [předmět] - položí předmět z inventáře do místnosti";
    }
}