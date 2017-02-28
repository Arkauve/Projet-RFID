package fr.catpass.resources;

/**
 * Created by Jordan on 07/02/2017.
 */
public class Home {
    private int id;
    private String adress;

    public Home(int id, String adress) {
        this.id = id;
        this.adress = adress;
    }
    public Home(String adress) {
        this.adress = adress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

}
