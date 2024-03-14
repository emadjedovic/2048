/**
 * 
 */
package save;
import java.io.*;

/**
 * Klasa za spasavanje i ucitavanje stanja igre iz fajla.
 */
public class SpasiStanje implements Serializable{

    /**
	 * Jedinstveni identifikator za serializaciju.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Sprema stanje igre u fajl.
     *
     * @param imeFajla Ime fajla u koji ce se spremiti stanje igre.
     * @param stanjeIgre Objekat koji predstavlja trenutno stanje igre.
     */
    public static void save(String imeFajla, StanjeIgre stanjeIgre) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(imeFajla))) {
            oos.writeObject(stanjeIgre);
            //System.out.println("saved");
        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println("failed to save");
        }
    }

    /**
     * Ucitava stanje igre iz fajla.
     *
     * @param imeFajla Ime fajla iz kojeg ce se ucitati stanje igre.
     * @return objekat koji predstavlja ucitano stanje igre.
     */
    public static StanjeIgre load(String imeFajla) {
        StanjeIgre stanjeIgre = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(imeFajla))) {
            stanjeIgre = (StanjeIgre) ois.readObject();
            //System.out.println("loaded");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            //System.out.println("failed to load");
        }
        return stanjeIgre;
    }
}

