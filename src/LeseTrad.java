import java.util.HashMap;

public class LeseTrad implements Runnable {

    private String tradFilnavn;
    private Monitor2 tradMonitor;

    public LeseTrad(String paramFilnavn, Monitor2 paramMonitor) {
        tradFilnavn = paramFilnavn;
        tradMonitor = paramMonitor;
    }

    public void settNyFilnavn(String paramNyFilnavn) {
        tradFilnavn = paramNyFilnavn;
    }

    @Override
    public void run() {
        HashMap<String, Subsekvens> nyHashMap = SubsekvensRegister.leseFil(tradFilnavn);
        tradMonitor.settInnHashMap(nyHashMap);
    }
}
