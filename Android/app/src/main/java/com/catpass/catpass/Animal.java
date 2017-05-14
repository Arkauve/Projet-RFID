package com.catpass.catpass;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jordan on 07/02/2017.
 */
public class Animal implements Parcelable {

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

    protected Animal(Parcel in) {
        id = in.readString();
        name = in.readString();
        years = in.readInt();
        idHome = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeInt(years);
        dest.writeInt(idHome);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };


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
