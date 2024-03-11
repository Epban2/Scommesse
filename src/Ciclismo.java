import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Ciclismo extends Scommessa {
    private String[] ciclisti = { "Jonas Vingegaard", "Tadej Pogacar", "Primoz Roglic", "Romain Bardet",
            "Richard Carapaz" };
    private String nomeVincitore;

    public Ciclismo(String data, double puntata, String nomeVincitore) {
        super(data, puntata);
        this.nomeVincitore = nomeVincitore;
        effettuaScommessa();
    }

    public String[] getCiclisti() {
        return ciclisti;
    }

    public void setCiclisti(String[] ciclisti) {
        this.ciclisti = ciclisti;
    }

    public String getNomeVincitore() {
        return nomeVincitore;
    }

    public void setNomeVincitore(String nomeVincitore) {
        this.nomeVincitore = nomeVincitore;
    }

    @Override
    public String toString() {
        return "Ciclismo [ciclisti=" + Arrays.toString(ciclisti) + ", nomeVincitore=" + nomeVincitore + "]";
    }

    /**
     * Effettua la scommessa e calcola la vincita
     */
    @Override
    public void effettuaScommessa() {
        int indexVinvitore = ThreadLocalRandom.current().nextInt(0, 4 + 1);
        if (nomeVincitore.equals(ciclisti[indexVinvitore])) {
            setVincita(quota * puntata);
            setVinto(true);
        }

    }
}
