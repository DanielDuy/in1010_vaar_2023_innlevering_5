import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor2 {
    private SubsekvensRegister subsekvensRegister;
    private Lock laas;

    Condition ikkeTom;

    public Monitor2(SubsekvensRegister paramSubRegister) {
        subsekvensRegister = paramSubRegister;
        laas = new ReentrantLock();
        ikkeTom = laas.newCondition();
    }

    public SubsekvensRegister hentSubsekvensRegister() {
        return subsekvensRegister;
    }

    public void settInnHashMap(HashMap<String, Subsekvens> paramHashMap)  {
        laas.lock();
        try {
            subsekvensRegister.settInnHashMap(paramHashMap);
        } finally {
            laas.unlock();
        }
    }

    public ArrayList<HashMap<String, Subsekvens>> hentToHashMapsMedLaas() throws InterruptedException {
        laas.lock();
        ArrayList<HashMap<String, Subsekvens>> tempArrayList = new ArrayList<>();
        try {
            if (subsekvensRegister.hentAntallHashMap() > 1) {
                HashMap<String, Subsekvens> tempHashMap1 = subsekvensRegister.hentHashMap(0);
                subsekvensRegister.fjernHashMap(0);
                HashMap<String, Subsekvens> tempHashMap2 = subsekvensRegister.hentHashMap(0);
                subsekvensRegister.fjernHashMap(0);
                tempArrayList.add(tempHashMap1);
                tempArrayList.add(tempHashMap2);
            }
        } finally {
            laas.unlock();
        }
        return tempArrayList;
    }

}
