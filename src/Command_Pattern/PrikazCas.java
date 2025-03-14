package Command_Pattern;

import Hlavni_Herni_Tridy.Hra;

public class PrikazCas implements Prikaz {
    private Hra hra;

    public PrikazCas(Hra hra) {
        this.hra = hra;
    }

    @Override
    public boolean proved(String[] parametry) {
        int zbyvajiciTahy = hra.getCasovySystem().getZbyvajiciTahy();
        System.out.println("Zbývá ti " + zbyvajiciTahy + " tahů.");

        if (zbyvajiciTahy <= 5) {
            System.out.println("Pospěš si! Knihovna se brzy rozpadne.");
        }

        return true;
    }

    @Override
    public String getNazev() {
        return "cas";
    }

    @Override
    public String getPopis() {
        return "cas - zobrazí zbývající počet tahů";
    }
}