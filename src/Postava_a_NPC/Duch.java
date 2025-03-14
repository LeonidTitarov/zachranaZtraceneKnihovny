package Postava_a_NPC;

public class Duch extends NPC {

    public Duch() {
        super("Duch starého archiváře", "Průsvitná postava starého muže v knihovnických šatech.");
        pridejDialog("*Šeptá* Vítej, mladý učni. Jsem duch starého archiváře této knihovny.");
        pridejDialog("Hledáš ukradenou knihu? Možná ti mohu pomoci...");
    }

    public void prozradTajemstvi() {
        System.out.println("Duch se přiblíží a zašeptá: \"Existuje tajná chodba ve sklepení. Hledej za velkým regálem.\"");
    }

    @Override
    public void dejNapovedu() {
        System.out.println("Duch říká: \"Magické předměty jsou klíčem k odhalení tajemství knihovny. Hledej svítek pravdy ve studovně.\"");
    }
}