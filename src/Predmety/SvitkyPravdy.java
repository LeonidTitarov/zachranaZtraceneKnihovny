package Predmety;

public class SvitkyPravdy extends prenosnyPredmet {

    public SvitkyPravdy() {
        super("Svitek pravdy", "Starý pergamen s magickými runami, které se třpytí ve světle.");
    }

    public void odhalSkryte() {
        System.out.println("Držíš svitek před sebou a runy na něm začínají zářit.");
        System.out.println("Odkrývají se ti skryté detaily místnosti, které jsi dříve neviděl(a).");
    }

    @Override
    public void pouzij() {
        odhalSkryte();
    }
}
