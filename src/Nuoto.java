import java.util.concurrent.ThreadLocalRandom;

/**
 * Le scommesse su nuoto avranno stile, distanza e nome del vincitore.
 */
public class Nuoto extends Scommessa {
    private String stile;
    private Double distanza;
    private String nomeVincitore;
    String[] atletiNuoto = { "Tom Dean", "Carson Foster", "Pan Zhanle", "Ryan Murphy", " Maxime Grousset",
            "Bobby Finke", "Sam Short" };

    public Nuoto(String data, double puntata, String stile, Double distanza,
            String nomeVincitore) {
        super(data, puntata);
        this.stile = stile;
        this.distanza = distanza;
        this.nomeVincitore = nomeVincitore;
        EffettuaScommessa();
    }

    public String getStile() {
        return stile;
    }

    public double getDistanza() {
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
        // genero casualmente l'indice del vincitore (locazione dell'array)
        int indexVinvitore = ThreadLocalRandom.current().nextInt(0, 6 + 1);
        if (nomeVincitore.equals(atletiNuoto[indexVinvitore])) {
            setVincita(quota * puntata);
            setVinto(true);
        }
    }
}
