package score;

/**
 * Klasa koja cuva trenutni rezultat i najbolji rezultat.
 */
public class Score {
    private int trenutniScore;
    private int bestScore;

    /**
     * Konstruktor - inicijalizira trenutni i najbolji rezultat na nulu.
     */
    public Score() {
        trenutniScore = 0;
        bestScore = 0;
    }

    /**
     * Postavlja trenutni rezultat i azurira najbolji rezultat ako je trenutni veci.
     * @param noviScore Novi trenutni rezultat.
     */
    public void setTrenutniScore(int noviScore) {
        trenutniScore = noviScore;

        // Provjerava je li trenutni rezultat veci od najboljeg rezultata
        if (trenutniScore > bestScore) {
            setBestScore(noviScore);
        }
    }
    
    /**
     * Postavlja novi best score.
     * @param noviBestScore Novi najbolji rezultat.
     */
    public void setBestScore(int noviBestScore) {
    	bestScore = noviBestScore;
    }

    /**
     * Vraca trenutni score.
     * @return Trenutni rezultat.
     */
    public int getTrenutniScore() {
        return trenutniScore;
    }

    /**
     * Vraca best score.
     * @return Najbolji rezultat.
     */
    public int getBestScore() {
        return bestScore;
    }

    /**
     * Postavlja trenutni rezultat na nulu.
     */
    public void resetujScore() {
        trenutniScore = 0;
    }
}
