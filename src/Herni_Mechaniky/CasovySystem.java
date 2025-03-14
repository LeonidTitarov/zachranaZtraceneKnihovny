package Herni_Mechaniky;

public class CasovySystem {
    private int zbyvajiciTahy;
    private boolean casVyprsel;

    public CasovySystem() {
        this.zbyvajiciTahy = 30; // Dle zadání je 30 tahů
        this.casVyprsel = false;
    }

    public void odpocetTahu() {
        zbyvajiciTahy--;

        if (zbyvajiciTahy <= 0) {
            casVyprsel = true;
            System.out.println("Čas vypršel! Knihy se začínají rozpadat...");
        } else if (zbyvajiciTahy <= 5) {
            System.out.println("Pozor! Zbývá ti jen " + zbyvajiciTahy + " tahů.");
        }
    }}