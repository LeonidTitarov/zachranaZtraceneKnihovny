package Command_Pattern;

import Hlavni_Herni_Tridy.Hra;
import Predmety.prenosnyPredmet;
import Predmety.MagickaSvice;
import Predmety.SvitkyPravdy;

public class PrikazPouzij implements Prikaz {
    private Hra hra;

    public PrikazPouzij(Hra hra) {
        this.hra = hra;
    }

    @Override
    public boolean proved(String[] parametry) {
        if (parametry.length == 0) {
            System.out.println("Co mám použít? Zadej název předmětu.");
            return true;
        }

        String nazevPredmetu = parametry[0];
        prenosnyPredmet predmet = hra.getInventar().najdiPredmet(nazevPredmetu);

        if (predmet == null) {
            System.out.println("Takový předmět nemáš v inventáři.");
            return true;
        }

        // Speciální chování podle typu předmětu
        if (predmet instanceof MagickaSvice) {
            ((MagickaSvice) predmet).rozsvit();
            ((MagickaSvice) predmet).odhalStopy();
        } else if (predmet instanceof SvitkyPravdy) {
            ((SvitkyPravdy) predmet).odhalSkryte();
        } else {
            predmet.pouzij();
        }

        System.out.println("Použil(a) jsi: " + predmet.getNazev());
        hra.getCasovySystem().odpocetTahu();

        return true;
    }

    @Override
    public String getNazev() {
        return "pouzij";
    }

    @Override
    public String getPopis() {
        return "pouzij [předmět] - použije předmět";
    }}