package com.catpass.catpass;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jordan on 09/05/2017.
 */

public class Home implements Parcelable {
    private int id;
    private String adress;

    public Home(int id, String adress) {
        this.id = id;
        this.adress = adress;
    }
    public Home(String adress) {
        this.adress = adress;
    }

    protected Home(Parcel in) {
        id = in.readInt();
        adress = in.readString();
    }

    public static final Creator<Home> CREATOR = new Creator<Home>() {
        @Override
        public Home createFromParcel(Parcel in) {
            return new Home(in);
        }

        @Override
        public Home[] newArray(int size) {
            return new Home[size];
        }
    };

    public Home() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(adress);
    }
}
