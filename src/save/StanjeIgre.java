/**
 * 
 */
package save;

import logika.Logika;
import java.io.Serializable;

/**
 * Klasa koja predstavlja stanje igre.
 */
public class StanjeIgre implements Serializable {
	
	/**
	* Jedinstveni identifikator za serializaciju.
	*/
	private static final long serialVersionUID = 1L;

    /**
     * Matrica brojeva na ploci.
     */
    private int[][] ploca;

    /**
     * Trenutni rezultat igre.
     */
    private int trenutniScore;

    /**
     * Najbolji rezultat postignut u igri.
     */
    private int bestScore;

    /**
     * Konstruktor za klasu StanjeIgre.
     * @param logika Logika vezana za igru.
     */
    public StanjeIgre(Logika logika) {
        this.ploca = logika.getPloca();
        this.trenutniScore = logika.getZadnjiScore();
        this.bestScore = logika.getScoreManager().getBestScore();
    }

    /**
     * Vraca matricu brojeva na ploci.
     * @return ploca (niz intova)
     */
    public int[][] getPloca() {
        return ploca;
    }

    /**
     * Vraca trenutni score.
     * @return trenutni score
     */
    public int getTrenutniScore() {
        return trenutniScore;
    }

    /**
     * Vraca best score.
     * @return best score
     */
    public int getBestScore() {
        return bestScore;
    }
}

