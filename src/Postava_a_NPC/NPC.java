package Postava_a_NPC;

import java.util.ArrayList;
import java.util.List;

public class NPC extends Postava {
    private String jmeno;
    private String popis;
    private List<String> dialogy;

    public NPC() {
        super();
        this.dialogy = new ArrayList<>();
    }

    public NPC(String jmeno, String popis) {
        super(jmeno, popis);
        this.jmeno = jmeno;
        this.popis = popis;
        this.dialogy = new ArrayList<>();
    }

    public void zahajDialog() {
        if (dialogy.isEmpty()) {
            System.out.println("Tato postava nemá co říct.");
            return;
        }

        System.out.println(dialogy.get(0));
    }

    public void dejNapovedu() {
        System.out.println(jmeno + " říká: \"Měl bys prozkoumat knihovnu důkladněji.\"");
    }

    public void pridejDialog(String dialog) {
        dialogy.add(dialog);
    }

    @Override
    public String getJmeno() {
        return jmeno;
    }

    @Override
    public String getPopis() {
        return popis;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public boolean getUvodni_dialog() {
        // tady mi to chybi dodelat
    }

    public void zobrazMoznostiDialogu() {
    }

    public String zpracujOdpoved(int cisloVolby) {
        return null;
    }
}