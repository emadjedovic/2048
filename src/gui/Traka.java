package gui;

import javax.swing.*;

import score.Score;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * Klasa koja predstavlja traku sa dodatnim informacijama u igri "2048".
 * Nasljedjuje JPanel i omogucava prikaz trenutnog rezultata i najboljeg rezultata, te dugmeta za novu igru.
 */
public class Traka extends JPanel {

	/**
     * Jedinstveni identifikator za serializaciju.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Objekt klase Okvir.
     */
    private Okvir okvir;

    /**
     * Dugme za pokretanje nove igre.
     */
    private JButton novaIgra;

    /**
     * Labela koja prikazuje trenutni rezultat.
     */
    private JLabel scoreLabel;

    /**
     * Labela koja prikazuje najbolji rezultat.
     */
    private JLabel bestLabel;

    /**
     * Objekt klase Score.
     */
    private Score score;

    /**
     * Konstruktor klase Traka.
     *
     * @param o Objekat klase Okvir.
     * @param s Objekat klase Score.
     */
    public Traka(Okvir o, Score s) {
        this.okvir = o;
        this.score = s;
        dodajElemente();
    }

    private void dodajElemente() {

        novaIgra = new JButton("NEW GAME");
        novaIgra.setBackground(Color.white);
        novaIgra.setFont(new Font("Arial", Font.BOLD, 16));

        novaIgra.addActionListener(e -> {
            okvir.novaIgraKliknuta();
        });

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bestLabel = new JLabel("Best: 0");
        bestLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel prazanLabel = new JLabel();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;

        c.insets = new Insets(30, 30, 30, 0);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        add(novaIgra, c);

        c.insets = new Insets(10, 0, 10, 20);

        c.gridx = 1;
        c.gridy = 0;
        add(prazanLabel, c);

        c.gridx = 2;
        c.gridy = 0;
        add(scoreLabel, c);

        c.gridx = 3;
        c.gridy = 0;
        add(bestLabel, c);
    }

    /**
     * Metoda koja osvjezava prikaz trenutnog rezultata i najboljeg rezultata u traci.
     */
    public void osvjezi() {
        scoreLabel.setText("Score: " + score.getTrenutniScore());
        bestLabel.setText("Best: " + score.getBestScore());
    }

}
