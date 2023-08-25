import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SubsekvensRegister {

    private ArrayList<HashMap<String, Subsekvens>> hashBeholder = new ArrayList<>();

    public ArrayList<HashMap<String, Subsekvens>> hentHashBeholder() {
        return hashBeholder;
    }

    public void settInnHashMap(HashMap<String, Subsekvens> paramHashMap) {
        hashBeholder.add(paramHashMap);
    }

    public void fjernHashMap(int paramPlass) {
        hashBeholder.remove(paramPlass);
    }

    public HashMap<String, Subsekvens> hentHashMap (int arrayListePlass) {
        return hashBeholder.get(arrayListePlass);
    }

    public int hentAntallHashMap() {
        return hashBeholder.size();
    }

    public static HashMap<String, Subsekvens> leseFil(String paramFilnavn) {

        HashMap<String, Subsekvens> nyHashMap = new HashMap<>();

        try {

            File nyFil = new File(paramFilnavn);

            Scanner filLeser = new Scanner(nyFil);

            while (filLeser.hasNextLine()) {

                String linje = filLeser.nextLine();

                if (linje.length() < 3) {
                    break;
                }

                for (int i = 0; i < (linje.length()-2); i++) {

                    String nySubsekvens = "";

                    for (int k = i; k < i+3; k++) {
                        nySubsekvens += linje.charAt(k);
                    }

                    if (!nyHashMap.containsKey(nySubsekvens)) {
                        Subsekvens nySubksekvensObjekt = new Subsekvens(nySubsekvens);
                        nyHashMap.put(nySubsekvens, nySubksekvensObjekt);
                    }
                }
            }

            filLeser.close();

        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke fil med denne filnavnet.");
            e.printStackTrace();
        }
        return nyHashMap;
    }

    public static HashMap<String, Subsekvens> slaSammenHashMaps(HashMap<String, Subsekvens> paramHashMap1, HashMap<String, Subsekvens> paramHashMap2) {

        HashMap<String, Subsekvens> nyHashMap = new HashMap<>();
        String[] keySet1 = new String[paramHashMap1.size()];
        String[] keySet2 = new String[paramHashMap2.size()];

        int index = 0;

        for (String i : paramHashMap1.keySet()) {
            keySet1[index] = i;
            index++;
        }

        index = 0;

        for (String i : paramHashMap2.keySet()) {
            keySet2[index] = i;
            index++;
        }

        if (paramHashMap1.size() >= paramHashMap2.size()) {

            for (int i = 0; i < keySet1.length; i++) {

                if (nyHashMap.containsKey(keySet1[i])) {

                    int gammelAntall = nyHashMap.get(keySet1[i]).hentAntallForekomster();
                    int leggeTilAntall = paramHashMap1.get(keySet1[i]).hentAntallForekomster();

                    nyHashMap.put(keySet1[i], new Subsekvens(keySet1[i], gammelAntall + leggeTilAntall));

                } else {
                    nyHashMap.put(keySet1[i], new Subsekvens(keySet1[i], paramHashMap1.get(keySet1[i]).hentAntallForekomster()));

                }

                if (i < keySet2.length) {

                    if (nyHashMap.containsKey(keySet2[i])) {

                        int gammelAntall = nyHashMap.get(keySet2[i]).hentAntallForekomster();
                        int leggeTilAntall = paramHashMap2.get(keySet2[i]).hentAntallForekomster();

                        nyHashMap.put(keySet2[i], new Subsekvens(keySet2[i], gammelAntall + leggeTilAntall));

                    } else {
                        nyHashMap.put(keySet2[i], new Subsekvens(keySet2[i], paramHashMap2.get(keySet2[i]).hentAntallForekomster()));
                    }
                }
            }

        } else {

            for (int i = 0; i < keySet2.length; i++) {

                if (i < keySet1.length) {

                    if (nyHashMap.containsKey(keySet1[i])) {

                        int gammelAntall = nyHashMap.get(keySet1[i]).hentAntallForekomster();
                        int leggeTilAntall = paramHashMap1.get(keySet1[i]).hentAntallForekomster();

                        nyHashMap.put(keySet1[i], new Subsekvens(keySet1[i], gammelAntall + leggeTilAntall));

                    } else {
                        nyHashMap.put(keySet1[i], new Subsekvens(keySet1[i], paramHashMap1.get(keySet1[i]).hentAntallForekomster()));

                    }
                }
                if (nyHashMap.containsKey(keySet2[i])) {

                    int gammelAntall = nyHashMap.get(keySet2[i]).hentAntallForekomster();
                    int leggeTilAntall = paramHashMap2.get(keySet2[i]).hentAntallForekomster();

                    nyHashMap.put(keySet2[i], new Subsekvens(keySet2[i], gammelAntall + leggeTilAntall));

                } else {
                    nyHashMap.put(keySet2[i], new Subsekvens(keySet2[i], paramHashMap2.get(keySet2[i]).hentAntallForekomster()));
                }
            }
        }

        return nyHashMap;
    }

}
