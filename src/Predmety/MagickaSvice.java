package Predmety;

public class MagickaSvice extends prenosnyPredmet {
    private boolean rozsvicena;

    public MagickaSvice() {
        super("Magická svíce", "Stará svíce, která vydává zvláštní modravé světlo.");
        this.rozsvicena = false;
    }

    public void rozsvit() {
        if (!rozsvicena) {
            rozsvicena = true;
            System.out.println("Rozsvítil(a) jsi magickou svíci. Její modravé světlo odhaluje věci, které nejsou vidět běžným zrakem.");
        } else {
            System.out.println("Svíce již svítí.");
        }
    }

    public void odhalStopy() {
        if (rozsvicena) {
            System.out.println("Ve světle magické svíce vidíš stopy magické energie, které vedou přes místnost.");
        } else {
            System.out.println("Nejprve musíš svíci rozsvítit.");
        }
    }

    @Override
    public void pouzij() {
        rozsvit();
    }

    public boolean isRozsvicena() {
        return rozsvicena;
    }
}