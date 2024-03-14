package main;

import javax.swing.JOptionPane;

import gui.GUI;
import save.StanjeIgre;
import save.SpasiStanje;

/**
 * Glavna klasa za pokretanje igre "2048" s grafickim interfejsom.
 * Ova klasa sadrzi metod main koji inicijalizira i pokrece igru.
 */
public class MainGUI {

	/**
     * Metoda za pokretanje igre "2048".
     * Inicijalizira graficki korisnicki interfejs i provjerava postoji li sacuvano prethodno stanje.
     * Ako postoji, pita korisnika zeli li nastaviti igru odakle je stao.
     * Ako korisnik zeli nastaviti, ucitava sacuvano stanje; inace, pokrece novu igru.
     * Ako nema sacuvane igre, pokrece novu igru.
     *
     * @param args Argumenti nisu obavezni za pokretanje.
     */
	public static void main(String[] args) {
	    GUI gui = new GUI();

	    // Provjeri postoji li sacuvano prethodno stanje (jesmo li vec igrali).
	    StanjeIgre sacuvanoStanjeIgre = SpasiStanje.load(GUI.SAVE_FILE);

	    if (sacuvanoStanjeIgre != null && sacuvanoStanjeIgre.getBestScore()!=0) {
	        // Ako postoji prethodna igra, pitaj korisnika zeli li nastaviti.
	        int pitanje = JOptionPane.showConfirmDialog(null, "Zelite li nastaviti igru odakle ste stali?", "Nastavi igru", JOptionPane.YES_NO_OPTION);

	        if (pitanje == JOptionPane.YES_OPTION) {
	            gui.ucitajSacuvanoStanje();
	        } else {
	        	//ne zeli nastaviti, pokreni novu igru
	            gui.novaIgraKliknuta();
	        }
	    } else {
	        // pokreni novu igru ako nema prijasnje igre
	        gui.novaIgraKliknuta();
	    }

	}
}
