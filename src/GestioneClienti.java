import java.util.ArrayList;

public class GestioneClienti {
    public ArrayList<Cliente> clienti;

    public GestioneClienti() {
        clienti = new ArrayList<>();
    }

    /**
     * Aggiunge un cliente alla lista
     *
     * @param cliente
     */
    public void AggiungiCliente(Cliente cliente) {
        clienti.add(cliente);
    }

    /**
     * Ritorna la lista con i clienti
     *
     * @return
     */
    public String stampaClienti() {
        String str = "";
        for (Cliente cliente : clienti) {
            str += cliente.toString() + "\n";
        }
        return str;
    }

    /**
     * Ritorna un'array di Stringhe (String[]) con i nomi dei clienti da utilizzare
     * nel menu a cascata
     *
     * @return
     */
    public String[] ritornaProfili() {
        String[] nomiClienti = new String[4]; // array di stringhe
        String str = "";
        int count = 0;
        for (Cliente cliente : clienti) {
            str = cliente.ritornaNomeCompleto();
            nomiClienti[count] = str;
            count++;
        }
        return nomiClienti;
    }

    /**
     * Ritorna un stringa contentente tutte le puntate per ogni disciplina (sommando le singole di tutte i clienti) 
     * @return
     */
    public String ritornaScommesseTotaliPerTipologia() {
        int[] valori = new int[5];

        // Calcola il totale delle scommesse per ogni sport
        for (Cliente cliente : clienti) {
            valori[0] += cliente.getScommesseCalcio();
            valori[1] += cliente.getScommesseBasket();
            valori[2] += cliente.getScommesseCiclismo();
            valori[3] += cliente.getScommesseAtletica();
            valori[4] += cliente.getScommesseNuoto();
        }

        String text = "<html>"
                    + "Scommesse calcio: " + valori[0] + "<br>"
                    + "Scommesse basket: " + valori[1] + "<br>"
                    + "Scommesse atletica: " + valori[2] + "<br>"
                    + "Scommesse nuoto: " + valori[3] + "<br>"
                    + "Scommesse ciclismo: " + valori[4] + "<br>"
                    + "</html>";

        return text;
    }

}
