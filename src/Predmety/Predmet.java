package Predmety;

public abstract class Predmet {
    protected String nazev;
    protected String popis;

    public Predmet() {
        this.nazev = "";
        this.popis = "";
    }

    public Predmet(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
    }

    public String getNazev() {
        return nazev;
    }

    public String getPopis() {
        return popis;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }
}