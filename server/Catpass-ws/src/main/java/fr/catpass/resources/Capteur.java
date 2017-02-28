package fr.catpass.resources;

/**
 * Created by Jordan on 21/02/2017.
 */
public class Capteur {
    String id;
    int idMaison;

    public Capteur(String id, int idMaison) {
        this.id = id;
        this.idMaison = idMaison;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdMaison() {
        return idMaison;
    }

    public void setIdMaison(int idMaison) {
        this.idMaison = idMaison;
    }
}
