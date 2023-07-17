package com.harena.eval_v1.fonctions;

import com.harena.eval_v1.models.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fonction1 {

    public Patient setAge(Patient patient){
        Date dateActuelle = new Date();
        long differenceMillisecondes = dateActuelle.getTime() - patient.getDateDeNaissance().getTime();
        long differenceAnnees = differenceMillisecondes / (1000L * 60 * 60 * 24 * 365);
        patient.setAge((int) differenceAnnees);
        return patient;
    }

    public Date stringToDate(String dateString){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Erreur de conversion de la date.");
            e.printStackTrace();
        }
        return date;
    }

}
