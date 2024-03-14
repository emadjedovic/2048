package gui;

import javax.swing.*;

import score.Score;

import java.awt.*;

/**
 * Klasa koja predstavlja panel za prikaz matrice kvadrata u igri "2048".
 * Nasljedjuje JPanel i omogucava vizualizaciju trenutnog stanja igre.
 */
public class Panel extends JPanel {

	 /**
     * Jedinstveni identifikator za serializaciju.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Objekt klase Okvir.
     */
    private Okvir okvir;

    /**
     * Dvodimenzionalno polje za spremanje malih panela (kvadratica).
     */
    private JPanel[][] kvadratici;

    /**
     * Objekt klase Score.
     */
    //private Score score;

    /**
     * Konstruktor klase Panel.
     *
     * @param o Objekat klase Okvir.
     * @param s Objekat klase Score.
     */
    public Panel(Okvir o, Score s) {
        this.okvir = o;
        //this.score = s;
        inicijalizirajPanele();
    }

    // mali kvadratici, panele jer moramo unutra staviti jLabels zbog brojeva
    private void inicijalizirajPanele() {

        setLayout(new GridLayout(4, 4, 5, 5));
        kvadratici = new JPanel[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                kvadratici[i][j] = new JPanel();
                postaviLabelu(kvadratici[i][j], okvir.getElement(i, j));

                kvadratici[i][j].setLayout(new GridLayout(1, 1));
                kvadratici[i][j].setPreferredSize(new Dimension(33, 33));

                add(kvadratici[i][j]);
            }
        }
    }

    // unutar svakog pojedinacnog kvadratica imamo labelu (broj)
    private JLabel stvoriLabelu(int vrijednost) {
        JLabel labela;
        if (vrijednost != 0)
            labela = new JLabel("" + vrijednost);
        else
            labela = new JLabel("");

        Font font = new Font("Arial", Font.BOLD, 40);
        labela.setFont(font);
        labela.setHorizontalAlignment(0);
        labela.setOpaque(true);

        return labela;
    }

    private Color dohvatiBoju(int vrijednost) {
        Color boja;
        switch (vrijednost) {
            case 0:
                boja = new Color(191, 207, 179);
                break;
            case 2:
                boja = new Color(230, 204, 179);
                break;
            case 4:
                boja = new Color(220, 220, 194);
                break;
            case 8:
                boja = new Color(255, 235, 205);
                break;
            case 16:
                boja = new Color(222, 184, 135);
                break;
            case 32:
                boja = new Color(188, 143, 143);
                break;
            case 64:
                boja = new Color(170, 65, 65);
                break;
            case 128:
                boja = new Color(107, 142, 35);
                break;
            case 256:
                boja = new Color(230, 230, 179);
                break;
            case 512:
                boja = new Color(230, 230, 179);
                break;
            default:
                boja = new Color(230, 230, 179);
                break;
        }
        return boja;

    }

    // postavlja labelu-broj u mali panel (kvadratic)
    private void postaviLabelu(JPanel panel, int vrijednost) {
        panel.removeAll(); // Ukloni sve komponente iz kvadratica
        JLabel labela = stvoriLabelu(vrijednost);
        labela.setBackground(dohvatiBoju(vrijednost));
        panel.add(labela);
        panel.revalidate();
        // panel.repaint();
    }

    /**
     * Metoda za osvjezavanje panela.
     */
    public void osvjezi() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                postaviLabelu(kvadratici[i][j], okvir.getElement(i, j));
            }
        }
        revalidate();
        // repaint();
    }
}
