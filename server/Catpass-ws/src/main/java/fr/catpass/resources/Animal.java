package fr.catpass.resources;

/**
 * Created by Jordan on 07/02/2017.
 */
public class Animal {

    private String id;
    private String name;
    private int years;
    private int idHome;

    public Animal(String id, String name, int years, int idHome) {
        this.id = id;
        this.name = name;
        this.years = years;
        this.idHome = idHome;
    }

    public Animal() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getIdHome() {
        return idHome;
    }

    public void setIdHome(int idHome) {
        this.idHome = idHome;
    }
}
