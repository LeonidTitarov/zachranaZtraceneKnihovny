import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Třída reprezentující herní svět textové hry "Záchrana Ztracené Knihovny".
 * Obsahuje místnosti a jejich propojení.
 */

public class Svet {
    private Map<String, Mistnost> mistnosti;
    private Mistnost aktualniMistnost;

    public Svet() {
        mistnosti = new HashMap<>();
    }

    /**
     * Načtení světa ze souboru CSV.
     * @param souborCesta Cesta k souboru CSV
     * @return true pokud se načtení povedlo, jinak false
     */

    public boolean nactiSvet(String souborCesta) {
        try (BufferedReader br = new BufferedReader(new FileReader(souborCesta))) {
            String radek;
            while ((radek = br.readLine()) != null) {
                // Přeskočíme komentáře nebo prázdné řádky
                if (radek.trim().isEmpty() || radek.startsWith("#")) {
                    continue;
                }

                String[] casti = radek.split(";");

                // Očekávaný formát: nazevMistnosti;popis;sever;jih;vychod;zapad;nahoru;dolu
                if (casti.length >= 8) {
                    String nazev = casti[0].trim();
                    String popis = casti[1].trim();

                    // Vytvoříme místnost, pokud ještě neexistuje
                    Mistnost mistnost = mistnosti.getOrDefault(nazev, new Mistnost(nazev, popis));
                    mistnosti.put(nazev, mistnost);

                    // Nastavíme výchozí místnost, pokud ještě nebyla nastavena
                    if (aktualniMistnost == null) {
                        aktualniMistnost = mistnost;
                    }

                    // Projdeme všechny směry
                    String[] smery = {"sever", "jih", "vychod", "zapad", "nahoru", "dolu"};
                    for (int i = 0; i < 6; i++) {
                        String sousedNazev = casti[i + 2].trim();
                        if (!sousedNazev.isEmpty() && !sousedNazev.equals("-")) {
                            // Vytvoříme sousední místnost, pokud ještě neexistuje
                            // (doplníme popis později)
                            Mistnost soused = mistnosti.getOrDefault(sousedNazev,
                                    new Mistnost(sousedNazev, ""));
                            mistnosti.put(sousedNazev, soused);
                            mistnost.pridejVychod(smery[i], soused);
                        }
                    }
                }
            }
            return true;
        } catch (IOException e) {
            System.err.println("Chyba při načítání souboru: " + e.getMessage());
            return false;
        }
    }

    /**
     * Přesun do jiné místnosti.
     * @param smer Směr, kterým se chceme přesunout
     * @return Nová místnost nebo null, pokud se přesun nezdařil
     */
    public Mistnost jdiSmerem(String smer) {
        if (aktualniMistnost == null) {
            return null;
        }

        Mistnost novaMistnost = aktualniMistnost.vratSousedniMistnost(smer);
        if (novaMistnost != null) {
            aktualniMistnost = novaMistnost;
        }
        return aktualniMistnost;
    }

    /**
     * Vrátí aktuální místnost.
     * @return Aktuální místnost
     */
    public Mistnost getAktualniMistnost() {
        return aktualniMistnost;
    }

    /**
     * Nastaví aktuální místnost podle názvu.
     * @param nazevMistnosti Název místnosti
     * @return true pokud se nastavení povedlo, jinak false
     */
    public boolean nastavAktualniMistnost(String nazevMistnosti) {
        if (mistnosti.containsKey(nazevMistnosti)) {
            aktualniMistnost = mistnosti.get(nazevMistnosti);
            return true;
        }
        return false;
    }

    /**
     * Vrátí místnost podle názvu.
     * @param nazev Název místnosti
     * @return Místnost nebo null, pokud místnost neexistuje
     */
    public Mistnost getMistnost(String nazev) {
        return mistnosti.get(nazev);
    }

    /**
     * Vrátí počet místností ve světě.
     * @return Počet místností
     */
    public int getPocetMistnosti() {
        return mistnosti.size();
    }
}

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
    public void pridejVychod(String smer, Mistnost sousedniMistnost) {
        vychody.put(smer.toLowerCase(), sousedniMistnost);
    }

    /**
     * Získání sousední místnosti v zadaném směru.
     * @param smer Směr, kterým se chceme vydat
     * @return Sousední místnost nebo null, pokud v daném směru žádná místnost není
     */
    public Mistnost vratSousedniMistnost(String smer) {
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
    public Map<String, Mistnost> getVychody() {
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
