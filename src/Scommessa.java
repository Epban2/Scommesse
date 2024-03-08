import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Ciascuna scommessa avrà una data, una cifra puntata e una quota e un booleano
 * che indica se ha vinto.
 */
public abstract class Scommessa {
    protected String data;
    protected Double puntata;
    protected double quota;
    protected boolean vinto;
    protected double vincita = 0;

    public Scommessa(String data, double puntata) {
        SecureRandom random = new SecureRandom();
        this.data = data;
        this.puntata = puntata;
        this.quota = 2.0 + random.nextDouble() * 1.5; // numero casuale non intero tra 2 e 3.5
        this.vinto = false;
    }

    public Scommessa() {
    } // costruttore vuoto

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getPuntata() {
        return puntata;
    }

    public void setPuntata(double puntata) {
        this.puntata = puntata;
    }

    public double getQuota() {
        return quota;
    }

    public void setQuota(double quota) {
        this.quota = quota;
    }

    public boolean isVinto() {
        return vinto;
    }

    public void setVinto(boolean vinto) {
        this.vinto = vinto;
    }

    public double getVincita() {
        return vincita;
    }

    public void setVincita(double vincita) {
        this.vincita = vincita;
    }

    @Override
    public String toString() {
        return "Scommessa{" +
                "data=" + data +
                ", puntata=" + puntata +
                ", quota=" + quota +
                ", vinto=" + vinto;
    }

    // metodo astratto
    public abstract void EffettuaScommessa();

}
