package fr.catpass.resources;

/**
 * Created by Jordan on 07/02/2017.
 */
public class Animal {

    private String id;
    private String name;
    private int years;
    private Home home;

    public Animal(String id, String name, int years, Home maison) {
        this.id = id;
        this.name = name;
        this.years = years;
        this.home = home;
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

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }
}
