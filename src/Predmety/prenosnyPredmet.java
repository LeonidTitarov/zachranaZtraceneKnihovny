package Predmety;

public class prenosnyPredmet extends Predmet {

    public prenosnyPredmet() {
        super();
    }

    public prenosnyPredmet(String nazev, String popis) {
        super(nazev, popis);
    }

    public void pouzij() {
        System.out.println("Použil(a) jsi " + nazev + ", ale nic zvláštního se nestalo.");
    }
}