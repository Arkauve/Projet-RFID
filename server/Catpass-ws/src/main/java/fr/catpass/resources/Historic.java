package fr.catpass.resources;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Jordan on 28/02/2017.
 */
@XmlRootElement
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

    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlElement
    public boolean isOut() {
        return out;
    }

    public void setOut(boolean out) {
        this.out = out;
    }

    @XmlElement
    public String getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(String idAnimal) {
        this.idAnimal = idAnimal;
    }
}
