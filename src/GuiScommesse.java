import javax.accessibility.AccessibleIcon;
import javax.swing.*;

import org.w3c.dom.views.DocumentView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiScommesse extends JFrame {
    private GestioneClienti gestioneClienti;
    private JPanel jpTitolo;
    private JPanel jpElenchiCascata;
    private JLabel jlbTitolo;
    public JComboBox jcbClienti;
    public JComboBox jcbSport;
    public JButton jbConfermaJcombo;
    public JLabel jlbTestoCentrale;
    public JPanel jpBottoniLaterali;
    public JPanel jpCentrale;
    public JLabel[] jlbEtichette = new JLabel[5];
    public JButton jbUltimaScommessa;
    private JButton jbPuntateCliente;
    public JButton jbPuntatePerSport;
    public JButton jbPiuVincente;

    public GuiScommesse(GestioneClienti gestioneClienti) {
        this.setSize(800, 800);
        this.setLocationRelativeTo(null); // Finestra al centro dello schermo.
        this.setIconImage(new ImageIcon("img/sisal.png").getImage()); // immagine della scheda
        this.gestioneClienti = gestioneClienti;

        // titolo (top)
        jpTitolo = new JPanel();
        jlbTitolo = new JLabel();

        jlbTitolo.setText("Centro Scommesse La Rocca");
        jlbTitolo.setFont(new Font("Futura", Font.BOLD, 30));
        jlbTitolo.setHorizontalAlignment(JLabel.CENTER);
        jpTitolo.add(jlbTitolo);

        // Cascata
        jpElenchiCascata = new JPanel();
        jpElenchiCascata.setLayout(new FlowLayout());

        String[] listaNomi = gestioneClienti.ritornaProfili(); // richiamo il metodo
        String[] elencoSport = { "Calcio", "Basket", "Ciclismo", "Atletica", "Nuoto" };

        jcbClienti = new JComboBox(listaNomi);
        jcbClienti.addActionListener(new AscoltaJcombo());

        jcbSport = new JComboBox(elencoSport);
        jcbSport.addActionListener(new AscoltaJcombo());

        jpElenchiCascata.add(jcbClienti);
        jpElenchiCascata.add(jcbSport);

        // Bottone che apre il nuovo frame
        jbConfermaJcombo = new JButton();
        jbConfermaJcombo.setText("Conferma");
        jbConfermaJcombo.addActionListener(new ConfermaJcombo());
        jbConfermaJcombo.setFocusPainted(false);
        jpElenchiCascata.add(jbConfermaJcombo);

        // bottoni laterali
        jpBottoniLaterali = new JPanel(new GridLayout(4, 0)); // pannello
        jbUltimaScommessa = new JButton(); // bottone 1
        jbUltimaScommessa.setText("Esito ultima scommessa");
        jbUltimaScommessa.setFocusPainted(false);
        jbUltimaScommessa.setContentAreaFilled(false);
        jbUltimaScommessa.setBorderPainted(false);
        jbUltimaScommessa.addActionListener(new AscoltaUltimaScommessa());
        jpBottoniLaterali.add(jbUltimaScommessa);

        jbPuntateCliente = new JButton(); // bottone 2
        jbPuntateCliente.setText("Cifra totale puntata cliente");
        jbPuntateCliente.setFocusPainted(false);
        jbPuntateCliente.setContentAreaFilled(false);
        jbPuntateCliente.setBorderPainted(false);
        jpBottoniLaterali.add(jbPuntateCliente);
        jbPuntateCliente.addActionListener(new AscoltaPuntateCliente());

        /**
         * jbPuntatePerSport = new JButton(); // bottone 3
         * jbPuntatePerSport.setText("Puntate per sport");
         * jbPuntatePerSport.setFocusPainted(false);
         * jbPuntatePerSport.setContentAreaFilled(false);
         * jbPuntatePerSport.setBorderPainted(false);
         * jpBottoniLaterali.add(jbPuntatePerSport);
         * jbPuntatePerSport.addActionListener(new AscoltaPuntatePerTipologia());
         */
        jbPiuVincente = new JButton(); // bottone 4
        jbPiuVincente.setText("Cliente piu' vincente");
        jbPiuVincente.setFocusPainted(false);
        jbPiuVincente.setContentAreaFilled(false);
        jbPiuVincente.setBorderPainted(false);
        jpBottoniLaterali.add(jbPiuVincente);
        jbPiuVincente.addActionListener(new AscoltaPiuVincente());
        // testo centrale (risultato dei bottoni laterali es. ultima scommessa)
        jpCentrale = new JPanel(new GridLayout(5, 0));

        jlbTestoCentrale = new JLabel();
        jlbTestoCentrale.setFont(new Font("Futura", Font.BOLD, 20));
        jlbTestoCentrale.setHorizontalAlignment(JLabel.CENTER);
        jpCentrale.add(jlbTestoCentrale);

        // aggiungo tutto al frame
        this.add(jpTitolo, BorderLayout.NORTH);
        this.add(jpCentrale, BorderLayout.CENTER);
        this.add(jpElenchiCascata, BorderLayout.SOUTH);
        this.add(jpBottoniLaterali, BorderLayout.EAST);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class AscoltaJcombo implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // testoAlCentro.setText((String) jcbClienti.getSelectedItem());
        }
    }

    /**
     * Chiamo il secondo frame passato un cliente e il Jcombo degli sport
     */
    class ConfermaJcombo implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Cliente clienteInput = ritornaCliente();
            new InputFrame(clienteInput, jcbSport);
        }
    }

    /**
     * Ritorna il cliente selezionato nel JComboBox
     * 
     * @return
     */
    public Cliente ritornaCliente() {
        Cliente clienteInput = null;
        for (int i = 0; i < 4; i++) {
            if (gestioneClienti.clienti.get(i).ritornaNomeCompleto().equals(jcbClienti.getSelectedItem()))
                clienteInput = gestioneClienti.clienti.get(i);
        }
        return clienteInput;
    }

    /**
     * Stampa se l’ultima scommessa del cliente è stata vinta o no
     */
    class AscoltaUltimaScommessa implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Cliente cliente = ritornaCliente();
            Scommessa scommessa = null;
            if (cliente.listaScommesse.size() > 0) {
                scommessa = cliente.listaScommesse.get(cliente.listaScommesse.size() - 1);
                if (scommessa.isVinto())
                    jlbTestoCentrale.setText("L'ultima scommessa e' stata vinta!");
                else
                    jlbTestoCentrale.setText("L'ultima scommessa e' stata persa!");

            } else
                jlbTestoCentrale.setText("L'utente non ha ancora mai scommmesso!");
        }
    }

    /**
     * Per ogni cliente la cifra totale puntata
     */
    class AscoltaPuntateCliente implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Cliente cliente = ritornaCliente();
            Double totalePuntate = 0.0;
            for (Scommessa scommessa : cliente.listaScommesse) {
                totalePuntate += scommessa.puntata;
            }
            jlbTestoCentrale.setText("Puntate totali= " + Double.toString(totalePuntate) + "$");
        }
    }

    /**
     * TODO: METTI A POSTO
     * Puntata per ciascuna tipologia di sport
     *
     * class AscoltaPuntatePerTipologia implements ActionListener {
     * 
     * @Override
     *           public void actionPerformed(ActionEvent e) {
     *           Cliente cliente = ritornaCliente();
     *           Double puntateCalcio = 0.0;
     *           Double puntateBasket = 0.0;
     *           Double puntateNuoto = 0.0;
     *           Double puntateAtletica = 0.0;
     *           Double puntateCiclismo = 0.0;
     * 
     *           for (Scommessa scommessa : cliente.listaScommesse) {
     *           if (scommessa instanceof Calcio)
     *           puntateCalcio += scommessa.puntata;
     *           else if (scommessa instanceof Basket)
     *           puntateBasket += scommessa.puntata;
     *           else if (scommessa instanceof Atletica)
     *           puntateAtletica += scommessa.puntata;
     *           else if (scommessa instanceof Nuoto)
     *           puntateNuoto += scommessa.puntata;
     *           else
     *           puntateCiclismo += scommessa.puntata;
     *           }
     * 
     *           String[] sport = { "Puntate Calcio: " + puntateCalcio, "Puntate
     *           Basket: " + puntateBasket,
     *           "Puntate Atletica: " + puntateAtletica, "Puntate Nuoto: " +
     *           puntateNuoto,
     *           "Puntate Ciclismo: " + puntateCiclismo };
     * 
     *           jpCentrale.removeAll();
     *           for (int i = 0; i < sport.length; i++) {
     *           JLabel label = new JLabel(sport[i]);
     *           label.setFont(new Font("Futura", Font.BOLD, 18));
     *           label.setHorizontalAlignment(JLabel.CENTER);
     *           jpCentrale.add(label);
     *           }
     *           jpCentrale.revalidate();
     *           jpCentrale.repaint();
     *           }
     *           }
     */

    /**
     * Il cliente che ha vinto più soldi
     * 
     */
    class AscoltaPiuVincente implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Cliente clientePiuVincente = gestioneClienti.clienti.get(0);
            for (Cliente clienteIndice : gestioneClienti.clienti) {
                if (clienteIndice.ritornaVincita() > clientePiuVincente.ritornaVincita())
                    clientePiuVincente = clienteIndice;
            }
            if (clientePiuVincente.ritornaVincita() == 0)
                jlbTestoCentrale.setText("Nessun cliente ha vinto!");
            else {
                Double vincitaArrotondata = Math.round((clientePiuVincente.ritornaVincita()) * 100.0) / 100.0;
                jlbTestoCentrale.setText("Cliente piu' vincente: " + clientePiuVincente.ritornaNomeCompleto() + " ("
                        + Double.toString(vincitaArrotondata) + "$)");
            }
        }
    }

}
