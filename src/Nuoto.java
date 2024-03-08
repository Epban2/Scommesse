import java.util.concurrent.ThreadLocalRandom;

/**
 * Le scommesse su nuoto avranno stile, distanza e nome del vincitore.
 */
public class Nuoto extends Scommessa {
    private String stile;
    private int distanza;
    private String nomeVincitore;
    private String[] listaAtleti = { "Luca", "Mario", "Matteo", "Piero", "Giorgio" };

    public Nuoto(String data, double puntata, String stile, int distanza,
            String nomeVincitore) {
        super(data, puntata);
        this.stile = stile;
        this.distanza = distanza;
        this.nomeVincitore = nomeVincitore;
    }

    public String getStile() {
        return stile;
    }

    public int getDistanza() {
        return distanza;
    }

    public String getNomeVincitore() {
        return nomeVincitore;
    }

    @Override
    public String toString() {
        return "Nuoto: " + super.toString() +
                "stile='" + stile + '\'' +
                ", distanza=" + distanza +
                ", nomeVincitore='" + nomeVincitore + '\'' +
                '}';
    }

    /**
     * Effettua la scommessa e calcola la vincita
     */
    @Override
    public void EffettuaScommessa() {
        int indexVinvitore = ThreadLocalRandom.current().nextInt(0, 4 + 1);
        if (nomeVincitore.equals(listaAtleti[indexVinvitore])) {
            setVincita(quota * puntata);
            setVinto(true);
        }
    }
}
