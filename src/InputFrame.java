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

    public JTextField jtfDataScommessa;
    public JTextField jtfPuntata;
    public JTextField jtfSquadraUno;
    public JTextField jtfSquadraDue;
    public JComboBox jcbRisultati;

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

        if (jComboBoxSport.getSelectedItem() == "Calcio" || jComboBoxSport.getSelectedItem() == "Basket")
            jbConferma.addActionListener(new AscoltaCalcioBasket());

        jpInput = new JPanel(new GridLayout(5, 2));
        CreareGUI();
    }

    private void CreareGUI() {
        this.setTitle("Input dati scommessa");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        if (jComboBoxSport.getSelectedItem() == "Calcio") {
            this.setIconImage(new ImageIcon("img/calcio.png").getImage());
            ScommessaCalcioBasket();
        } else if (jComboBoxSport.getSelectedItem() == "Basket") {
            this.setIconImage(new ImageIcon("img/basket.png").getImage());
            ScommessaCalcioBasket();
        } else if (jComboBoxSport.getSelectedItem() == "Nuoto") {
            this.setIconImage(new ImageIcon("img/nuoto.png").getImage());
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
     * Predispone il pannello alla scommessa di calcio e basket
     * imposta i textfield adeguati.
     */
    public void ScommessaCalcioBasket() {
        jpInput.add(new JLabel("Data scommessa:"));
        jtfDataScommessa = new JTextField();
        jpInput.add(jtfDataScommessa);

        jpInput.add(new JLabel("Puntata: "));
        jtfPuntata = new JTextField();
        jpInput.add(jtfPuntata);

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

            ArrayList<String> campi = new ArrayList<>();
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

            for (String str : campi) {
                if (str.isBlank() || str.isEmpty())
                    stringheCorrette = false;// controllo che tutti i campi non siano vuoti
            }

            if (stringheCorrette && numeroCorretto) {
                jlbAlert.setText("");
                data = jtfDataScommessa.getText();
                squadraUno = jtfSquadraUno.getText();
                squadraDue = jtfSquadraDue.getText();
                risultato = (String) jcbRisultati.getSelectedItem();
                jlbAlert.setForeground(Color.black);
                jlbAlert.setText("Scommessa effettuata con successo");

                if (jComboBoxSport.getSelectedItem() == "Calcio")
                    cliente.AggiungiScommessa("Calcio", data, puntata, squadraUno, squadraDue, risultato);
                else if (jComboBoxSport.getSelectedItem() == "Basket")
                    cliente.AggiungiScommessa("Basket", data, puntata, squadraUno, squadraDue, risultato);

                /**
                 * else if (...=="Atletica")...
                 * 
                 * TODO: 1. FARE IF CON ALTRI SPORT E ISTANZIARE LA SCOMMESSA PER ALTRE
                 * DISCIPLINE
                 * TODO: 2. CREARE IL METODO PER GENERARE I TEXTFIELD DI ALTRI SPORT
                 */

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
}