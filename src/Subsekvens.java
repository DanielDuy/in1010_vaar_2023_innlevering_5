public class Subsekvens {
    public final String subsekvens;
    private int antallForekomster;

    public Subsekvens(String konstruktorSubsekvens) {
        subsekvens = konstruktorSubsekvens;
        this.antallForekomster = 1;
    }

    public Subsekvens(String konstruktorSubsekvens, int konstruktorAntForekomst) {
        subsekvens = konstruktorSubsekvens;
        antallForekomster = konstruktorAntForekomst;
    }

    public String hentSubsekvens() {
        return subsekvens;
    }

    public int hentAntallForekomster() {
        return antallForekomster;
    }

    public void leggTilAntallForekomster() {
        antallForekomster += 1;
    }

    public void endreAntallForekomster(int nyAntallForekomster) {
        antallForekomster = nyAntallForekomster;
    }

    @Override
    public String toString() {
        return "("+subsekvens+","+antallForekomster+")";
    }
}
