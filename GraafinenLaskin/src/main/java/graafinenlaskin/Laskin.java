package graafinenlaskin;

public class Laskin {

    private int arvo;

    public Laskin() {

        arvo = 0;
    }

    public int arvo() {

        return arvo;
    }

    public void summa(int a) {

        arvo += a;
    }

    public void erotus(int a) {

        arvo -= a;
    }

    public void nollaa() {

        arvo = 0;
    }

}
