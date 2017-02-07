package fr.catpass.resources;

/**
 * Created by Jordan on 07/02/2017.
 */
public class Animal {

    private String id;
    private String name;
    private int age;
    private Maison maison;

    public Animal(String id, String name, int age, Maison maison) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.maison = maison;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Maison getMaison() {
        return maison;
    }

    public void setMaison(Maison maison) {
        this.maison = maison;
    }
}
