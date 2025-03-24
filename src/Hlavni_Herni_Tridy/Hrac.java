

        package Hlavni_Herni_Tridy;

import Herni_Mechaniky.Inventar;
import Predmety.Predmet;
import Predmety.prenosnyPredmet;

public class Hrac {
    private String jmeno;
    private Inventar inventar;
    private Svet svet;
    private Hra hra;

    public Hrac(String jmeno, Svet svet, Hra hra) {
        this.jmeno = jmeno;
        this.inventar = new Inventar();
        this.svet = svet;
        this.hra = hra;
    }

    /**
     * Pohyb hráče v určeném směru
     * @param smer Směr pohybu (sever, jih, vychod, zapad, nahoru, dolu)
     * @return Informace o výsledku pohybu
     */
    public String pohyb(String smer) {
        Mistnost aktualniMistnost = svet.getAktualniMistnost();
        Mistnost novaMistnost = svet.jdiSmerem(smer);

        if (novaMistnost == aktualniMistnost) {
            return "Tímto směrem nemůžeš jít!";
        }

        // Odpočítáme tah
        hra.getCasovySystem().odpocetTahu();

        StringBuilder sb = new StringBuilder();
        sb.append("Přesunul(a) jsi se do: ").append(novaMistnost.getNazev()).append('\n');
        sb.append(novaMistnost.getPopis()).append('\n');
        sb.append(novaMistnost.seznamVychodu());

        return sb.toString();
    }

    /**
     * Zvednutí předmětu z aktuální místnosti
     * @param nazevPredmetu Název předmětu k sebrání
     * @return Informace o výsledku akce
     */
    public String seberPredmet(String nazevPredmetu) {
        if (inventar.jePlny()) {
            return "Tvůj inventář je plný!";
        }

        Predmet predmet = hra.najdiPredmetVMistnosti(nazevPredmetu);

        if (predmet == null) {
            return "Předmět '" + nazevPredmetu + "' zde není!";
        }

        if (!(predmet instanceof prenosnyPredmet)) {
            return "Tento předmět nemůžeš sebrat!";
        }

        prenosnyPredmet prenosPredmet = (prenosnyPredmet) predmet;
        inventar.pridejPredmet(prenosPredmet);
        hra.odstranPredmetZMistnosti(nazevPredmetu);

        // Odpočítáme tah
        hra.getCasovySystem().odpocetTahu();

        return "Sebral(a) jsi: " + nazevPredmetu;
    }

    /**
     * Použití předmětu z inventáře
     * @param nazevPredmetu Název předmětu k použití
     * @return Informace o výsledku akce
     */
    public String pouzijPredmet(String nazevPredmetu) {
        prenosnyPredmet predmet = inventar.najdiPredmet(nazevPredmetu);

        if (predmet == null) {
            return "Nemáš " + nazevPredmetu + " v inventáři!";
        }

        predmet.pouzij();

        // Odpočítáme tah
        hra.getCasovySystem().odpocetTahu();

        return "Použil(a) jsi: " + nazevPredmetu;
    }

    /**
     * Zobrazení obsahu inventáře
     * @return Textový výpis inventáře
     */
    public String zobrazInventar() {
        return inventar.zobrazInventar();// tady musim jeste dodelat
    }

    /**
     * Prozkoumání předmětu v inventáři
     * @param nazevPredmetu Název předmětu k prozkoumání
     * @return Informace o předmětu nebo chybová zpráva
     */
    public String prozkoumejPredmet(String nazevPredmetu) {
        prenosnyPredmet predmet = inventar.najdiPredmet(nazevPredmetu);

        if (predmet != null) {
            return predmet.getPopis();
        }

        // Zkusíme najít předmět v místnosti
        Predmet predmetVMistnosti = hra.najdiPredmetVMistnosti(nazevPredmetu);
        if (predmetVMistnosti != null) {
            return predmetVMistnosti.getPopis();
        }

        return "Předmět '" + nazevPredmetu + "' není v inventáři ani v místnosti!";
    }

    /**
     * Zahájení dialogu s postavou
     * @param jmenoNPC Jméno postavy
     * @return Výsledek dialogu
     */
    public String mluvSNPC(String jmenoNPC) {
        // Hledáme NPC v aktuální místnosti
        Postava_a_NPC.NPC npc = hra.najdiNPCVMistnosti(jmenoNPC);

        if (npc == null) {
            return "Postava '" + jmenoNPC + "' zde není!";
        }

        // Odpočítáme tah
        hra.getCasovySystem().odpocetTahu();

        // Zahájíme dialog
        hra.getDialogSystem().zahajDialog(npc);

        return "Mluvíš s: " + jmenoNPC;
    }

    /**
     * Zahození předmětu z inventáře
     * @param nazevPredmetu Název předmětu k zahození
     * @return Informace o výsledku akce
     */
    public String zahodPredmet(String nazevPredmetu) {
        prenosnyPredmet predmet = inventar.najdiPredmet(nazevPredmetu);

        if (predmet == null) {
            return "Nemáš " + nazevPredmetu + " v inventáři!";
        }

        inventar.odeberPredmet(predmet);
        hra.pridejPredmetDoMistnosti(predmet);

        // Odpočítáme tah
        hra.getCasovySystem().odpocetTahu();

        return "Zahodil(a) jsi: " + nazevPredmetu;
    }

    /**
     * Prozkoumání aktuální místnosti
     * @return Popis místnosti
     */
    public String prozkoumejMistnost() {
        // Odpočítáme tah
        hra.getCasovySystem().odpocetTahu();
        hra.prozkoumejMistnost();

        return svet.getAktualniMistnost().getPopis() + "\n" + svet.getAktualniMistnost().seznamVychodu();
    }

    /**
     * Zobrazení nápovědy
     * @return Text nápovědy
     */
    public String zobrazNapovedu() {
        return hra.getNapovedaSystem().zobrazNapovedu();
    }

    public String getJmeno() {
        return jmeno;
    }

    public Inventar getInventar() {
        return inventar;
    }
}