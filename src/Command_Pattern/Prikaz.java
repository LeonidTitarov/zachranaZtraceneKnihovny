package Command_Pattern;

public interface Prikaz {
    /**
     * Provede příkaz s danými parametry
     * @param parametry Parametry příkazu (může být prázdné)
     * @return true pokud hra má pokračovat, false pokud má skončit
     */

    boolean proved(String[] parametry);

    /**
     * Vrátí název příkazu
     * @return Název příkazu
     */

    String getNazev();

    /**
     * Vrátí stručný popis příkazu
     * @return Popis příkazu
     */

    String getPopis();
}
