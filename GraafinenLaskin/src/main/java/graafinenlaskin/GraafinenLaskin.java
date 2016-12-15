package graafinenlaskin;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class GraafinenLaskin implements Runnable {

    private JFrame frame;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private JButton plus;
    private JButton miinus;
    private JButton nollaa;

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

        asetaNimet();

        TapahtumanKasittelija tk = new TapahtumanKasittelija(tuloskentta, syotekentta, plus, miinus, nollaa);

        plus.addActionListener(tk);
        miinus.addActionListener(tk);
        nollaa.addActionListener(tk);

        nollaa.setEnabled(false);
        container.add(lisaaJPanel());
    }

    public JPanel lisaaJPanel() {

        JPanel paneli = new JPanel(new GridLayout(1, 3));
        paneli.add(plus);
        paneli.add(miinus);
        paneli.add(nollaa);

        return paneli;
    }

    public void asetaNimet() {

        tuloskentta.setName("tulos");
        syotekentta.setName("syote");
        plus.setName("+");
        miinus.setName("-");
        nollaa.setName("Z");
    }
}
