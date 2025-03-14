package Command_Pattern;

import Hlavni_Herni_Tridy.Hra;

public class PrikazInventar implements Prikaz {
    private Hra hra;

    public PrikazInventar(Hra hra) {
        this.hra = hra;
    }

    @Override
    public boolean proved(String[] parametry) {
        hra.getInventar().zobrazInventar();
        return true;
    }

    @Override
    public String getNazev() {
        return "inventar";
    }

    @Override
    public String getPopis() {
        return "inventar - zobrazí obsah inventáře";
    }
}