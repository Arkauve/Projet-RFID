package fr.catpass.resources;

import java.util.Date;

/**
 * Created by Jordan on 28/02/2017.
 */
public class Historic {
    int id;
    Date date;
    boolean out;
    int idAnimal;

    public Historic(int id, Date date, boolean out, int idAnimal) {
        this.id = id;
        this.date = date;
        this.out = out;
        this.idAnimal = idAnimal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isOut() {
        return out;
    }

    public void setOut(boolean out) {
        this.out = out;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }
}
