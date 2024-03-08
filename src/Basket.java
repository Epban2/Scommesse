import java.util.concurrent.ThreadLocalRandom;

/**
 * Le scommesse sul calcio e basket avranno il nome delle 2 squadre e il
 * risultato previsto.
 */
public class Basket extends Scommessa {
    private String squadraUno;
    private String squadraDue;
    private String risultatoPrevisto;

    public Basket(String data, double puntata, String squadraUno, String squadraDue,
            String risultatoPrevisto) {
        super(data, puntata);
        this.squadraUno = squadraUno;
        this.squadraDue = squadraDue;
        this.risultatoPrevisto = risultatoPrevisto;
        EffettuaScommessa();
    }

    public String getSquadraUno() {
        return squadraUno;
    }

    public void setSquadraUno(String squadraUno) {
        this.squadraUno = squadraUno;
    }

    public String getSquadraDue() {
        return squadraDue;
    }

    public void setSquadraDue(String squadraDue) {
        this.squadraDue = squadraDue;
    }

    public String getRisultatoPrevisto() {
        return risultatoPrevisto;
    }

    public void setRisultatoPrevisto(String risultatoPrevisto) {
        this.risultatoPrevisto = risultatoPrevisto;
    }

    @Override
    public String toString() {
        return "Basket: " + super.toString() +
                "squadraUno='" + squadraUno + '\'' +
                ", squadraDue='" + squadraDue + '\'' +
                ", risultatoPrevisto=" + risultatoPrevisto;
    }

    /**
     * Effettua la scommessa e calcola il risultato se vinto
     */
    @Override
    public void EffettuaScommessa() {
        int risultatoSquadraUno = ThreadLocalRandom.current().nextInt(0, 4 + 1);
        int risultatoSquadraDue = ThreadLocalRandom.current().nextInt(0, 4 + 1);
        if (risultatoPrevisto.equals("1") && risultatoSquadraUno > risultatoSquadraDue) {
            this.setVincita(this.quota * this.puntata);
            setVinto(true);
        } else if (risultatoPrevisto.equals("2") && risultatoSquadraDue > risultatoSquadraUno) {
            this.setVincita(this.quota * this.puntata);
            setVinto(true);
        } else if (risultatoPrevisto.equals("x") && risultatoSquadraDue == risultatoSquadraUno) {
            this.setVincita(this.quota * this.puntata);
            setVinto(true);
        } else
            setVinto(false);
    }
}
