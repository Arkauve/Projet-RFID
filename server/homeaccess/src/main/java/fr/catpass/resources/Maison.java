package fr.catpass.resources;

/**
 * Created by Jordan on 07/02/2017.
 */
public class Maison {
    private int id;
    private String adresse;

    public Maison(int id, String adresse) {
        this.id = id;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

}
