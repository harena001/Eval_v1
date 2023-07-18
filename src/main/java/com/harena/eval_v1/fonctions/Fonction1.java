package com.harena.eval_v1.fonctions;

import com.harena.eval_v1.models.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    public int totalePrix(List<DetailsGroupeActe> list){
        int rep = 0;
        for (int i = 0; i < list.size(); i++) {
            rep = rep + list.get(i).getPrix();
        }
        return rep;
    }

    public List<Acte> setPourcentage(List<Acte> list){
        for (int i = 0; i < list.size(); i++) {
            //if (list.get(i).getBudgetAnnuel() != 0){
                int pourcentage = list.get(i).getReel() * 100;
                int budget = pourcentage / list.get(i).getBudgetAnnuel();
                //pourcentage = 10 / 5;
            //}
            list.get(i).setRealisation(budget);
        }
        return list;
    }

    public int totaleReel(List<Acte> liste){
        int rep = 0;
        for (int i = 0; i < liste.size(); i++) {
            rep = rep + liste.get(i).getReel();
        }
        return rep;
    }

    public int totaleBudget(List<Acte> liste){
        int rep = 0;
        for (int i = 0; i < liste.size(); i++) {
            rep = rep + liste.get(i).getBudgetAnnuel();
        }
        return rep;
    }

    public int totaleRealisation(List<Acte> liste){
        int rep = 0;
        for (int i = 0; i < liste.size(); i++) {
            rep = rep + liste.get(i).getRealisation();
        }
        rep = rep / liste.size();
        return rep;
    }


    public List<Depense> setPourcentageDepense(List<Depense> list){
        for (int i = 0; i < list.size(); i++) {
            //if (list.get(i).getBudgetAnnuel() != 0){
            int pourcentage = list.get(i).getReel() * 100;
            int budget = pourcentage / list.get(i).getBudgetAnnuel();
            //pourcentage = 10 / 5;
            //}
            list.get(i).setRealisation(budget);
        }
        return list;
    }
    public int totaleReeld(List<Depense> liste){
        int rep = 0;
        for (int i = 0; i < liste.size(); i++) {
            rep = rep + liste.get(i).getReel();
        }
        return rep;
    }

    public int totaleBudgetd(List<Depense> liste){
        int rep = 0;
        for (int i = 0; i < liste.size(); i++) {
            rep = rep + liste.get(i).getBudgetAnnuel();
        }
        return rep;
    }

    public int totaleRealisationd(List<Depense> liste){
        int rep = 0;
        for (int i = 0; i < liste.size(); i++) {
            rep = rep + liste.get(i).getRealisation();
        }
        rep = rep / liste.size();
        return rep;
    }

}
