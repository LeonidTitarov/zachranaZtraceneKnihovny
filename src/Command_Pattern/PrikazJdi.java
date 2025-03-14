package Command_Pattern;

import Hlavni_Herni_Tridy.Hra;

import Hlavni_Herni_Tridy.Mistnost;

public class PrikazJdi implements Prikaz {
    private Hra hra;

    public PrikazJdi(Hra hra) {
        this.hra = hra;
    }

    @Override
    public boolean proved(String[] parametry) {
        if (parametry.length == 0) {
            System.out.println("Kam mám jít? Musíš zadat směr.");
            return true;
        }

        String smer = parametry[0];
        Mistnost novaMistnost = hra.getSvet().jdiSmerem(smer);

        if (novaMistnost == hra.getSvet().getAktualniMistnost()) {
            System.out.println("Tímto směrem nemůžeš jít!");
        } else {
            hra.getCasovySystem().odpocetTahu();
            System.out.println("Přesunul(a) jsi se do: " + novaMistnost.getNazev());
            System.out.println(novaMistnost.getPopis());
            System.out.println(novaMistnost.seznamVychodu());
        }

        return true;
    }

    @Override
    public String getNazev() {
        return "jdi";
    }

    @Override
    public String getPopis() {
        return "jdi [směr] - přesun do jiné místnosti (sever, jih, vychod, zapad, nahoru, dolu)";
    }
}