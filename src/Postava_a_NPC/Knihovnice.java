package Postava_a_NPC;

public class Knihovnice extends NPC {
    private boolean dalKlic;

    public Knihovnice() {
        super("Knihovnice Helena", "Starší žena s brýlemi a přísným výrazem.");
        this.dalKlic = false;
        pridejDialog("Dobrý den, Alexi. Co potřebuješ v této těžké chvíli?");
        pridejDialog("Kniha Prvního Kouzla? Ano, její zmizení je katastrofa. Musíme ji rychle najít!");
    }

    public void poskytniKlic() {
        if (!dalKlic) {
            System.out.println("Knihovnice ti podává malý stříbrný klíč. \"Tento klíč otevírá dveře do věže. Buď opatrný.\"");
            dalKlic = true;
        } else {
            System.out.println("Už jsem ti dala klíč od věže.");
        }
    }

    public void dejRadu() {
        System.out.println("Knihovnice říká: \"V této knihovně je mnoho tajemství. Zkus prozkoumat východní křídlo.\"");
    }

    public boolean isDalKlic() {
        return dalKlic;
    }

    @Override
    public void dejNapovedu() {
        System.out.println("Knihovnice říká: \"Pamatuji si, že zlý mág se velmi zajímal o astronomii. Možná stojí za to podívat se do věže.\"");
    }
}