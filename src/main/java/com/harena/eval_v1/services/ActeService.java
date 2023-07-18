package com.harena.eval_v1.services;

import com.harena.eval_v1.dao.ActeDao;
import com.harena.eval_v1.models.Acte;

import java.util.List;

public class ActeService {

    public static ActeDao acteDao = new ActeDao();

    public List<Acte> getListActe(){
        return acteDao.getListActe();
    }

    public void saveActe(Acte acte){
        acteDao.saveActe(acte);
    }

    public void updateNomActe(int idActe, String nom){
        acteDao.updateNomActe(idActe,nom);
    }
    public void updateBudgetAnnuelActe(int idActe, int budgetAnnuel){
        acteDao.updateBudgetAnnuelActe(idActe,budgetAnnuel);
    }
    public void updateCodeActe(int idActe, String code){
        acteDao.updateCodeActe(idActe,code);
    }

    public void deleteActe(int idActe){
        acteDao.deleteActes(idActe);
    }

    public void validerPayement(int idGroupeActe){
        acteDao.validerPayement(idGroupeActe);
    }


    public List<Acte> getListeActeRecetteFin(int idMois,int annee){
        return acteDao.getListeActeRecetteFinal(getListActe(),idMois,annee);
    }

}
