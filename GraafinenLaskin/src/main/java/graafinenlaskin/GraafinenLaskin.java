package graafinenlaskin;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class GraafinenLaskin implements Runnable, ActionListener {

    private JFrame frame;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private JButton plus;
    private JButton miinus;
    private JButton nollaa;
    private Laskin laskin = new Laskin();

    @Override
    public void run() {

        frame = new JFrame("Laskin");
        frame.setPreferredSize(new Dimension(300, 150));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {

        frame.setLayout(new GridLayout(3, 1));
        tuloskentta = new JTextField("0");

        container.add(tuloskentta);
        tuloskentta.setEnabled(false);
        syotekentta = new JTextField("");
        container.add(syotekentta);

        plus = new JButton("+");
        miinus = new JButton("-");
        nollaa = new JButton("Z");

        tuloskentta.setName("tulos");
        syotekentta.setName("syote");
        plus.setName("+");
        miinus.setName("-");
        nollaa.setName("Z");

        plus.addActionListener(this);
        miinus.addActionListener(this);
        nollaa.addActionListener(this);

        nollaa.setEnabled(false);
        JPanel paneli = new JPanel(new GridLayout(1, 3));
        paneli.add(plus);
        paneli.add(miinus);
        paneli.add(nollaa);
        container.add(paneli);
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
