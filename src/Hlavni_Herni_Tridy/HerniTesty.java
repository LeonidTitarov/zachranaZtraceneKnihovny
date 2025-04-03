package Hlavni_Herni_Tridy;

import Predmety.prenosnyPredmet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Třída pro testování metod herních tříd Hra a Hrac
 * @author Váš jméno
 */
public class HerniTesty {

    private Hra hra;
    private Hrac hrac;

    @Before
    public void setUp() {
        hra = new Hra();
        hrac = new Hrac("TestHrac", hra.getSvet(), hra);
    }

    /**
     * Test inicializace hry
     */
    @Test
    public void testInicializaceHry() {
        assertNotNull("Hra by měla být inicializována", hra);
        assertNotNull("Svět by měl být vytvořen", hra.getSvet());
        assertNotNull("Seznam příkazů by měl být vytvořen", hra.getSeznamPrikazu());
        assertNotNull("Inventář by měl být vytvořen", hra.getInventar());
    }

    /**
     * Test pohybu hráče
     */
    @Test
    public void testPohybuHrace() {
        String vysledek = hrac.pohyb("sever");
        assertFalse("Pohyb by měl vrátit popis nové místnosti",
                vysledek.contains("Tímto směrem nemůžeš jít!"));

        // Test neplatného směru
        String neplatnySmer = hrac.pohyb("neexistujici_smer");
        assertTrue("Při neplatném směru by měla být chybová zpráva",
                neplatnySmer.contains("Tímto směrem nemůžeš jít!"));
    }

    /**
     * Test sbírání předmětu
     */
    @Test
    public void testSebraniPredmetu() {
        // Nejprve přidáme předmět do aktuální místnosti
        prenosnyPredmet testPredmet = new prenosnyPredmet("TestPredmet", "Popis testovacího předmětu");
        hra.pridejPredmetDoMistnosti(testPredmet);

        String vysledek = hrac.seberPredmet("TestPredmet");
        assertTrue("Předmět by měl být sebrán",
                vysledek.contains("Sebral(a) jsi: TestPredmet"));

        // Test sbírání neexistujícího předmětu
        String neexistujici = hrac.seberPredmet("NeexistujiciPredmet");
        assertTrue("Při pokusu o sebrání neexistujícího předmětu by měla být chybová zpráva",
                neexistujici.contains("Předmět 'NeexistujiciPredmet' zde není!"));
    }

    /**
     * Test použití předmětu
     */
    @Test
    public void testPouzitiPredmetu() {
        // Přidáme předmět do inventáře
        prenosnyPredmet testPredmet = new prenosnyPredmet("TestPredmet", "Popis testovacího předmětu");
        hrac.getInventar().pridejPredmet(testPredmet);

        String vysledek = hrac.pouzijPredmet("TestPredmet");
        assertTrue("Předmět by měl být použit",
                vysledek.contains("Použil(a) jsi: TestPredmet"));

        // Test použití neexistujícího předmětu
        String neexistujici = hrac.pouzijPredmet("NeexistujiciPredmet");
        assertTrue("Při pokusu o použití neexistujícího předmětu by měla být chybová zpráva",
                neexistujici.contains("Nemáš NeexistujiciPredmet v inventáři!"));
    }

    /**
     * Test prozkoumání předmětu
     */
    @Test
    public void testProzkoumaniPredmetu() {
        // Přidáme předmět do inventáře
        prenosnyPredmet testPredmet = new prenosnyPredmet("TestPredmet", "Popis testovacího předmětu");
        hrac.getInventar().pridejPredmet(testPredmet);

        String vysledek = hrac.prozkoumejPredmet("TestPredmet");
        assertEquals("Popis by měl odpovídat předmětu",
                "Popis testovacího předmětu", vysledek);

        // Test prozkoumání neexistujícího předmětu
        String neexistujici = hrac.prozkoumejPredmet("NeexistujiciPredmet");
        assertTrue("Při pokusu o prozkoumání neexistujícího předmětu by měla být chybová zpráva",
                neexistujici.contains("Předmět 'NeexistujiciPredmet' není v inventáři ani v místnosti!"));
    }

    /**
     * Test zahození předmětu
     */
    @Test
    public void testZahozeniPredmetu() {
        // Přidáme předmět do inventáře
        prenosnyPredmet testPredmet = new prenosnyPredmet("TestPredmet", "Popis testovacího předmětu");
        hrac.getInventar().pridejPredmet(testPredmet);

        String vysledek = hrac.zahodPredmet("TestPredmet");
        assertTrue("Předmět by měl být zahozen",
                vysledek.contains("Zahodil(a) jsi: TestPredmet"));
        assertTrue("Předmět by měl být v místnosti",
                hra.najdiPredmetVMistnosti("TestPredmet") != null);

        // Test zahození neexistujícího předmětu
        String neexistujici = hrac.zahodPredmet("NeexistujiciPredmet");
        assertTrue("Při pokusu o zahození neexistujícího předmětu by měla být chybová zpráva",
                neexistujici.contains("Nemáš NeexistujiciPredmet v inventáři!"));
    }

    /**
     * Test prozkoumání místnosti
     */
    @Test
    public void testProzkoumaniMistnosti() {
        String vysledek = hrac.prozkoumejMistnost();
        assertTrue("Výstup by měl obsahovat popis místnosti",
                vysledek.contains(hra.getSvet().getAktualniMistnost().getPopis()));
        assertTrue("Výstup by měl obsahovat seznam východů",
                vysledek.contains(hra.getSvet().getAktualniMistnost().seznamVychodu()));
    }

    /**
     * Test časového systému
     */
    @Test
    public void testCasovehoSystemu() {
        int pocatecniTahy = hra.getCasovySystem().getZbyvajiciTahy();

        // Proveďeme akci, která spotřebovává tahy
        hrac.pohyb("sever");

        assertEquals("Po provedení akce by se měl počet tahů snížit",
                pocatecniTahy - 1, hra.getCasovySystem().getZbyvajiciTahy());
    }

    /**
     * Test inventáře
     */
    @Test
    public void testInventare() {
        // Test prázdného inventáře
      //  assertTrue("Inventář by měl být prázdný", hrac.getInventar().zobrazInventar().contains("Inventář je prázdný"));

        // Přidáme předmět
        prenosnyPredmet testPredmet = new prenosnyPredmet("TestPredmet", "Popis testovacího předmětu");
        hrac.getInventar().pridejPredmet(testPredmet);

       // assertFalse("Inventář by neměl být prázdný", hrac.getInventar().zobrazInventar().contains("Inventář je prázdný"));
       // assertTrue("Inventář by měl obsahovat přidaný předmět", hrac.getInventar().zobrazInventar().contains("TestPredmet"));
    }
}