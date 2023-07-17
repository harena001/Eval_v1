package com.harena.eval_v1.models;

import java.util.Date;

public class DetailsGroupeActe {

    public String nomActe;
    public int prix;
    public Date date;

    public String getNomActe() {
        return nomActe;
    }

    public void setNomActe(String nomActe) {
        this.nomActe = nomActe;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
