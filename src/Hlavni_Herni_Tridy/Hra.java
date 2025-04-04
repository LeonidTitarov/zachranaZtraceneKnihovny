package  Hlavni_Herni_Tridy;

import Command_Pattern.Prikaz;
import Command_Pattern.SeznamPrikazu;
import Herni_Mechaniky.CasovySystem;
import Herni_Mechaniky.DialogSystem;
import Herni_Mechaniky.Inventar;
import Herni_Mechaniky.NapovedaSystem;
import Postava_a_NPC.Duch;
import Postava_a_NPC.Golem;
import Postava_a_NPC.Knihovnice;
import Postava_a_NPC.NPC;
import Predmety.MagickaSvice;
import Predmety.Predmet;
import Predmety.SvitkyPravdy;
import Predmety.prenosnyPredmet;

import java.util.*;

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

        for (String nazevMistnosti : new String[]{"HLAVNI_ARCHIV", "STUDOVNA", "VYCHODNI_KRIDLO",
                "ZAPADNI_KRIDLO", "SKLEPENI", "VEZ", "MAGOVA_SKRYS"}) {
            predmetyVMistnostech.put(nazevMistnosti, new ArrayList<>());
        }

        MagickaSvice magickaSvice = new MagickaSvice();
        predmetyVMistnostech.get("ZAPADNI_KRIDLO").add(magickaSvice);


        SvitkyPravdy svitekPravdy = new SvitkyPravdy();
        predmetyVMistnostech.get("STUDOVNA").add(svitekPravdy);

        prenosnyPredmet knihaPrvnihoKouzla = new prenosnyPredmet("Kniha Prvního Kouzla",
                "Starodávná kniha vázaná v kůži s zlatými runovými symboly. Vyzařuje z ní mocná magická energie.");
        predmetyVMistnostech.get("MAGOVA_SKRYS").add(knihaPrvnihoKouzla);


        Predmet podstavec = new Predmet("Podstavec",
                "Kamenný podstavec s prohlubní ve tvaru knihy. Je určený pro Knihu Prvního Kouzla.") {
            @Override
            public String getNazev() {
                return nazev;
            }

            @Override
            public String getPopis() {
                return popis;
            }
        };
        predmetyVMistnostech.get("HLAVNI_ARCHIV").add(podstavec);

        // Magické zrcadlo ve Východním křídle
        Predmet magickeZrcadlo = new Predmet("Magické zrcadlo",
                "Velké zrcadlo v ozdobném rámu. Když se do něj podíváš, vidíš více než jen svůj odraz.") {
            @Override
            public String getNazev() {
                return nazev;
            }

            @Override
            public String getPopis() {
                return popis;
            }
        };
        predmetyVMistnostech.get("VYCHODNI_KRIDLO").add(magickeZrcadlo);

        // Alchymistický stůl v Západním křídle
        Predmet alchymistickyStul = new Predmet("Alchymistický stůl",
                "Stůl plný různých lahviček, kádinky, hmoždíře a dalších alchymistických pomůcek.") {
            @Override
            public String getNazev() {
                return nazev;
            }

            @Override
            public String getPopis() {
                return popis;
            }
        };
        predmetyVMistnostech.get("ZAPADNI_KRIDLO").add(alchymistickyStul);

        // Astronomický dalekohled ve věži
        Predmet astronomickyDalekohled = new Predmet("Astronomický dalekohled",
                "Velký dalekohled namířený k obloze. Vypadá, že jej lze nastavit podle hvězd.") {
            @Override
            public String getNazev() {
                return nazev;
            }

            @Override
            public String getPopis() {
                return popis;
            }
        };
        predmetyVMistnostech.get("VEZ").add(astronomickyDalekohled);

        prenosnyPredmet lektvarNeviditelnosti = new prenosnyPredmet("Lektvar neviditelnosti",
                "Průhledná tekutina v malé lahvičce. Po vypití tě učiní dočasně neviditelným.");

    }





    private void pridejNpcDoMistnosti() {

        for (String nazevMistnosti : new String[]{"HLAVNI_ARCHIV", "STUDOVNA", "VYCHODNI_KRIDLO",
                "ZAPADNI_KRIDLO", "SKLEPENI", "VEZ", "MAGOVA_SKRYS"}) {
            npcVMistnostech.put(nazevMistnosti, new ArrayList<>());
        }

        Knihovnice knihovniceHelena = new Knihovnice();
        npcVMistnostech.get("HLAVNI_ARCHIV").add(knihovniceHelena);

        Duch duchStarehoArchivare = new Duch();
        npcVMistnostech.get("STUDOVNA").add(duchStarehoArchivare);


        Golem golemStrazce = new Golem();
        npcVMistnostech.get("VYCHODNI_KRIDLO").add(golemStrazce);

        // Mágovi pomocníci (havrani) v Mágově skrýši
        NPC havran1 = new NPC("Havran", "Velký černý pták s inteligentním pohledem.") {
            @Override
            public void zahajDialog() {
                System.out.println("Krááá! Co tu děláš, vetřelče?");
            }

            @Override
            public void dejNapovedu() {
                System.out.println("Havran krákavě říká: \"Pán má rád hádanky. Možná by ses mohl proklouznout, kdybys byl neviditelný.\"");
            }
        };
        havran1.pridejDialog("Krááá! Nejsi vítán v pánově doupěti!");

        NPC havran2 = new NPC("Havran", "Další z velkých černých ptáků, který tě bedlivě sleduje.") {
            @Override
            public void zahajDialog() {
                System.out.println("*Pátravě tě sleduje* Proč jsi přišel?");
            }

            @Override
            public void dejNapovedu() {
                System.out.println("Havran tiše říká: \"Pán ukryl knihu přímo ve svém pracovním stole. Ale je magicky chráněna.\"");
            }
        };
        havran2.pridejDialog("Hlídáme pánovy poklady před zloději jako jsi ty!");

        npcVMistnostech.get("MAGOVA_SKRYS").add(havran1);
        npcVMistnostech.get("MAGOVA_SKRYS").add(havran2);

        // Zlý mág (finální protivník) v Mágově skrýši
        NPC zlyMag = new NPC("Zlý mág", "Vysoký muž v tmavém plášti s kapucí a zlověstným pohledem.") {
            @Override
            public void zahajDialog() {
                System.out.println("*Temným hlasem* Tak ty ses odvážil přijít až sem, malý učni? Knihu ti nedám!");
            }

            @Override
            public void dejNapovedu() {
                System.out.println("Mág se ušklíbne: \"Knihu jsem ukryl dobře. Jen já vím, jak prolomit její ochranu.\"");
            }
        };
        zlyMag.pridejDialog("Ta kniha je příliš mocná pro takovou knihovnu. Její moc náleží mně!");
        zlyMag.pridejDialog("Myslíš, že mě můžeš zastavit? Jsi jen slabý učeň!");

        npcVMistnostech.get("MAGOVA_SKRYS").add(zlyMag);
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


        if (predmety != null) {
            for (Predmet predmet : predmety) {
                if (predmet.getNazev().equalsIgnoreCase(nazevPredmetu)) {
                    System.out.println(predmet.getPopis());
                    return;
                }
            }
        }
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