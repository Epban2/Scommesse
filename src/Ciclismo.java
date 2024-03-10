import java.util.concurrent.ThreadLocalRandom;

/**
 * Le scommesse sul ciclismo avranno i nomi (in ordine) dei primi tre ciclisti
 * al traguardo
 */
public class Ciclismo extends Scommessa {
    private String vincitoriInOrdine;
    //TODO: METTI A POSTO IL FATTO DI VINCITORI IN ORDINE PROBABILMENTE UTILIZZARE JCOMBO

    private String[] listaAtleti = { "Pietro", "Giacomo", "Mattia", "Giuseppe", "Sandro" }; 
    private String nomeVincitore;

    public Ciclismo(String data, double puntata, String vincitoriInOrdine, String nomeVincitore) {
        super(data, puntata);
        this.vincitoriInOrdine = vincitoriInOrdine;
        this.nomeVincitore = nomeVincitore;
    }

    public String getVincitoriInOrdine() {
        return vincitoriInOrdine;
    }

    public void setVincitoriInOrdine(String vincitoriInOrdine) {
        this.vincitoriInOrdine = vincitoriInOrdine;
    }

    @Override
    public String toString() {
        return "Ciclismo: " + super.toString() +
                "vincitoriInOrdine='" + vincitoriInOrdine + '\'';
    }

    /**
     * Effettua la scommessa e calcola la vincita
     */
    @Override
    public void EffettuaScommessa() {
        int indexVinvitore = ThreadLocalRandom.current().nextInt(0, 4 + 1);
        if (nomeVincitore.equals(listaAtleti[indexVinvitore])) { //TODO: METTERE A POSTO CON IL FATTO DEI VINCITORI IN ORDINE
            setVincita(quota * puntata);
            setVinto(true);
        }

    }
}
