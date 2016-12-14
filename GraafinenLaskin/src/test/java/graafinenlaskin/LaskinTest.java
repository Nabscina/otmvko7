package graafinenlaskin;

import java.awt.Frame;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import org.assertj.swing.core.GenericTypeMatcher;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import org.junit.Test;

public class LaskinTest extends AssertJSwingJUnitTestCase {

    FrameFixture window;

    @Test
    public void tulosAlussaNolla() {
        window.textBox("tulos").requireText("0");
    }

    @Test
    public void luvunLisaysNolaanToimii() {

        window.textBox("syote").enterText("10");
        window.button("+").click();
        window.textBox("tulos").requireText("10");
    }

    @Test
    public void luvunVahennysToimii() {

        window.textBox("syote").enterText("10");
        window.button("-").click();
        window.textBox("tulos").requireText("-10");
    }

    @Test
    public void laskutoimitustenKomboAntaaOikeanVastauksen() {

        window.textBox("syote").enterText("25");
        window.button("-").click();
        window.textBox("tulos").requireText("-25");

        window.textBox("syote").enterText("10");
        window.button("+").click();
        window.textBox("tulos").requireText("-15");

        window.textBox("syote").enterText("100");
        window.button("+").click();
        window.textBox("tulos").requireText("85");

        window.textBox("syote").enterText("25");
        window.button("+").click();
        window.textBox("tulos").requireText("110");
    }

    @Test
    public void epavalidiaArvoaEiHuomioida() {

        window.textBox("syote").enterText("25");
        window.button("+").click();
        window.textBox("tulos").requireText("25");

        window.textBox("syote").enterText("kettu");
        window.button("+").click();
        window.textBox("syote").requireText("");

        window.textBox("tulos").requireText("25");
    }

    @Test
    public void zEiVoiKlikataAlussa() {

        assertFalse(window.button("Z").isEnabled());
        assertTrue(!window.button("Z").isEnabled());
    }

    @Test
    public void zVoiKlikataMyohemmin() {

        window.textBox("syote").enterText("5");
        window.button("+").click();

        assertTrue(window.button("Z").isEnabled());
    }

    @Test
    public void zKlikkaaminenNollaaTuloskentan() {

        window.textBox("syote").enterText("21");
        window.button("-").click();
        window.textBox("tulos").requireText("-21");

        window.button("Z").click();
        window.textBox("tulos").requireText("0");
    }

    @Test
    public void zEiVoiKlikataKunTuloskentassa0() {

        window.textBox("syote").enterText("21");
        window.button("-").click();

        assertTrue(window.button("Z").isEnabled());

        window.textBox("syote").enterText("21");
        window.button("+").click();

        assertFalse(window.button("Z").isEnabled());

        window.textBox("syote").enterText("0");
        window.button("+").click();

        assertFalse(window.button("Z").isEnabled());
    }

    @Test
    public void syotekenttaTyhjeneeKunKlikataan() {

        window.textBox("syote").enterText("10");
        window.button("+").click();
        window.textBox("syote").requireText("");

        window.textBox("syote").enterText("10");
        window.button("-").click();
        window.textBox("syote").requireText("");

        window.textBox("syote").enterText("10");
        window.button("+").click();
        window.button("Z").click();
        window.textBox("syote").requireText("");
    }

    @Override
    protected void onSetUp() {
        application(Main.class).start();

        window = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).using(robot());
    }
}
