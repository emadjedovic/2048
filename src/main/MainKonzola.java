/**
 * 
 */
package main;

import javax.swing.JOptionPane;
import konzola.Konzola;
import save.SpasiStanje;
import save.StanjeIgre;

/**
 * Glavna klasa za pokretanje igre "2048" s konzolnim interfejsom.
 */
public class MainKonzola {

	/**
     * Metoda za pokretanje igre "2048" putem konzolnog interfejsa.
     * Inicijalizira konzolu za igru i provjerava postoji li sacuvano prethodno stanje.
     * Ako postoji, pita korisnika zeli li nastaviti igru odakle je stao.
     * Ako korisnik zeli nastaviti, ucitava sacuvano stanje; inace, pokrece novu igru.
     * Nakon završetka igre, sprema trenutno stanje igre.
     *
     * @param args Argumenti nisu obavezni za pokretanje.
     */
	public static void main(String[] args) {
        Konzola konzola = new Konzola();
        
     // Provjeri postoji li sacuvano prethodno stanje (jesmo li vec igrali).
	    StanjeIgre sacuvanoStanjeIgre = SpasiStanje.load(Konzola.SAVE_FILE);
	    
	    if (sacuvanoStanjeIgre != null && sacuvanoStanjeIgre.getBestScore()!=0) {
	        // Ako postoji prethodna igra, pitaj korisnika zeli li nastaviti.
	        int pitanje = JOptionPane.showConfirmDialog(null, "Zelite li nastaviti igru odakle ste stali?", "Nastavi igru", JOptionPane.YES_NO_OPTION);

	        if (pitanje == JOptionPane.YES_OPTION) {
	        	konzola.ucitajSacuvanoStanje();
	        } else {
	        	//ne zeli nastaviti, pokreni novu igru
	            konzola.novaIgraKliknuta();
	        }
	    } else {
	        // pokreni novu igru ako nema prijasnje igre
	        konzola.novaIgraKliknuta();
	    }
        
    }

}
