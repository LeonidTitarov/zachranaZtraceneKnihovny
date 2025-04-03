package Herni_Mechaniky;

import Predmety.prenosnyPredmet;
import java.util.ArrayList;
import java.util.List;

public class Inventar {
    private final int MAX_KAPACITA = 4; // Podle zadání
    private List<prenosnyPredmet> predmety;

    public Inventar() {
        this.predmety = new ArrayList<>();
    }

    public void pridejPredmet(prenosnyPredmet predmet) {
        if (!jePlny()) {
            predmety.add(predmet);
            System.out.println("Přidal(a) jsi " + predmet.getNazev() + " do inventáře.");
        } else {
            System.out.println("Inventář je plný. Musíš něco položit.");
        }
    }

    public void odeberPredmet(prenosnyPredmet predmet) {
        predmety.remove(predmet);
        System.out.println("Odebral(a) jsi " + predmet.getNazev() + " z inventáře.");
    }

    public boolean obsahujePredmet(String nazevPredmetu) {
        return najdiPredmet(nazevPredmetu) != null;
    }

    // tady jsem to zmenil SAMMM na String
    public String zobrazInventar() {
        if (predmety.isEmpty()) {
            System.out.println("Tvůj inventář je prázdný.");
            return null;
        }

        System.out.println("Obsah inventáře:");
        for (prenosnyPredmet predmet : predmety) {
            System.out.println("- " + predmet.getNazev());
        }
        System.out.println("Celkem předmětů: " + predmety.size() + "/" + MAX_KAPACITA);
        return null;
    }

    public prenosnyPredmet najdiPredmet(String nazevPredmetu) {
        for (prenosnyPredmet predmet : predmety) {
            if (predmet.getNazev().equalsIgnoreCase(nazevPredmetu)) {
                return predmet;
            }
        }
        return null;
    }

    public boolean jePlny() {
        return predmety.size() >= MAX_KAPACITA;
    }
}