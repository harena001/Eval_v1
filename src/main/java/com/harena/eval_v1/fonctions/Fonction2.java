package com.harena.eval_v1.fonctions;

import com.harena.eval_v1.models.DepenseFait;
import com.harena.eval_v1.models.Patient;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fonction2 {

    public int[] listToTabInt(List<String> list){
        int[] rep = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            rep[i] = Integer.parseInt(list.get(i));
        }
        return rep;
    }

    public DepenseFait[] toTabDepense(int idDepense, int prix, int jour, int annee, int[] tabMois){
        DepenseFait[] rep = new DepenseFait[tabMois.length];

        for (int i = 0; i < tabMois.length; i++) {
            LocalDate localDate = LocalDate.of(annee,tabMois[i],jour);
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            DepenseFait depenseFait = new DepenseFait();
            depenseFait.setIdDepense(idDepense);
            depenseFait.setDate(date);
            depenseFait.setPrix(prix);
            rep[i] = depenseFait;
        }

        return rep;
    }

    public DepenseFait stringToDepenseFait(String[] tabstr){
        DepenseFait rep = new DepenseFait();
        String[] date = tabstr[0].split("/");
        LocalDate ldate = LocalDate.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0]));
        Date datef = Date.from(ldate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        rep.setCode(tabstr[1]);
        rep.setPrix(Integer.parseInt(tabstr[2]));
        rep.setDate(datef);

        return rep;
    }

    public List<DepenseFait> dataCsvtoList(List<String[]> data){
        List<DepenseFait> rep = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            rep.add(stringToDepenseFait(data.get(i)));
        }
        return rep;
    }

    public List<Patient> search(List<Patient> liste, String str){
        List<Patient> rep = new ArrayList<>();
        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i).getNomPatient().toUpperCase().contains(str.toUpperCase())){
                rep.add(liste.get(i));
            }
        }
        return rep;
    }

}
