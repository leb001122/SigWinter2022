package com.example.sigapplication;

public class Disease {
    private String diseaseName;
    private String description;

    public Disease(String dName, String description) {
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
