package Command_Pattern;

import Hlavni_Herni_Tridy.Hra;

public class PrikazProzkoumat implements Prikaz {
    private Hra hra;

    public PrikazProzkoumat(Hra hra) {
        this.hra = hra;
    }

    @Override
    public boolean proved(String[] parametry) {
        if (parametry.length > 0) {
            // Zkoumání konkrétního předmětu v místnosti
            String nazevPredmetu = parametry[0];
            hra.prozkoumejPredmet(nazevPredmetu);
        } else {
            // Zkoumání celé místnosti
            hra.prozkoumejMistnost();
        }

        hra.getCasovySystem().odpocetTahu();
        return true;
    }

    @Override
    public String getNazev() {
        return "prozkoumat";
    }

    @Override
    public String getPopis() {
        return "prozkoumat - prozkoumá aktuální místnost, prozkoumat [předmět] - prozkoumá konkrétní předmět";
    }
}