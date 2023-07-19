package com.harena.eval_v1.models;

import com.harena.eval_v1.fonctions.Fonction1;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class DetailsGroupeActe {

    public String nomActe;
    public int prix;
    public Date date;
    public int idGroupeActe;

    public int getIdGroupeActe() {
        return idGroupeActe;
    }

    public void setIdGroupeActe(int idGroupeActe) {
        this.idGroupeActe = idGroupeActe;
    }

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
        /*SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH);
        Fonction1 fonction1 = new Fonction1();
        return fonction1.stringToDate(sdf.format(date));*/
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
