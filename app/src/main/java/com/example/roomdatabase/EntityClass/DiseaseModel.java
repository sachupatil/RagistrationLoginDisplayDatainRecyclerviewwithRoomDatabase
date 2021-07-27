package com.example.roomdatabase.EntityClass;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "Diseaseuser")
public class DiseaseModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    String disease;
    String diseaseName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }




}
