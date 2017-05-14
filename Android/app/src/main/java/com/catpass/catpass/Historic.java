package com.catpass.catpass;

import java.util.Date;

/**
 * Created by Jordan on 28/02/2017.
 */
public class Historic {
    int id;
    Date date;
    boolean out;
    String idAnimal;

    public Historic(int id, Date date, boolean out, String idAnimal) {
        this.id = id;
        this.date = date;
        this.out = out;
        this.idAnimal = idAnimal;
    }

    public Historic(int id, Date date, boolean out) {
        this.id = id;
        this.date = date;
        this.out = out;
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

    public String getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(String idAnimal) {
        this.idAnimal = idAnimal;
    }
}
