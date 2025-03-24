    package Hlavni_Herni_Tridy;

    public class TestSveta {
        public static void main(String[] args) {

            Svet svet = new Svet();

            // Načtení světa ze souboru
            boolean uspech = svet.nactiSvet("./src/mapa.csv");
            if (!uspech) {
                System.out.println("Nepodařilo se načíst svět ze souboru!");
                return;
            }

            System.out.println("Svět byl úspěšně načten!");
            System.out.println("Počet místností: " + svet.getPocetMistnosti());
            System.out.println();


            svet.nastavAktualniMistnost("VEZ");

            zobrazInformaceOMistnosti(svet.getAktualniMistnost());

            System.out.println("\nPřesouvám se na východ...");
            Mistnost novaMistnost = svet.jdiSmerem("vychod");
            zobrazInformaceOMistnosti(novaMistnost);

            System.out.println("\nPřesouvám se na sever...");
            novaMistnost = svet.jdiSmerem("sever");
            zobrazInformaceOMistnosti(novaMistnost);

            System.out.println("\nPřesouvám se na zapad...");
            novaMistnost = svet.jdiSmerem("zapad");
            zobrazInformaceOMistnosti(novaMistnost);

            System.out.println("\nPřesouvám se na jih...");
            novaMistnost = svet.jdiSmerem("jih");
            zobrazInformaceOMistnosti(novaMistnost);
        }

        /**
         * Zobrazení informací o místnosti.
         * @param mistnost Místnost
         */

        private static void zobrazInformaceOMistnosti(Mistnost mistnost) {
            if (mistnost == null) {
                System.out.println("Místnost nenalezena!");
                return;
            }

            System.out.println("Nacházíš se v: " + mistnost.getNazev());
            System.out.println(mistnost.getPopis());
            System.out.println(mistnost.seznamVychodu());
        }
    }
