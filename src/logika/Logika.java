package logika;

import java.util.ArrayList;
import java.util.Random;
import score.Score;

/**
 * Klasa koja predstavlja logiku igre "2048". Ova klasa manipulira matricom 4x4
 * i implementira pravila igre, kao i dodavanje novih brojeva nakon svakog poteza.
 */
public class Logika {

    private int ploca[][] = new int[4][4];
    private Score score;
    private int zadnjiScore;

    /**
     * Konstruktor klase Logika. Inicijalizira plocu igre, postavlja pocetne vrijednosti
     * i instancira objekat za pracenje rezultata.
     */
    public Logika() {
        postaviPolja();
        inicijalnoOtvori();
        zadnjiScore = 0;
        score = new Score();
    }

    // inicijalno postavlja dva nova broja na plocu igre
    private void inicijalnoOtvori() {
        int prviEksponent = odaberiEksponent();
        int drugiEksponent = odaberiEksponent();

        ArrayList<Integer> listaNula = pronadiNuleNaPloci();

        if (listaNula.size() < 2) {
            // nije moguce postaviti dva nova broja
            return;
        }

        int linearniIndeks1 = odaberiPoziciju(listaNula);
        int linearniIndeks2 = odaberiPoziciju(listaNula);

        if (linearniIndeks1 != -1 && linearniIndeks2 != -1) {
            postaviBrojNaPlocu(linearniIndeks1, prviEksponent);
            postaviBrojNaPlocu(linearniIndeks2, drugiEksponent);
        }
    }

    // odabire nasumicnu poziciju iz liste slobodnih polja
    private int odaberiPoziciju(ArrayList<Integer> listaNula) {
        int velicinaListeNula = listaNula.size();
        if (velicinaListeNula > 0) {
            Random random = new Random();
            int pozicija = random.nextInt(velicinaListeNula);
            return listaNula.get(pozicija);
        }
        return -1; // nema dostupnih pozicija
    }

    //sva polja na 0
	private void postaviPolja() {
		for (int i = 0; i < ploca.length; i++) {
			for (int j = 0; j < ploca[i].length; j++) {
				ploca[i][j] = 0;
			}
		}
	}

	// dodavanje novog broja (2 ili 4) na plocu igre na prazno polje nakon svakog poteza
	private void dodajBrojNakonPoteza() {
		ArrayList<Integer> listaNula = pronadiNuleNaPloci();
		int eksponent = odaberiEksponent();
		postaviNoviBrojNaPlocu(listaNula, eksponent);
	}
	
	// postavlja novi broj na plocu igre na slucajno odabrano prazno polje
	private void postaviNoviBrojNaPlocu(ArrayList<Integer> listaNula, int eksponent) {
	    int velicinaListeNula = listaNula.size();
	    if (velicinaListeNula > 0) {
	        Random random = new Random();
	        int pozicija = random.nextInt(velicinaListeNula);
	        int linearniIndeks = listaNula.get(pozicija);

	        postaviBrojNaPlocu(linearniIndeks, eksponent);
	        listaNula.remove(pozicija);
	    }
	}
	
	// postavlja broj na plocu na odredjenu poziciju
	private void postaviBrojNaPlocu(int linearniIndeks, int eksponent) {
        int[] koordinate = linearniIndeksU2D(linearniIndeks);
        int i = koordinate[0];
        int j = koordinate[1];
        
        double dvaIliCetiri = Math.pow(2, eksponent);
        ploca[i][j] = (int) dvaIliCetiri;
    }

	// konvertuje linearni indeks u dvodimenzionalne koordinate na ploci
    private int[] linearniIndeksU2D(int linearniIndeks) {
        int i = linearniIndeks / ploca[0].length;
        int j = linearniIndeks % ploca[0].length;
        return new int[]{i, j};
    }

    // pronalazi sva prazna polja na ploci
	private ArrayList<Integer> pronadiNuleNaPloci() {
        ArrayList<Integer> listaNula = new ArrayList<>();
        for (int i = 0; i < ploca.length; i++) {
            for (int j = 0; j < ploca[i].length; j++) {
                if (ploca[i][j] == 0) {
                    listaNula.add(i * ploca[0].length + j);
                }
            }
        }
        return listaNula;
    }

	// odabire nasumicni eksponent za novi broj (1 ili 2)
	private int odaberiEksponent() {
	    int[] eksponenti = {1, 2};
	    Random random = new Random();
	    int randomIndeks = random.nextInt(2);
	    return eksponenti[randomIndeks];
	}

	/**
	 * Pomjera sve elemente prema gore na ploci igre i spaja iste brojeve.
	 * Poziva funkciju za dodavanje novog broja nakon poteza.
	 */
    public void pomjeriGore() {
        for (int j = 0; j < ploca[0].length; j++) {
            for (int i = 1; i < ploca.length; i++) {
                if (ploca[i][j] != 0) {
                    pomjeriJedanElementGore(i, j);
                }
            }
        }
        dodajBrojNakonPoteza();
        score.setTrenutniScore(zadnjiScore);
    }

    // pomjera element prema gore na ploci igre i spaja iste brojeve
    private void pomjeriJedanElementGore(int red, int kolona) {
        for (int i = red - 1; i >= 0; i--) {
            if (ploca[i][kolona] == 0) {
                // pomakni element prema gore
                ploca[i][kolona] = ploca[i + 1][kolona];
                ploca[i + 1][kolona] = 0;
            } else if (ploca[i][kolona] == ploca[i + 1][kolona]) {
                // spoji dva ista elementa
                ploca[i][kolona] *= 2;
                if (ploca[i][kolona] > zadnjiScore) {
                    zadnjiScore = ploca[i][kolona];
                }
                ploca[i + 1][kolona] = 0;
                break;  // prekini petlju nakon spajanja
            } else {
            	// elementi su razliciti, nema spajanja, izlaz iz petlje
                break;
            }
        }
    }

    /**
     * Pomjera sve elemente prema dolje na ploci igre i spaja iste brojeve.
     * Poziva funkciju za dodavanje novog broja nakon poteza.
     */
    public void pomjeriDole() {
        for (int j = 0; j < ploca[0].length; j++) {
            for (int i = ploca.length - 2; i >= 0; i--) {
                if (ploca[i][j] != 0) {
                    pomjeriJedanElementDole(i, j);
                }
            }
        }
        dodajBrojNakonPoteza();
        score.setTrenutniScore(zadnjiScore);
    }

    // pomjera element prema dole na ploci igre i spaja iste brojeve
    private void pomjeriJedanElementDole(int red, int kolona) {
        for (int i = red + 1; i < ploca.length; i++) {
            if (ploca[i][kolona] == 0) {
                // Pomakni element prema dolje
                ploca[i][kolona] = ploca[i - 1][kolona];
                ploca[i - 1][kolona] = 0;
            } else if (ploca[i][kolona] == ploca[i - 1][kolona]) {
                // Spoji dva ista elementa
                ploca[i][kolona] *= 2;
                if (ploca[i][kolona] > zadnjiScore) {
                    zadnjiScore = ploca[i][kolona];
                }
                ploca[i - 1][kolona] = 0;
                break;  // prekini petlju nakon spajanja
            } else {
            	// elementi su razliciti, nema spajanja, izlaz iz petlje
                break;
            }
        }
    }
    
    /**
	 * Pomjera sve elemente prema lijevo na ploci igre i spaja iste brojeve.
	 * Poziva funkciju za dodavanje novog broja nakon poteza.
	 */
    public void pomjeriLijevo() {
        for (int i = 0; i < ploca.length; i++) {
            for (int j = 0; j < ploca[i].length; j++) {
                if (ploca[i][j] != 0) {
                    pomjeriJedanElementLijevo(i, j);
                }
            }
        }
        dodajBrojNakonPoteza();
        score.setTrenutniScore(zadnjiScore);
    }

    // pomjera element prema lijevo na ploci igre i spaja iste brojeve
    private void pomjeriJedanElementLijevo(int red, int kolona) {
        for (int j = kolona - 1; j >= 0; j--) {
            if (ploca[red][j] == 0) {
                // Pomakni element u lijevo
                ploca[red][j] = ploca[red][j + 1];
                ploca[red][j + 1] = 0;
            } else if (ploca[red][j] == ploca[red][j + 1]) {
                // Spoji dva ista elementa
                ploca[red][j] *= 2;
                if (ploca[red][j] > zadnjiScore) {
                    zadnjiScore = ploca[red][j];
                }
                ploca[red][j + 1] = 0;
                break;  // prekini petlju nakon spajanja
            } else {
            	// elementi su razliciti, nema spajanja, izlaz iz petlje
                break;
            }
        }
        
    }

    /**
	 * Pomjera sve elemente prema desno na ploci igre i spaja iste brojeve.
	 * Poziva funkciju za dodavanje novog broja nakon poteza.
	 */
    public void pomjeriDesno() {
        for (int i = 0; i < ploca.length; i++) {
            for (int j = ploca[i].length - 2; j >= 0; j--) {
                if (ploca[i][j] != 0) {
                    pomjeriJedanElementDesno(i, j);
                }
            }
        }
        dodajBrojNakonPoteza();
        score.setTrenutniScore(zadnjiScore);
    }

    // pomjera element prema desno na ploci igre i spaja iste brojeve
    private void pomjeriJedanElementDesno(int red, int kolona) {
        for (int j = kolona + 1; j < ploca[0].length; j++) {
            if (ploca[red][j] == 0) {
                // Pomakni element u desno
                ploca[red][j] = ploca[red][j - 1];
                ploca[red][j - 1] = 0;
            } else if (ploca[red][j] == ploca[red][j - 1]) {
                // Spoji dva ista elementa
                ploca[red][j] *= 2;
                if (ploca[red][j] > zadnjiScore) {
                    zadnjiScore = ploca[red][j];
                }
                ploca[red][j - 1] = 0;
                break;  // prekini petlju nakon spajanja
            } else {
                // elementi su razliciti, nema spajanja, izlaz iz petlje
                break;
            }
        }
    }

	
    /**
     * Vraca vrijednost elementa na odredjenoj poziciji na ploci.
     *
     * @param i red
     * @param j kolona
     * @return Vrijednost elementa na poziciji (i, j).
     */
    public int getElement(int i, int j) {
        return ploca[i][j];
    }

    /**
     * Vraca objekat klase Score.
     * @return Objekat klase Score.
     */
    public Score getScoreManager() {
        return score;
    }

    /**
     * Vraca zadnji rezultat.
     * @return Zadnji postignuti rezultat.
     */
    public int getZadnjiScore() {
        return zadnjiScore;
    }
    
    /**
     * Postavlja best score.
     * @param bs best score
     */
    public void setBestScore(int bs) {
    	score.setBestScore(bs);
    }
    
    /**
     * Postavlja trenutni score.
     * @param ts trenutni score
     */
    public void setTrenutniScore(int ts) {
    	score.setTrenutniScore(ts);
    }
    
    /**
     * Postavlja plocu proslijedjenu kao parametar.
     * @param novaPloca nova ploca
     */
    public void setPloca(int[][] novaPloca) {
    	this.ploca = novaPloca;
    }
    
    /**
     * Vraca plocu.
     * @return ploca matrica intova
     */
    public int[][] getPloca() {
    	return this.ploca;
    }
    
    /**
     * Metoda koja provjerava ima li mogucih poteza.
     * @return true ako ima mogucih poteza, inace false
     */
    public boolean imaMogucihPoteza() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (ploca[i][j] == 0) {
                    // pronadjena prazna celija, postoji legitiman potez
                    return true;
                }

                // provjeri susjedne celije za istu vrijednost (moguce spajanje)
                if (j < 3 && ploca[i][j] == ploca[i][j + 1]) {
                    return true;
                }
                if (i < 3 && ploca[i][j] == ploca[i + 1][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
