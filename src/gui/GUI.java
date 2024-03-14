package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import logika.Logika;
import save.StanjeIgre;
import save.SpasiStanje;
import score.Score;

/**
 * Klasa koja predstavlja graficki korisnicki interfejs igre.
 * Implementira KeyListener kako bi reagovala na dogadjaje tastature.
 */
public class GUI implements KeyListener {

    private Logika logika;
    private Okvir okvir;
    private Score score;
    
    /**
     * Ime fajla za spremanje stanja igre.
     */
    public static final String SAVE_FILE = "zadnjaIgraGUI.ser";

    /**
     * Konstruktor klase GUI. Inicijalizira logiku, score i okvir.
     * Poziva se u main metodi kako bi se pokrenula igra.
     */
    public GUI() {
        logika = new Logika();
        score = logika.getScoreManager();
        okvir = new Okvir(this, score);
    }
    
    /**
     * Metoda koja se poziva kada je kliknuto na dugme za pocetak nove igre.
     * Resetuje logiku i score, osvjezava panel i traku.
     */
    public void novaIgraKliknuta() {
    	
    	//System.out.println("Nova igra kliknuta.");
        logika = new Logika();
        score.resetujScore();
        spasiStanje();
        osvjeziPanel();
        osvjeziTraku();
    }

    /**
     * Metoda koja se poziva kada je pritisnuta tipka na tastaturi.
     * 
     * @param e Dogadjaj tastature koji sadrzi informacije o pritisnutoj tipki.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                logika.pomjeriGore();
                osvjeziTraku();
                break;
            case KeyEvent.VK_DOWN:
                logika.pomjeriDole();
                osvjeziTraku();
                break;
            case KeyEvent.VK_LEFT:
                logika.pomjeriLijevo();
                osvjeziTraku();
                break;
            case KeyEvent.VK_RIGHT:
                logika.pomjeriDesno();
                osvjeziTraku();
                break;
        }
        
        score.setTrenutniScore(logika.getZadnjiScore());
        spasiStanje();
        osvjeziPanel();
        osvjeziTraku();
        if (!logika.imaMogucihPoteza()) {
            // prikazi obavijest ako nema vise validnih poteza
            new ObavijestProzor("Igra gotova - nema vise validnih poteza.", this);
        }
    }

    /**
     * Metoda za spremanje stanja igre.
     */
    public void spasiStanje() {
        SpasiStanje.save(SAVE_FILE, new StanjeIgre(logika));
	}

    /**
	 * Metoda koja ucitava stanje posljednje igrane igre. 
	 */
	public void ucitajSacuvanoStanje() {
    	StanjeIgre sacuvanoStanjeIgre = SpasiStanje.load(SAVE_FILE);
        if (sacuvanoStanjeIgre != null) {
            // ako je uspjesno ucitano stanje, postavljamo logiku i osvjezavamo prikaz
            logika = new Logika();
            logika.setPloca(sacuvanoStanjeIgre.getPloca());
            this.score.setBestScore(sacuvanoStanjeIgre.getBestScore());
            this.score.setTrenutniScore(sacuvanoStanjeIgre.getTrenutniScore());
            osvjeziTraku();
            osvjeziPanel();
        }
		
	}

	/**
	 * Prazna implementacija metode interfejsa "KeyListener".
	 *
	 * @param e Objekat KeyEvent koji sadrži informacije o događaju puštanja tipke.
	 */
	@Override
    public void keyTyped(KeyEvent e) {}
	
	/**
	 * Prazna implementacija metode interfejsa "KeyListener".
	 *
	 * @param e Objekat KeyEvent koji sadrži informacije o događaju puštanja tipke.
	 */
    @Override
    public void keyReleased(KeyEvent e) {}

    /**
     * Metoda za osvjezavanje trake u okviru GUI.
     */
    public void osvjeziTraku() {
        okvir.osvjeziTraku();
    }

    /**
     * Metoda za osvjezavanje panela u okviru GUI.
     */
    public void osvjeziPanel() {
        okvir.osvjeziPanel();
    }

    /**
     * Metoda za dobijanje trenutnog score-a.
     *
     * @return Trenutni rezultat igre.
     */
    public int getTrenutniScore() {
        return score.getTrenutniScore();
    }

    /**
     * Metoda za dobijanje best score-a.
     *
     * @return best score.
     */
    public int getBestScore() {
        return score.getBestScore();
    }

    /**
     * Metoda za dobijanje elementa u datom redu i koloni.
     *
     * @param i Redni broj reda.
     * @param j Redni broj kolone.
     * @return Vrijednost elementa na datoj poziciji.
     */
    public int getElement(int i, int j) {
        return logika.getElement(i, j);
    }
    
    /**
     * Vraca logiku za ovu igru.
     * @return logika igre
     */
    public Logika getLogika() {
        return logika;
    }
}
