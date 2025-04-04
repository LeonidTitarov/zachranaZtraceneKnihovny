package Herni_Mechaniky;

public class CasovySystem {
    private int zbyvajiciTahy;
    private boolean casVyprsel;
    private static final int VYCHOZI_POCET_TAHU = 30;

    public CasovySystem() {
        this.zbyvajiciTahy = VYCHOZI_POCET_TAHU; // Dle zadání je 30 tahů
        this.casVyprsel = false;
    }

    /**
     * Alternativní konstruktor s vlastním počtem tahů
     * @param pocetTahu Počáteční počet tahů
     */
    public CasovySystem(int pocetTahu) {
        this.zbyvajiciTahy = pocetTahu;
        this.casVyprsel = false;
    }

    /**
     * Odpočet jednoho tahu
     */
    public void odpocetTahu() {
        zbyvajiciTahy--;
        // po kazdem tahu se to logicky bude odecitat

        if (zbyvajiciTahy <= 0) {
            casVyprsel = true;
            System.out.println("Čas vypršel! Knihy se začínají rozpadat...");
        } else if (zbyvajiciTahy <= 5) {
            System.out.println("Pozor! Zbývá ti jen " + zbyvajiciTahy + " tahů.");
        }
    }

    /**
     * Přidání extra tahů jako bonus
     * @param pocetTahu Počet tahů k přidání
     */
    public void pridejTahy(int pocetTahu) {
        if (pocetTahu > 0) {
            zbyvajiciTahy += pocetTahu;
            System.out.println("Získal(a) jsi " + pocetTahu + " extra tahů! Zbývá ti " + zbyvajiciTahy + " tahů.");
        }
    }

    /**
     * Kontrola, zda čas vypršel
     * @return true pokud čas vypršel, jinak false
     */
    public boolean isCasVyprsel() {
        return casVyprsel;
    }

    /**
     * Získání zbývajícího počtu tahů
     * @return Zbývající počet tahů
     */
    public int getZbyvajiciTahy() {
        return zbyvajiciTahy;
    }

    /**
     * Resetování časového systému
     */
    public void reset() {
        this.zbyvajiciTahy = VYCHOZI_POCET_TAHU;
        this.casVyprsel = false;
    }
}