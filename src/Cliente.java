import java.util.ArrayList;

public class Cliente {
    private String nome;
    private String cognome;
    public ArrayList<Scommessa> listaScommesse;
    private int scommesseEffettuate = 0;
    private double puntataTotale = 0;
    private double puntataCalcio = 0;
    private double puntataBasket = 0;
    private double puntataCiclismo = 0;
    private double puntataAtletica = 0;
    private double puntataNuoto = 0;

    private int scommesseCalcio = 0;
    private int scommesseBasket = 0;
    private int scommesseCiclismo = 0;
    private int scommesseNuoto = 0;
    private int scommesseAtletica = 0;

    public Cliente(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
        listaScommesse = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getScommesseEffettuate() {
        return scommesseEffettuate;
    }

    public void setScommesseEffettuate(int scommesseEffettuate) {
        this.scommesseEffettuate = scommesseEffettuate;
    }

    public double getPuntataTotale() {
        return puntataTotale;
    }

    public ArrayList<Scommessa> getListaScommesse() {
        return listaScommesse;
    }

    public void setListaScommesse(ArrayList<Scommessa> listaScommesse) {
        this.listaScommesse = listaScommesse;
    }

    public int getScommesseCalcio() {
        return scommesseCalcio;
    }

    public void setScommesseCalcio(int scommesseCalcio) {
        this.scommesseCalcio = scommesseCalcio;
    }

    public int getScommesseBasket() {
        return scommesseBasket;
    }

    public void setScommesseBasket(int scommesseBasket) {
        this.scommesseBasket = scommesseBasket;
    }

    public int getScommesseCiclismo() {
        return scommesseCiclismo;
    }

    public void setScommesseCiclismo(int scommesseCiclismo) {
        this.scommesseCiclismo = scommesseCiclismo;
    }

    public int getScommesseNuoto() {
        return scommesseNuoto;
    }

    public void setScommesseNuoto(int scommesseNuoto) {
        this.scommesseNuoto = scommesseNuoto;
    }

    public int getScommesseAtletica() {
        return scommesseAtletica;
    }

    public void setScommesseAtletica(int scommesseAtletica) {
        this.scommesseAtletica = scommesseAtletica;
    }

    public void setPuntataTotale(double puntataTotale) {
        this.puntataTotale = puntataTotale;
    }

    public double getPuntataCalcio() {
        return puntataCalcio;
    }

    public void setPuntataCalcio(double puntataCalcio) {
        this.puntataCalcio = puntataCalcio;
    }

    public double getPuntataBasket() {
        return puntataBasket;
    }

    public void setPuntataBasket(double puntataBasket) {
        this.puntataBasket = puntataBasket;
    }

    public double getPuntataCiclismo() {
        return puntataCiclismo;
    }

    public void setPuntataCiclismo(double puntataCiclismo) {
        this.puntataCiclismo = puntataCiclismo;
    }

    public double getPuntataAtletica() {
        return puntataAtletica;
    }

    public void setPuntataAtletica(double puntataAtletica) {
        this.puntataAtletica = puntataAtletica;
    }

    public double getPuntataNuoto() {
        return puntataNuoto;
    }

    public void setPuntataNuoto(double puntataNuoto) {
        this.puntataNuoto = puntataNuoto;
    }

    @Override
    public String toString() {
        return "Cliente [nome=" + nome + ", cognome=" + cognome + ", listaScommesse=" + listaScommesse
                + ", scommesseEffettuate=" + scommesseEffettuate + ", puntataTotale=" + puntataTotale
                + ", puntataCalcio=" + puntataCalcio + ", puntataBasket=" + puntataBasket + ", puntataCiclismo="
                + puntataCiclismo + ", puntataAtletica=" + puntataAtletica + ", puntataNuoto=" + puntataNuoto
                + ", scommesseCalcio=" + scommesseCalcio + ", scommesseBasket=" + scommesseBasket
                + ", scommesseCiclismo=" + scommesseCiclismo + ", scommesseNuoto=" + scommesseNuoto
                + ", scommesseAtletica=" + scommesseAtletica + "]";
    }

    /**
     * Ritorna la somma di tutte le singole puntate
     * 
     * @return
     */
    public Double ritornaPuntataTotale() {
        return puntataTotale;
    }

    /**
     * Utilizzato per il controllo del cliente tramite il JcomBox
     * 
     * @return
     */
    public String ritornaNomeCompleto() {
        return this.nome + " " + this.cognome;
    }

    /**
     * Ritorna una stringa con tutte le puntate
     * 
     * @return
     */
    public String riepilogoScommesse() {
        String text = "<html>"
                + "Puntate totali: " + puntataTotale + "<br>"
                + "Puntate calcio: " + puntataCalcio + "<br>"
                + "Puntate basket: " + puntataBasket + "<br>"
                + "Puntate atletica: " + puntataAtletica + "<br>"
                + "Puntate nuoto: " + puntataNuoto + "<br>"
                + "Puntate ciclismo: " + puntataCiclismo + "<br>"
                + "</html>";
        return text;
    }

    /**
     * Crea una nuova scommessa di calcio/basket a seconda del tipo (Stringa
     * contentente lo sport)
     * 
     * @param tipoScommessa
     * @param data
     * @param puntataCliente
     * @param squadraUno
     * @param squadraDue
     * @param risultatoPrevisto
     */
    public void aggiungiScommessaCalcioBasket(String tipoScommessa, String data, double puntataCliente,
            String squadraUno,
            String squadraDue,
            String risultatoPrevisto) {
        Scommessa scommessa = null;
        if (tipoScommessa == "Calcio") {
            scommessa = new Calcio(data, puntataCliente, squadraUno, squadraDue, risultatoPrevisto);
            setScommesseCalcio(scommesseCalcio + 1);
        } else {
            scommessa = new Basket(data, puntataCliente, squadraUno, squadraDue, risultatoPrevisto);
            setScommesseBasket(scommesseBasket + 1);
        }
        listaScommesse.add(scommessa);
        setScommesseEffettuate(scommesseEffettuate + 1);
        setPuntataTotale(puntataTotale + puntataCliente);
        setPuntataCalcio(puntataCalcio + puntataCliente);
    }

    /**
     * Con i parametri dei JTF crea una scommessa di tipo Nuoto
     * 
     * @param data
     * @param puntataCliente
     * @param stile
     * @param distanza
     * @param nomeVincitore
     */
    public void aggiungiScommessaNuoto(String data, double puntataCliente, String stile, Double distanza,
            String nomeVincitore) {
        Scommessa scommessa = new Nuoto(data, puntataCliente, stile, distanza, nomeVincitore);
        listaScommesse.add(scommessa);
        setScommesseEffettuate(scommesseEffettuate + 1);
        setPuntataTotale(puntataTotale + puntataCliente);
        setPuntataNuoto(puntataNuoto + puntataCliente);
        setScommesseNuoto(scommesseNuoto + 1);
    }

    /**
     * Con i parametri dei JTF crea una scommessa di tipo Nuoto
     * 
     * @param data
     * @param puntataCliente
     * @param stile
     * @param distanza
     * @param nomeVincitore
     */
    public void aggiungiScommessaAtletica(String data, double puntata, String specialita, String nomeVincitore) {
        Scommessa scommessa = new Atletica(data, puntata, specialita, nomeVincitore);
        listaScommesse.add(scommessa);
        setScommesseEffettuate(scommesseEffettuate + 1);
        setPuntataTotale(puntataTotale + puntata);
        setPuntataAtletica(puntataAtletica + puntata);
        setScommesseAtletica(scommesseAtletica + 1);
    }

    /**
     * Con i parametri dei JTF crea una scommessa di tipo Ciclismo
     */
    public void aggiungiScommessaCiclismo(String data, double puntata, String nomeVincitore) {
        Scommessa scommessa = new Ciclismo(data, puntata, nomeVincitore);
        listaScommesse.add(scommessa);
        setScommesseEffettuate(scommesseEffettuate + 1);
        setPuntataTotale(puntataTotale + puntata);
        setPuntataCiclismo(puntataCiclismo + puntata);
        setScommesseCiclismo(scommesseCiclismo + 1);
    }

    /**
     * Ritorna la vincita totale di tutte le scommesse effettuate
     * 
     * @return
     */
    public double ritornaVincita() {
        double totale = 0;
        for (Scommessa scommessa : listaScommesse) {
            totale += scommessa.vincita;
        }
        return totale;
    }
}
