package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa koja predstavlja prozor za prikaz obavijesti korisniku.
 */
public class ObavijestProzor extends JFrame {

    /**
	 * Jedinstveni identifikator za serializaciju.
	 */
	private static final long serialVersionUID = 1L;

	/**
    * Instanca klase GUI koja koristi prozor.
    */
   private GUI gui;

   /**
    * Oznaka za prikazivanje teksta obavijesti.
    */
   private JLabel labela;

   /**
    * Dugme za zapocinjanje nove igre.
    */
   private JButton novaIgraButton;

   /**
    * Dugme za izlaz iz igre.
    */
   private JButton izlazButton;

    /**
     * Konstruktor klase ObavijestProzor.
     *
     * @param obavijest Tekst obavijesti koja ce se prikazati.
     * @param g         Instanca klase GUI koja koristi prozor.
     */
    public ObavijestProzor(String obavijest, GUI g) {
        this.gui = g;
        setTitle("Obavijest");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(300, 150));
        setLocationRelativeTo(null);

        dodajElemente(obavijest);
        dodajEvente();

        setVisible(true);
    }

    /**
     * Dodaje graficke elemente na prozor.
     *
     * @param s Tekst obavijesti koji ce se prikazati.
     */
    private void dodajElemente(String s) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        labela = new JLabel(s);
        novaIgraButton = new JButton("Zapocni novu igru");
        izlazButton = new JButton("Izadji iz igre");

        novaIgraButton.setBackground(new Color(144, 238, 144));
        izlazButton.setBackground(new Color(255, 127, 127));

        c.insets = new Insets(5, 5, 5, 5);

        c.gridx = 0;
        c.gridy = 0;
        add(labela, c);

        c.gridy = 1;
        add(novaIgraButton, c);
        
        c.gridy = 2;
        add(izlazButton, c);
    }

    /**
     * Dodaje evente na dugmad.
     */
    private void dodajEvente() {
        novaIgraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	gui.novaIgraKliknuta();
            	//System.out.println("Nova igra kliknuta iz obavijesti prozora");
            	gui.spasiStanje();
            	//System.out.println("spaseno stanje iz obavijesti prozora");
                
                dispose();
            }
        });

        izlazButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	gui.novaIgraKliknuta();
            	gui.spasiStanje();
            	
                System.exit(0); // Zatvori cijelu aplikaciju (sve prozore)
            }
        });
    }
}
