package Postava_a_NPC;

public class Golem extends NPC {
    private boolean aktivni;

    public Golem() {
        super("Golem strážce", "Obrovská kamenná socha, která se pohybuje a hlídá vstup.");
        this.aktivni = true;
        pridejDialog("*Dunivým hlasem* STOP! Nikdo nesmí projít bez oprávnění.");
    }

    public void bran() {
        if (aktivni) {
            System.out.println("Golem ti blokuje cestu. Musíš najít způsob, jak ho obejít nebo deaktivovat.");
        } else {
            System.out.println("Golem stojí nehybně a nechává tě projít.");
        }
    }

    public void kontroluj() {
        if (aktivni) {
            System.out.println("Golem tě pozorně sleduje. Vypadá, že reaguje na magické předměty.");
        }
    }

    public void deaktivuj() {
        this.aktivni = false;
        System.out.println("Golem ztuhne a přestane se hýbat.");
    }

    public boolean isAktivni() {
        return aktivni;
    }

    @Override
    public void dejNapovedu() {
        System.out.println("Golem říká: \"Reagujeme na specifická zaklínadla. Možná některé z nich najdeš v knihách.\"");
    }
}