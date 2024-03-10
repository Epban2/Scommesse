import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputFrame extends JFrame {
    private Cliente cliente;
    private JComboBox jComboBoxSport;

    private JLabel jlbTitolo;
    public JLabel jlbAlert;

    private JPanel jpInput;
    private JButton jbConferma;
    // JTF per tutte le scommesse
    public JTextField jtfDataScommessa;
    public JTextField jtfPuntata;
    // calcio/basket
    public JTextField jtfSquadraUno;
    public JTextField jtfSquadraDue;
    public JComboBox jcbRisultati;
    // nuoto
    public JComboBox jcbStili;
    public JTextField jtfDistanza;
    public JComboBox jcbAteltiNuoto;

    public InputFrame(Cliente cliente, JComboBox jComboBoxSport) {
        this.cliente = cliente;
        this.jComboBoxSport = jComboBoxSport;

        this.jlbTitolo = new JLabel();
        jlbTitolo.setText("Inserimento dati");
        jlbTitolo.setFont(new Font("Futura", Font.BOLD, 25));
        jlbTitolo.setHorizontalAlignment(JLabel.CENTER);

        jlbAlert = new JLabel();
        jlbAlert.setFont(new Font("Futura", Font.BOLD, 15));
        jlbAlert.setHorizontalAlignment(JLabel.CENTER);

        jbConferma = new JButton();
        jbConferma.setFocusPainted(false);
        jbConferma.setText("Conferma");

        // assegno l'ascoltatore correto in base allo sport selezionato
        if (jComboBoxSport.getSelectedItem() == "Calcio" || jComboBoxSport.getSelectedItem() == "Basket")
            jbConferma.addActionListener(new AscoltaCalcioBasket());

        else if (jComboBoxSport.getSelectedItem() == "Nuoto")
            jbConferma.addActionListener(new AscoltaNuoto());/** ... altri if per ciclismo etc.. */

        jpInput = new JPanel(new GridLayout(5, 2));
        CreareGUI();
    }

    private void CreareGUI() {
        this.setTitle("Input dati scommessa");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);

        jpInput.add(new JLabel("Data scommessa:")); // tutti i tipi di scommessa hanno data e puntata
        jtfDataScommessa = new JTextField();
        jpInput.add(jtfDataScommessa);
        jpInput.add(new JLabel("Puntata: "));
        jtfPuntata = new JTextField();
        jpInput.add(jtfPuntata);

        if (jComboBoxSport.getSelectedItem() == "Calcio") {
            this.setIconImage(new ImageIcon("img/calcio.png").getImage());
            ScommessaCalcioBasket();
        } else if (jComboBoxSport.getSelectedItem() == "Basket") {
            this.setIconImage(new ImageIcon("img/basket.png").getImage());
            ScommessaCalcioBasket();
        } else if (jComboBoxSport.getSelectedItem() == "Nuoto") {
            this.setIconImage(new ImageIcon("img/nuoto.png").getImage());
            ScommessaNuoto();
        } else if (jComboBoxSport.getSelectedItem() == "Atletica") {
            this.setIconImage(new ImageIcon("img/atletica.png").getImage());
        } else
            this.setIconImage(new ImageIcon("img/ciclismo.png").getImage());

        this.add(jlbAlert, BorderLayout.SOUTH);
        this.add(jpInput, BorderLayout.CENTER);
        this.add(jlbTitolo, BorderLayout.NORTH);
        this.add(jbConferma, BorderLayout.EAST);
        this.setVisible(true);

    }

    /**
     * TODO: INTERFACCIE PER ATLETICA E CICLISMO
     * TODO: CREARE ASCOLTATORI PER ATLETICA E CICLISMO
     */

    /**
     * Predispone il pannello alla scommessa di calcio e basket
     * imposta i textfield adeguati.
     */
    public void ScommessaCalcioBasket() {
        jpInput.add(new JLabel("Nome squadra uno: "));
        jtfSquadraUno = new JTextField();
        jpInput.add(jtfSquadraUno);

        jpInput.add(new JLabel("Nome squadra due: "));
        jtfSquadraDue = new JTextField();
        jpInput.add(jtfSquadraDue);

        jpInput.add(new JLabel("Scegliere risultato: "));
        String[] risultati = { "1", "x", "2" };
        jcbRisultati = new JComboBox(risultati);
        jpInput.add(jcbRisultati);
    }

    /**
     * Predispone il pannello alla scommessa di calcio e basket (solamente grafica)
     * imposta i textfield adeguati.
     * 
     * campi costruttore nuoto: String data, double puntata, String stile, int
     * distanza,
     * String nomeVincitore
     */
    public void ScommessaNuoto() {
        jpInput.add(new JLabel("Stile: "));
        String[] stili = { "Libero", "Dorso", "Rana", "Farfalla" };
        jcbStili = new JComboBox(stili);
        jpInput.add(jcbStili);

        jpInput.add(new JLabel("Distana (m): "));
        jtfDistanza = new JTextField();
        jpInput.add(jtfDistanza);

        jpInput.add(new JLabel("Vincitore: "));
        String[] atletiNuoto = { "Tom Dean", "Carson Foster", "Pan Zhanle", "Ryan Murphy", " Maxime Grousset",
                "Bobby Finke", "Sam Short" };
        jcbAteltiNuoto = new JComboBox(atletiNuoto);
        jpInput.add(jcbAteltiNuoto);

    }

    /**
     * Metodo che scorre la lista con le stringhe di input
     * 
     * @param lista
     * @return boolean
     */
    public boolean controllaStringhe(ArrayList<String> lista) {
        boolean flag = true;
        for (String str : lista) {
            if (str.isBlank() || str.isEmpty())
                flag = false;// controllo che tutti i campi non siano vuoti scorrendo l'array
        }
        return flag;
    }

    /**
     * Ascoltatore che effettua la scommessa per calcio/Basket prendendo i parametri
     * dei JTextField
     */
    class AscoltaCalcioBasket implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String data = null;
            String squadraUno = null;
            String squadraDue = null;
            String risultato = null;
            Double puntata = null;
            boolean stringheCorrette = true;
            boolean numeroCorretto = true;

            ArrayList<String> campi = new ArrayList<>(); // aggiungo all'arrayList per controllare in un for
            campi.add(jtfDataScommessa.getText());
            campi.add(jtfPuntata.getText());
            campi.add(jtfSquadraUno.getText());
            campi.add(jtfSquadraDue.getText());

            try {
                if (Double.parseDouble(jtfPuntata.getText()) >= 1) {
                    puntata = Double.parseDouble(jtfPuntata.getText());
                    numeroCorretto = true;

                } else {
                    jlbAlert.setText("LA PUNTATA MINIMA è DI 1€");
                    numeroCorretto = false;
                }
            } catch (Exception ignored) {
                numeroCorretto = false; // modifico il flag
            }

            stringheCorrette = controllaStringhe(campi);

            if (stringheCorrette && numeroCorretto) {
                jlbAlert.setText("");
                data = jtfDataScommessa.getText();
                squadraUno = jtfSquadraUno.getText();
                squadraDue = jtfSquadraDue.getText();
                risultato = (String) jcbRisultati.getSelectedItem();
                jlbAlert.setForeground(Color.black);
                jlbAlert.setText("Scommessa effettuata con successo");

                if (jComboBoxSport.getSelectedItem() == "Calcio")
                    cliente.AggiungiScommessaCalcioBasket("Calcio", data, puntata, squadraUno, squadraDue, risultato);
                else if (jComboBoxSport.getSelectedItem() == "Basket")
                    cliente.AggiungiScommessaCalcioBasket("Basket", data, puntata, squadraUno, squadraDue, risultato);

            } else if (!stringheCorrette && numeroCorretto) {
                jlbAlert.setText("ALCUNI CAMPI SONO VUOTI");
                jlbAlert.setForeground(Color.RED);
            } else if (!numeroCorretto && stringheCorrette) {
                jlbAlert.setText("LA PUNTATA MINIMA è DI 1€");
                jlbAlert.setForeground(Color.RED);
            } else if (!numeroCorretto && !stringheCorrette) {
                jlbAlert.setText("CORREGGERE I CAMPI");
                jlbAlert.setForeground(Color.RED);
            }
        }
    }

    

    /**
     * Ascoltatore che effettua la scommessa per il nuoto prendendo i parametri
     * dei JTextField
     */
    class AscoltaNuoto implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String data;
            Double puntata = null;
            String stile;
            Double distanza = null;
            String nomeVincitore;

            boolean stringheCorrette = true;
            boolean puntantaCorretta = true;
            boolean distanzaCorretta = true;

            ArrayList<String> campi = new ArrayList<>();
            campi.add(jtfDataScommessa.getText());
            campi.add(jtfPuntata.getText());
            stringheCorrette = controllaStringhe(campi); // true false

            try { // puntata
                if (Double.parseDouble(jtfPuntata.getText()) >= 1) {
                    puntata = Double.parseDouble(jtfPuntata.getText());
                    puntantaCorretta = true;

                } else {
                    jlbAlert.setText("LA PUNTATA MINIMA è DI 1€");
                    puntantaCorretta = false;
                }
            } catch (Exception ignored) {
                puntantaCorretta = false; // modifico il flag
            }

            try { // distanza (m)
                if (Double.parseDouble(jtfDistanza.getText()) >= 50
                        && Double.parseDouble(jtfDistanza.getText()) <= 1500) {
                    puntata = Double.parseDouble(jtfPuntata.getText());
                    distanzaCorretta = true;
                } else if (Double.parseDouble(jtfDistanza.getText()) < 50) {
                    jlbAlert.setText("LA DISTANZA MINIMA è 50m");
                    jlbAlert.setForeground(Color.RED);
                    distanzaCorretta = false;
                } else {
                    jlbAlert.setForeground(Color.RED);
                    jlbAlert.setText("LA DISTANZA MASSIMA è 1500m");
                    distanzaCorretta = false;
                }
            } catch (Exception ignored) {
                distanzaCorretta = false;
            }

            // se e' tutto giusto effettua la scommessa
            if (stringheCorrette && distanzaCorretta && puntantaCorretta) {
                jlbAlert.setText(""); // svuoto la label di errore
                data = jtfDataScommessa.getText();
                stile = (String) jcbStili.getSelectedItem();
                nomeVincitore = (String) jcbAteltiNuoto.getSelectedItem();
                jlbAlert.setForeground(Color.black);
                jlbAlert.setText("Scommessa effettuata con successo");
                cliente.AggiungiScommessaNuoto(data, puntata, stile, distanza, nomeVincitore);

            } else if (!stringheCorrette && distanzaCorretta && puntantaCorretta) {
                jlbAlert.setText("ALCUNI CAMPI SONO VUOTI");
                jlbAlert.setForeground(Color.RED);
            } else if (!distanzaCorretta && !stringheCorrette && !puntantaCorretta) {
                jlbAlert.setText("CORREGGERE I CAMPI");
                jlbAlert.setForeground(Color.RED);
            }
        }
    }

}