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
        String[] nomiClienti = new String[4]; //array di stringhe
        String str = "";
        int count = 0;
        for (Cliente cliente : clienti) {
            str = cliente.ritornaNomeCompleto();
            nomiClienti[count] = str;
            count++;
        }
        return nomiClienti;
    }

}
