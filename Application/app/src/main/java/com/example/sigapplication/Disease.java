package com.example.sigapplication;

public class Disease {
    int id;
    String diseaseName;
    String description;

    public Disease(int id, String dName, String description) {
        this.id = id;
        this.diseaseName = dName;
        this.description = description;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public String getDescription() {
        return description;
    }
}
