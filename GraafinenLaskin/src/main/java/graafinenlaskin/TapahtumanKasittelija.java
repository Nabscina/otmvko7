package graafinenlaskin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

public class TapahtumanKasittelija implements ActionListener {

    private JTextField tuloskentta;
    private JTextField syotekentta;
    private JButton plus;
    private JButton miinus;
    private JButton nollaa;
    private Laskin laskin;

    public TapahtumanKasittelija(JTextField tulos, JTextField syote, JButton plus, JButton miinus, JButton nollaa, Laskin laskin) {

        this.tuloskentta = tulos;
        this.syotekentta = syote;
        this.plus = plus;
        this.miinus = miinus;
        this.nollaa = nollaa;
        this.laskin = laskin;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        int syote = 0;

        try {
            syote = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }

        if (ae.getSource() == plus) {
            laskin.summa(syote);
        } else if (ae.getSource() == miinus) {
            laskin.erotus(syote);
        } else {
            laskin.nollaa();
        }

        syotekentta.setText("");
        tuloskentta.setText("" + laskin.arvo());
        if (laskin.arvo() == 0) {
            nollaa.setEnabled(false);
        } else {
            nollaa.setEnabled(true);
        }
    }
}
