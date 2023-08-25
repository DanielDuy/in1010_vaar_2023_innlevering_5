import java.io.File;
import java.util.Objects;

public class Oblig5Del2B {

    // Del 3
    // 8 flette tråder

    public static void main(String [] args) {

        SubsekvensRegister sR = new SubsekvensRegister();

        Monitor2 mtr = new Monitor2(sR);

        File mappe = new File(args[0]);


        for (File fil : Objects.requireNonNull(mappe.listFiles())) {
            if (!fil.getName().equals("metadata.csv")) {
                LeseTrad tdr = new LeseTrad(fil.getPath(), mtr);
                tdr.run();
            }
        }

        int antallFletteTrader = 8;
        for (int i = 0; i < antallFletteTrader; i++) {
            FletteTrad nyFletteTrad = new FletteTrad(mtr);
            nyFletteTrad.run();
        }

        int maks = 0;
        String subsekvensMaks = "";
        for (String k : sR.hentHashMap(0).keySet()) {
            if (sR.hentHashMap(0).get(k).hentAntallForekomster() > maks) {
                maks = sR.hentHashMap(0).get(k).hentAntallForekomster();
                subsekvensMaks = k;
            }
        }

        System.out.println("Mest forekomster: "+subsekvensMaks+ " med "+maks+" forekomster.");

    }
}
