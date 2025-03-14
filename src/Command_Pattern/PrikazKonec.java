package Command_Pattern;

import Hlavni_Herni_Tridy.Hra;

public class PrikazKonec implements Prikaz {
    private Hra hra;

    public PrikazKonec(Hra hra) {
        this.hra = hra;
    }

    @Override
    public boolean proved(String[] parametry) {
        if (parametry.length > 0) {
            System.out.println("Konec co? Nechápu, co mám ukončit.");
            return true;
        }

        System.out.println("Děkuji za hru. Ahoj!");
        return false;  // signál pro ukončení hry
    }

    @Override
    public String getNazev() {
        return "konec";
    }

    @Override
    public String getPopis() {
        return "konec - ukončení hry";
    }
}