package Hlavni_Herni_Tridy;

import Command_Pattern.Prikaz;
import Command_Pattern.SeznamPrikazu;
import Herni_Mechaniky.CasovySystem;
import Herni_Mechaniky.DialogSystem;
import Herni_Mechaniky.Inventar;
import Herni_Mechaniky.NapovedaSystem;
import Postava_a_NPC.NPC;
import Predmety.Predmet;
import Predmety.prenosnyPredmet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Hra {
    private Svet svet;
    private boolean konecHry = false;
    private SeznamPrikazu seznamPrikazu;
    private Inventar inventar;
    private CasovySystem casovySystem;
    private DialogSystem dialogSystem;
    private NapovedaSystem napovedaSystem;
    private Scanner scanner;
    private Map<String, List<Predmet>> predmetyVMistnostech;
    private Map<String, List<NPC>> npcVMistnostech;
    private boolean knihaNalezena = false;

    public Hra() {
        svet = new Svet();
        seznamPrikazu = new SeznamPrikazu(this);
        inventar = new Inventar();
        casovySystem = new CasovySystem();
        dialogSystem = new DialogSystem();
        napovedaSystem = new NapovedaSystem(this);
        scanner = new Scanner(System.in);
        predmetyVMistnostech = new HashMap<>();
        npcVMistnostech = new HashMap<>();

        // Načtení světa
        boolean uspech = svet.nactiSvet("./src/mapa.csv");
        if (!uspech) {
            System.out.println("Nepodařilo se načíst svět!");
            System.exit(1);
        }

        // Inicializace místností s předměty a postavami
        inicializujSvet();
    }

    private void inicializujSvet() {
        // Inicializace předmětů v místnostech podle Game Design
        pridejPredmetyDoMistnosti();

        // Inicializace NPC v místnostech
        pridejNpcDoMistnosti();
    }

    private void pridejPredmetyDoMistnosti() {
        // Pro každou místnost vytvoříme list předmětů
        for (String nazevMistnosti : new String[]{"HLAVNI_ARCHIV", "STUDOVNA", "VYCHODNI_KRIDLO",
                "ZAPADNI_KRIDLO", "SKLEPENI", "VEZ", "MAGOVA_SKRYS"}) {
            predmetyVMistnostech.put(nazevMistnosti, new ArrayList<>());
        }

        // Přidání předmětů podle Game Designu

    }

    private void pridejNpcDoMistnosti() {
        // Pro každou místnost vytvoříme list postav
        for (String nazevMistnosti : new String[]{"HLAVNI_ARCHIV", "STUDOVNA", "VYCHODNI_KRIDLO",
                "ZAPADNI_KRIDLO", "SKLEPENI", "VEZ", "MAGOVA_SKRYS"}) {
            npcVMistnostech.put(nazevMistnosti, new ArrayList<>());
        }

        // Přidání NPC podle Game Designu

    }

    public void spustenHry() {
        System.out.println("Vítej ve hře Záchrana Ztracené Knihovny!");
        System.out.println("Jsi Alex, mladý učeň v Magické knihovně. Zjistil(a) jsi, že někdo ukradl Knihu Prvního Kouzla.");
        System.out.println("Tvým úkolem je ji najít a vrátit na její místo v Hlavním archivu, než se všechny knihy rozpadnou.");
        System.out.println("Pro nápovědu zadej příkaz 'napoveda'.");
        System.out.println();

        Mistnost aktualniMistnost = svet.getAktualniMistnost();
        System.out.println("Nacházíš se v: " + aktualniMistnost.getNazev());
        System.out.println(aktualniMistnost.getPopis());
        System.out.println(aktualniMistnost.seznamVychodu());

        // Hlavní herní smyčka
        while (!konecHry) {
            System.out.print("> ");
            String radek = scanner.nextLine();
            provedPrikaz(radek);

            // Kontrola časového limitu
            if (casovySystem.isCasVyprsel()) {
                System.out.println("Čas vypršel! Knihovna se rozpadla a s ní i všechna magie.");
                System.out.println("KONEC HRY - PROHRA");
                konecHry = true;
            }
        }

        System.out.println("Děkujeme za hru. Ahoj!");
    }

    public void provedPrikaz(String radek) {
        String[] slova = radek.split(" ");
        if (slova.length == 0) {
            return;
        }
        System.out.println(slova.length + " delka");

        String prikaz = slova[0].toLowerCase();
        Prikaz vykonnyPrikaz = seznamPrikazu.najdiPrikaz(prikaz);

        if (vykonnyPrikaz == null) {
            System.out.println("Neznámý příkaz. Pro nápovědu zadej 'napoveda'.");
            return;
        }
        konecHry = !vykonnyPrikaz.proved(slova);
    }

    public void ukonciHru() {
        konecHry = true;
    }

    public void vyhodnotHru() {
        if (knihaNalezena && svet.getAktualniMistnost().getNazev().equals("HLAVNI_ARCHIV")) {
            System.out.println("Gratulujeme! Vrátil(a) jsi Knihu Prvního Kouzla na její místo.");
            System.out.println("Knihovna je zachráněna a všechny knihy znovu získávají svou moc.");
            System.out.println("KONEC HRY - VÍTĚZSTVÍ");
            konecHry = true;
        } else {
            System.out.println("Položil(a) jsi Knihu Prvního Kouzla na podstavec.");
            System.out.println("Knihovna začíná znovu nabývat své moci. Zbývá ti jen vrátit se do Hlavního archivu.");
            knihaNalezena = true;
        }
    }

    public void prozkoumejMistnost() {
        Mistnost aktualniMistnost = svet.getAktualniMistnost();
        System.out.println("Prozkoumal(a) jsi místnost " + aktualniMistnost.getNazev() + ":");
        System.out.println(aktualniMistnost.getPopis());

        // Výpis předmětů v místnosti
        List<Predmet> predmety = predmetyVMistnostech.get(aktualniMistnost.getNazev());
        if (predmety != null && !predmety.isEmpty()) {
            System.out.println("Vidíš tyto předměty:");
            for (Predmet predmet : predmety) {
                System.out.println("- " + predmet.getNazev());
            }
        } else {
            System.out.println("Nevidíš tu žádné předměty.");
        }

        // Výpis NPC v místnosti
        List<NPC> postavy = npcVMistnostech.get(aktualniMistnost.getNazev());
        if (postavy != null && !postavy.isEmpty()) {
            System.out.println("Jsou zde tyto postavy:");
            for (NPC postava : postavy) {
                System.out.println("- " + postava.getJmeno());
            }
        } else {
            System.out.println("Není tu nikdo, s kým by ses mohl(a) bavit.");
        }
    }

    public void prozkoumejPredmet(String nazevPredmetu) {
        Mistnost aktualniMistnost = svet.getAktualniMistnost();
        List<Predmet> predmety = predmetyVMistnostech.get(aktualniMistnost.getNazev());

        // Nejprve zkontrolujeme předměty v místnosti
        if (predmety != null) {
            for (Predmet predmet : predmety) {
                if (predmet.getNazev().equalsIgnoreCase(nazevPredmetu)) {
                    System.out.println(predmet.getPopis());
                    return;
                }
            }
        }

        // Poté zkontrolujeme předměty v inventáři
        prenosnyPredmet predmetVInventari = inventar.najdiPredmet(nazevPredmetu);
        if (predmetVInventari != null) {
            System.out.println(predmetVInventari.getPopis());
            return;
        }

        System.out.println("Takový předmět tu není nebo ho nemáš v inventáři.");
    }

    public Predmet najdiPredmetVMistnosti(String nazevPredmetu) {
        Mistnost aktualniMistnost = svet.getAktualniMistnost();
        List<Predmet> predmety = predmetyVMistnostech.get(aktualniMistnost.getNazev());

        if (predmety != null) {
            for (Predmet predmet : predmety) {
                if (predmet.getNazev().equalsIgnoreCase(nazevPredmetu)) {
                    return predmet;
                }
            }
        }

        return null;
    }

    public void odstranPredmetZMistnosti(String nazevPredmetu) {
        Mistnost aktualniMistnost = svet.getAktualniMistnost();
        List<Predmet> predmety = predmetyVMistnostech.get(aktualniMistnost.getNazev());

        if (predmety != null) {
            predmety.removeIf(predmet -> predmet.getNazev().equalsIgnoreCase(nazevPredmetu));
        }
    }

    public void pridejPredmetDoMistnosti(prenosnyPredmet predmet) {
        Mistnost aktualniMistnost = svet.getAktualniMistnost();
        List<Predmet> predmety = predmetyVMistnostech.get(aktualniMistnost.getNazev());

        if (predmety != null) {
            predmety.add(predmet);
        }
    }

    public NPC najdiNPCVMistnosti(String jmenoNPC) {
        Mistnost aktualniMistnost = svet.getAktualniMistnost();
        List<NPC> postavy = npcVMistnostech.get(aktualniMistnost.getNazev());

        if (postavy != null) {
            for (NPC postava : postavy) {
                if (postava.getJmeno().equalsIgnoreCase(jmenoNPC)) {
                    return postava;
                }
            }
        }

        return null;
    }

    // Gettery
    public SeznamPrikazu getSeznamPrikazu() {
        return seznamPrikazu;
    }

    public Inventar getInventar() {
        return inventar;
    }

    public CasovySystem getCasovySystem() {
        return casovySystem;
    }

    public Svet getSvet() {
        return svet;
    }

    public DialogSystem getDialogSystem() {
        return dialogSystem;
    }

    public NapovedaSystem getNapovedaSystem() {
        return napovedaSystem;
    }
}