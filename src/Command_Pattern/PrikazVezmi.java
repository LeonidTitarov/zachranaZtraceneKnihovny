package Command_Pattern;

import Hlavni_Herni_Tridy.Hra;
import Predmety.Predmet;
import Predmety.prenosnyPredmet;

public class PrikazVezmi implements Prikaz {
    private Hra hra;

    public PrikazVezmi(Hra hra) {
        this.hra = hra;
    }

    @Override
    public boolean proved(String[] parametry) {
        if (parametry.length == 0) {
            System.out.println("Co mám vzít? Zadej název předmětu.");
            return true;
        }

        String nazevPredmetu = parametry[0];
        Predmet predmet = hra.najdiPredmetVMistnosti(nazevPredmetu);

        if (predmet == null) {
            System.out.println("Takový předmět tady není.");
            return true;
        }

        if (!(predmet instanceof prenosnyPredmet)) {
            System.out.println("Tento předmět nemůžeš vzít.");
            return true;
        }

        if (hra.getInventar().jePlny()) {
            System.out.println("Tvůj inventář je plný. Musíš něco položit.");
            return true;
        }

        hra.getInventar().pridejPredmet((prenosnyPredmet) predmet);
        hra.odstranPredmetZMistnosti(nazevPredmetu);
        System.out.println("Vzal(a) jsi: " + predmet.getNazev());
        hra.getCasovySystem().odpocetTahu();

        return true;
    }

    @Override
    public String getNazev() {
        return "vezmi";
    }

    @Override
    public String getPopis() {
        return "vezmi [předmět] - sebere předmět z místnosti do inventáře";
    }
}