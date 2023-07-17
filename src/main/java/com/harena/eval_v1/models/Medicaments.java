package com.harena.eval_v1.models;

public class Medicaments {

    public int id;
    public String NomMedoc;
    public int PrixMedoc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomMedoc() {
        return NomMedoc;
    }

    public void setNomMedoc(String nomMedoc) {
        NomMedoc = nomMedoc;
    }

    public int getPrixMedoc() {
        return PrixMedoc;
    }

    public void setPrixMedoc(int prixMedoc) {
        PrixMedoc = prixMedoc;
    }
}
