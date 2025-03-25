package Postava_a_NPC;

public abstract class Postava {
    private String jmeno;
    private String popis;

    public Postava() {
        this.jmeno = "";
        this.popis = "";
    }

    public Postava(String jmeno, String popis) {
        this.jmeno = jmeno;
        this.popis = popis;
    }

    public abstract String getJmeno();

    public abstract String getPopis();
}
