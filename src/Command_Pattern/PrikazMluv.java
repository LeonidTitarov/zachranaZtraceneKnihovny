package Command_Pattern;

import Hlavni_Herni_Tridy.Hra;
import Postava_a_NPC.NPC;
import Herni_Mechaniky.DialogSystem;

public class PrikazMluv implements Prikaz {
    private Hra hra;

    public PrikazMluv(Hra hra) {
        this.hra = hra;
    }

    @Override
    public boolean proved(String[] parametry) {
        if (parametry.length == 0) {
            System.out.println("S kým mám mluvit? Zadej jméno postavy.");
            return true;
        }

        String jmenoNPC = parametry[0];
        NPC postava = hra.najdiNPCVMistnosti(jmenoNPC);

        if (postava != null) {
            System.out.println("Začínáš rozhovor s: " + postava.getJmeno());
            hra.getDialogSystem().zahajDialog(postava);
            hra.getCasovySystem().odpocetTahu();
        } else {
            System.out.println("Taková postava tady není.");
        }

        return true;
    }

    @Override
    public String getNazev() {
        return "mluv";
    }

    @Override
    public String getPopis() {
        return "mluv [postava] - zahájí rozhovor s postavou";
    }
}