package Command_Pattern;

import Hlavni_Herni_Tridy.Hra;

public class PrikazNapoveda implements Prikaz {
    private Hra hra;

    public PrikazNapoveda(Hra hra) {
        this.hra = hra;
    }

    @Override
    public boolean proved(String[] parametry) {
        System.out.println("Jsi Alex, mladý učeň v Magické knihovně. Někdo ukradl Knihu Prvního Kouzla.");
        System.out.println("Tvým úkolem je ji najít a vrátit na místo, než knihovna přijde o svou moc.");
        System.out.println();
        System.out.println("Můžeš používat tyto příkazy:");

        hra.getSeznamPrikazu().vypisSeznamPrikazu();

        if (parametry.length > 0 && parametry[0].equals("tip")) {
            hra.getNapovedaSystem().poskytniTip();
        } else {
            hra.getNapovedaSystem().zobrazNapovedu();
        }

        return true;
    }

    @Override
    public String getNazev() {
        return "napoveda";
    }

    @Override
    public String getPopis() {
        return "napoveda - zobrazí nápovědu ke hře, napoveda tip - poskytne konkrétní tip";
    }
}