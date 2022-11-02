package com.medicare_backend.medicare_backend.schema.valueObject;

import javax.persistence.*;

@Entity
@Table(name = "disease")
public class Disease {
    private long diseaseId;
    private String diseaseName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        public long getDiseaseId() {
        return diseaseId;
    }
    public void setDiseaseId(long id) {
        this.diseaseId = id;
    }

    public Disease() {
    }

    public Disease(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    @Column(name = "disease_name", nullable = false)
    public String getDiseaseName() {
        return this.diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    @Override
    public String toString() {
        return "{" +
            " diseaseId='" + getDiseaseId() + "'" +
            ", diseaseName='" + getDiseaseName() + "'" +
            "}";
    }

}