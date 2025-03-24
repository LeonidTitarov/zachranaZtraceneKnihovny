package Herni_Mechaniky;

import Hlavni_Herni_Tridy.Hra;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NapovedaSystem {
    private List<String> napovedy;
    private List<String> tipy;
    private Random random;

    public NapovedaSystem(Hra hra) {
        this.napovedy = new ArrayList<>();
        this.tipy = new ArrayList<>();
        this.random = new Random();

        // Přidání základních nápověd
        napovedy.add("Používej příkazy jako 'jdi', 'seber', 'použij', 'prozkoumej', 'inventář', 'mluv'.");
        napovedy.add("Máš omezený počet tahů, než se knihy začnou rozpadat. Plánuj své kroky pečlivě.");
        napovedy.add("Magické předměty ti mohou pomoci odhalit skryté věci v knihovně.");
        napovedy.add("Postavy v knihovně ti mohou poskytnout cenné rady a předměty.");
        napovedy.add("Prozkoumej každou místnost důkladně, mohou v ní být skryté předměty nebo indicie.");

        // Přidání tipů
        tipy.add("Zkus použít magickou svíci v temných místnostech.");
        tipy.add("Golem reaguje na specifické předměty nebo zaklínadla.");
        tipy.add("Duch starého archiváře ví o tajných chodbách v knihovně.");
        tipy.add("Knihovnice může mít klíče od uzamčených místností.");
        tipy.add("Svitky pravdy odhalují skryté věci v místnostech.");
    }

    /**
     * Zobrazení nápovědy
     * @return Text nápovědy
     */
    public String zobrazNapovedu() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== NÁPOVĚDA ===\n");

        for (String napoveda : napovedy) {
            sb.append("- ").append(napoveda).append("\n");
        }

        return sb.toString();
    }

    /**
     * Poskytnutí náhodného tipu
     * @return Náhodný tip
     */
    public String poskytniTip() {
        if (tipy.isEmpty()) {
            return "Nemám pro tebe žádné další tipy.";
        }

        int index = random.nextInt(tipy.size());
        return "TIP: " + tipy.get(index);
    }

    /**
     * Přidání nové nápovědy
     * @param napoveda Text nápovědy
     */
    public void pridejNapovedu(String napoveda) {
        napovedy.add(napoveda);
    }

    /**
     * Přidání nového tipu
     * @param tip Text tipu
     */
    public void pridejTip(String tip) {
        tipy.add(tip);
    }
}