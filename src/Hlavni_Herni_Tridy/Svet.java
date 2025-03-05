package Hlavni_Herni_Tridy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Třída reprezentující herní svět textové hry "Záchrana Ztracené Knihovny".
 * Obsahuje místnosti a jejich propojení.
 */

public class Svet {
    private Map<String, Hlavni_Herni_Tridy.Mistnost> mistnosti;
    private Hlavni_Herni_Tridy.Mistnost aktualniMistnost;

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
                    Hlavni_Herni_Tridy.Mistnost mistnost = mistnosti.getOrDefault(nazev, new Hlavni_Herni_Tridy.Mistnost(nazev, popis));
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
                            Hlavni_Herni_Tridy.Mistnost soused = mistnosti.getOrDefault(sousedNazev,
                                    new Hlavni_Herni_Tridy.Mistnost(sousedNazev, ""));
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
    public Hlavni_Herni_Tridy.Mistnost jdiSmerem(String smer) {
        if (aktualniMistnost == null) {
            return null;
        }

        Hlavni_Herni_Tridy.Mistnost novaMistnost = aktualniMistnost.vratSousedniMistnost(smer);
        if (novaMistnost != null) {
            aktualniMistnost = novaMistnost;
        }
        return aktualniMistnost;
    }

    /**
     * Vrátí aktuální místnost.
     * @return Aktuální místnost
     */
    public Hlavni_Herni_Tridy.Mistnost getAktualniMistnost() {
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
    public Hlavni_Herni_Tridy.Mistnost getMistnost(String nazev) {
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

