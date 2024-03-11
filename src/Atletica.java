import java.util.concurrent.ThreadLocalRandom;

/**
 * Le scommesse su atletica avranno il nome specialità e il nome del vincitore
 */
public class Atletica extends Scommessa {
    private String specialita;
    private String nomeVincitore;
    private String[] listaAtleti = { "Gianluca", "Ignazio", "Nicola", "Michele", "Emanuele" };

    public Atletica(String data, double puntata, String specialita, String nomeVincitore) {
        super(data, puntata);
        this.specialita = specialita;
        this.nomeVincitore = nomeVincitore;
        effettuaScommessa();
    }

    public String getSpecialita() {
        return specialita;
    }

    public void setSpecialita(String specialita) {
        this.specialita = specialita;
    }

    public String getNomeVincitore() {
        return nomeVincitore;
    }

    public void setNomeVincitore(String nomeVincitore) {
        this.nomeVincitore = nomeVincitore;
    }

    @Override
    public String toString() {
        return "Atletica: " + super.toString() +
                "specialita='" + specialita + '\'' +
                ", nomeVincitore='" + nomeVincitore + '\'';
    }

    /**
     * Effettua la scommessa e calcola la vincita
     */
    @Override
    public void effettuaScommessa() {
        int indexVinvitore = ThreadLocalRandom.current().nextInt(0, 4 + 1);
        if (nomeVincitore.equals(listaAtleti[indexVinvitore])) {
            setVincita(quota * puntata);
            setVinto(true);
        }
    }
}
