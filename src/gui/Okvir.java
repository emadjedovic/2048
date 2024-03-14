package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import save.SpasiStanje;
import save.StanjeIgre;
import score.Score;

/**
 * Klasa koja predstavlja glavni prozor igre "2048".
 * Nasljedjuje JFrame i implementira graficki prikaz igre.
 */
public class Okvir extends JFrame {

	/**
	 * Jedinstveni identifikator za serializaciju.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Objekt klase GUI.
     */
    private GUI gui;

    /**
     * Panel za prikaz igre.
     */
    private Panel panel;

    /**
     * Traka sa score-om.
     */
    private Traka traka;

    /**
     * Objekt klase Score.
     */
    private Score score;

    /**
     * Konstruktor klase Okvir.
     *
     * @param g Objekat klase GUI.
     * @param s Objekat klase Score.
     */
    public Okvir(GUI g, Score s) {

        this.gui = g;
        this.score = s;

        setTitle("2048 | Ema Djedovic");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        dodajElemente();

        setSize(new Dimension(400, 500));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        addKeyListener(gui);
        setFocusable(true);
        requestFocusInWindow();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Igra se zatvara...");
                System.exit(0);
            }
        });
    }

    private void dodajElemente() {

        panel = new Panel(this, score);
        traka = new Traka(this, score);

        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0.1;
        add(traka, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1.0;
        add(panel, c);
    }

    /**
     * Metoda koja se poziva kada je kliknuto na dugme za pocetak nove igre.
     * Postavlja fokus na prozor i poziva odgovarajucu metodu u GUI klasi.
     */
    public void novaIgraKliknuta() {
        setFocusable(true);
        requestFocusInWindow();
        SpasiStanje.save(GUI.SAVE_FILE, new StanjeIgre(gui.getLogika()));
        gui.novaIgraKliknuta();
    }

    /**
     * Metoda za osvjezavanje trake.
     */
    public void osvjeziTraku() {
        traka.osvjezi();
    }

    /**
     * Metoda za osvjezavanje panela.
     */
    public void osvjeziPanel() {
        panel.osvjezi();
    }

    /**
     * Metoda za dobijanje elementa datog reda i kolone.
     *
     * @param i Redni broj reda.
     * @param j Redni broj kolone.
     * @return Vrijednost elementa na datoj poziciji.
     */
    public int getElement(int i, int j) {
        return gui.getElement(i, j);
    }
}
