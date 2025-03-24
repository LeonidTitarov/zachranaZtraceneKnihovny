package Herni_Mechaniky;

import Postava_a_NPC.NPC;
import java.util.Scanner;

public class DialogSystem {
    private NPC aktivniNPC;
    private Scanner scanner;

    public DialogSystem() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Zahájení dialogu s NPC
     * @param postava NPC, se kterou má být dialog zahájen
     */
    public void zahajDialog(NPC postava) {
        if (postava == null) {
            System.out.println("Tato postava neexistuje.");
            return;
        }

        this.aktivniNPC = postava;
        System.out.println("\n--- Dialog s postavou: " + postava.getJmeno() + " ---");
        postava.zahajDialog();

        vyberMoznosti();
    }

    /**
     * Výběr možností dialogu
     */
    public void vyberMoznosti() {
        if (aktivniNPC == null) {
            return;
        }

        System.out.println("\nMožnosti:");
        System.out.println("1. Požádat o nápovědu");

        // Specifické možnosti podle typu NPC
        if (aktivniNPC.getJmeno().contains("Knihovnice")) {
            System.out.println("2. Požádat o klíč");
            System.out.println("3. Požádat o radu");
        } else if (aktivniNPC.getJmeno().contains("Duch")) {
            System.out.println("2. Zeptat se na tajemství");
        } else if (aktivniNPC.getJmeno().contains("Golem")) {
            System.out.println("2. Zkontrolovat stav");
        }

        System.out.println("0. Ukončit dialog");

        System.out.print("\nVyber možnost: ");
        try {
            int volba = Integer.parseInt(scanner.nextLine());

            switch (volba) {
                case 0:
                    ukonciDialog();
                    break;
                case 1:
                    aktivniNPC.dejNapovedu();
                    vyberMoznosti(); // Pokračujeme v dialogu
                    break;
                case 2:
                    if (aktivniNPC.getJmeno().contains("Knihovnice")) {
                        ((Postava_a_NPC.Knihovnice) aktivniNPC).poskytniKlic();
                    } else if (aktivniNPC.getJmeno().contains("Duch")) {
                        ((Postava_a_NPC.Duch) aktivniNPC).prozradTajemstvi();
                    } else if (aktivniNPC.getJmeno().contains("Golem")) {
                        ((Postava_a_NPC.Golem) aktivniNPC).kontroluj();
                    }
                    vyberMoznosti(); // Pokračujeme v dialogu
                    break;
                case 3:
                    if (aktivniNPC.getJmeno().contains("Knihovnice")) {
                        ((Postava_a_NPC.Knihovnice) aktivniNPC).dejRadu();
                    } else {
                        System.out.println("Neplatná volba!");
                    }
                    vyberMoznosti(); // Pokračujeme v dialogu
                    break;
                default:
                    System.out.println("Neplatná volba!");
                    vyberMoznosti(); // Pokračujeme v dialogu
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Neplatný vstup! Zadej číslo.");
            vyberMoznosti(); // Pokračujeme v dialogu
        }
    }

    /**
     * Ukončení dialogu
     */
    public void ukonciDialog() {
        if (aktivniNPC != null) {
            System.out.println("Ukončil(a) jsi dialog s postavou " + aktivniNPC.getJmeno() + ".");
            aktivniNPC = null;
        }
    }

    /**
     * Kontrola, zda probíhá dialog
     * @return true pokud probíhá dialog, jinak false
     */
    public boolean probihaDalog() {
        return aktivniNPC != null;
    }

    /**
     * Získání aktivní NPC
     * @return Aktivní NPC nebo null
     */
    public NPC getAktivniNPC() {
        return aktivniNPC;
    }
}