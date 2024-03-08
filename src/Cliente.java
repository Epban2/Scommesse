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
        return "Cliente: " +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", scommesseEffettuate=" + scommesseEffettuate +
                ", puntataTotale=" + puntataTotale +
                ", puntataCalcio=" + puntataCalcio +
                ", puntataBasket=" + puntataBasket +
                ", puntataCiclismo=" + puntataCiclismo +
                ", puntataAtletica=" + puntataAtletica +
                ", puntataNuoto=" + puntataNuoto;
    }

    /**
     * Aggiorna la puntata totale sommando tutte le singole
     */
    public void calcolaPuntataTotale() {
        double somma = 0;
        somma = puntataCalcio + puntataBasket + puntataNuoto + puntataCiclismo + puntataAtletica;
        setPuntataTotale(somma);
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
     * L'idea è che prenda il string tipoScommessa dal jbombox con il tipo (dopo che
     * si preme conferma)
     * solo che non so come fare con gli input.
     * forse devo mettere un costruttore vuoto solo per istanziare, poi passo i
     * setter con=
     * il secondo frame di input
     *
     * @param tipoScommessa
     */
    public void AggiungiScommessa(String tipoScommessa, String data, double puntataCliente, String squadraUno,
            String squadraDue,
            String risultatoPrevisto) {
        Scommessa scommessa = null;
        if (tipoScommessa == "Calcio")
            scommessa = new Calcio(data, puntataCliente, squadraUno, squadraDue, risultatoPrevisto);
        /**
         * else if (tipoScommessa == "Atletica")
         * scommessa = new Atletica();
         * else if (tipoScommessa == "Nuoto")
         * scommessa = new Nuoto();
         * else if (tipoScommessa == "Ciclismo")
         * scommessa = new Ciclismo();
         */
        else
            scommessa = new Basket(data, puntataCliente, squadraUno, squadraDue, risultatoPrevisto);

        listaScommesse.add(scommessa);
        setScommesseEffettuate(scommesseEffettuate + 1);
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
