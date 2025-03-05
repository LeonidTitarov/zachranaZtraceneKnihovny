package Hlavni_Herni_Tridy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Třída reprezentující jednu místnost/lokaci ve světě hry.
 */
class Mistnost {
    private String nazev;
    private String popis;
    private Map<String, Mistnost> vychody;

    /**
     * Konstruktor místnosti.
     * @param nazev Jedinečný název místnosti
     * @param popis Textový popis místnosti
     */
    public Mistnost(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        this.vychody = new HashMap<>();
    }

    /**
     * Přidání východu/sousední místnosti.
     * @param smer Směr, kterým se lze dostat do sousední místnosti
     * @param sousedniMistnost Sousední místnost
     */
    public void pridejVychod(String smer, Hlavni_Herni_Tridy.Mistnost sousedniMistnost) {
        vychody.put(smer.toLowerCase(), sousedniMistnost);
    }

    /**
     * Získání sousední místnosti v zadaném směru.
     * @param smer Směr, kterým se chceme vydat
     * @return Sousední místnost nebo null, pokud v daném směru žádná místnost není
     */
    public Hlavni_Herni_Tridy.Mistnost vratSousedniMistnost(String smer) {
        return vychody.get(smer.toLowerCase());
    }

    /**
     * Vrátí název místnosti.
     * @return Název místnosti
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Vrátí popis místnosti.
     * @return Popis místnosti
     */
    public String getPopis() {
        return popis;
    }

    /**
     * Nastaví popis místnosti.
     * @param popis Nový popis místnosti
     */
    public void setPopis(String popis) {
        this.popis = popis;
    }

    /**
     * Vrátí seznam všech východů z místnosti.
     * @return Mapa směr -> místnost
     */
    public Map<String, Hlavni_Herni_Tridy.Mistnost> getVychody() {
        return new HashMap<>(vychody);
    }

    /**
     * Vrátí textový seznam směrů, kudy lze odejít.
     * @return Textový seznam směrů
     */

    public String seznamVychodu() {
        StringBuilder sb = new StringBuilder();
        sb.append("Východy: ");

        if (vychody.isEmpty()) {
            sb.append("žádné");
        } else {
            for (String smer : vychody.keySet()) {
                sb.append(smer).append(" ");
            }
        }

        return sb.toString();
    }
}
