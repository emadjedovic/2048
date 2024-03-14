package konzola;

import java.util.Scanner;
import save.SpasiStanje;
import save.StanjeIgre;
import score.Score;
import logika.Logika;


/**
 * Klasa koja predstavlja konzolni interfejs igre "2048".
 * Korisnik moze unositi poteze W/A/S/D.
 */
public class Konzola {
    private Logika logika;
    private Score score;
    
    /**
     * Ime fajla za spremanje stanja igre.
     */
    public static final String SAVE_FILE = "zadnjaIgraKonzola.ser";

    /**
     * Konstruktor za klasu Konzola. Inicijalizira objekat za logiku igre.
     */
    public Konzola () {
        logika = new Logika();
        score = logika.getScoreManager();
    }

    /**
     * Metoda koja pokrece konzolnu verziju igre.
     */
    public void novaIgraKliknuta() {
    	
    	//System.out.println("\nNova igra kliknuta.");
    	logika = new Logika();
    	score.resetujScore();
    	spasiStanje();
    	igraj();
    }
    
    /**
     * Metoda koja vrsi interakciju s korisnikom i prima input.
     */
    public void igraj() {
    	
        Scanner scanner = new Scanner(System.in);
        char unos;

        do {
        	//System.out.println("score test: "+score.getTrenutniScore());
            ispisiPlocu();
            
            System.out.println("\nUnesite potez!\n");
            System.out.println("W-gore | S-dole | "
            		+ "A-lijevo | D-desno"
            		+ "\nN-nova igra | Q-izlaz");
            
            System.out.println();
            unos = scanner.next().charAt(0);
            while(unos != 'W' && unos != 'w' && unos != 'S' && unos != 's'
            		&& unos != 'A' && unos != 'a' && unos != 'D' && unos != 'd'
            		&& unos != 'N' && unos != 'n' && unos != 'Q' && unos != 'q') {
            	unos = scanner.next().charAt(0);
            }
            System.out.println("--------------------------\n");

            if (unos == 'W' || unos == 'w') {
                logika.pomjeriGore();
            } else if (unos == 'S' || unos == 's') {
                logika.pomjeriDole();
            } else if (unos == 'A' || unos == 'a') {
                logika.pomjeriLijevo();
            } else if (unos == 'D' || unos == 'd') {
                logika.pomjeriDesno();
            }
          
            score.setTrenutniScore(logika.getZadnjiScore());
            spasiStanje();
            
        } while (unos != 'Q' && unos != 'q' && logika.imaMogucihPoteza()
        		&& unos !='N' && unos != 'n');
        
        if (unos == 'N' || unos == 'n') {
            novaIgraKliknuta();
        } else if (unos == 'Q' || unos == 'q') {
            scanner.close();
        	System.out.println("Kraj igre!");
        } else if (logika.imaMogucihPoteza() == false) {
            System.out.println("N - nova igra || Q - izlaz");
            unos = scanner.next().charAt(0);
            
            while(unos != 'Q' && unos != 'q' && unos != 'N' && unos != 'n') {
            	unos = scanner.next().charAt(0);
            }

            if (unos == 'Q' || unos == 'q') {
                scanner.close();
            	System.out.println("Kraj igre!");
            }else if (unos == 'N' || unos == 'n') {
                novaIgraKliknuta();
            }
        }
        
        //sta ako smo dosegli 2048?
    	scanner.close();
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
		if(sacuvanoStanjeIgre!=null) {
			logika = new Logika();
			logika.setPloca(sacuvanoStanjeIgre.getPloca());
	        this.score.setBestScore(sacuvanoStanjeIgre.getBestScore());
	        this.score.setTrenutniScore(sacuvanoStanjeIgre.getTrenutniScore());
	        igraj();
		}
	}
    
    /**
     * Metoda za ispisivanje trenutnog stanja igre.
     */
    public void ispisiPlocu() {
    	//rangovska for petlja
        for (int[] red: logika.getPloca()) {
            for (int vrijednost: red) {
                System.out.print(vrijednost + "\t");
            }
            System.out.println();
        }
        System.out.println("Score: " + score.getTrenutniScore());
        System.out.println("Best score: " + score.getBestScore());
    }

    /**
     * Vraca logiku vezanu za ovu igru.
     * @return logika
     */
	public Logika getLogika() {
		return this.logika;
	}

	

}
