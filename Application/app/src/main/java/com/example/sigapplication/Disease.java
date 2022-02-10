package com.example.sigapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Disease implements Parcelable {
    private int id;
    private String diseaseName;
    private String description;

    public Disease(int id, String dName, String description) {
        this.id = id;
        this.diseaseName = dName;
        this.description = description;
    }

    protected Disease(Parcel in) {
        id = in.readInt();
        diseaseName = in.readString();
        description = in.readString();
    }

    public int getId() {
        return id;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public String getDescription() {
        return description;
    }

    public static final Creator<Disease> CREATOR = new Creator<Disease>() {
        @Override
        public Disease createFromParcel(Parcel parcel) {
            return new Disease(parcel);
        }

        @Override
        public Disease[] newArray(int size) {
            return new Disease[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(diseaseName);
        parcel.writeString(description);
    }
}
