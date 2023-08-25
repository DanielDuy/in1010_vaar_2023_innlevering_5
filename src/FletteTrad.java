import java.util.ArrayList;
import java.util.HashMap;

public class FletteTrad implements Runnable {

    Monitor2 monitor2;

    public FletteTrad(Monitor2 paramMonitor2) {
        monitor2 = paramMonitor2;
    }

    @Override
    public void run() {
        while (monitor2.hentSubsekvensRegister().hentAntallHashMap() > 1) {
            ArrayList<HashMap<String, Subsekvens>> tempArrayList = null;
            try {
                tempArrayList = monitor2.hentToHashMapsMedLaas();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            HashMap<String, Subsekvens> nyHashMap = SubsekvensRegister.slaSammenHashMaps(tempArrayList.get(0), tempArrayList.get(1));
            monitor2.settInnHashMap(nyHashMap);
        }

    }
}
