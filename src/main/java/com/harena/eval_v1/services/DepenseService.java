package com.harena.eval_v1.services;

import com.harena.eval_v1.dao.ActeDao;
import com.harena.eval_v1.dao.DepenseDao;
import com.harena.eval_v1.models.Acte;
import com.harena.eval_v1.models.Depense;
import com.harena.eval_v1.models.DepenseFait;

import java.util.List;

public class DepenseService {

    public static DepenseDao depenseDao = new DepenseDao();

    public List<Depense> getListDepense(){
        return depenseDao.getListDepense();
    }

    public void saveDepense (Depense depense){
        depenseDao.saveDepense(depense);
    }

    public void updateNomDepense(int idDepense, String nom){
        depenseDao.updateNomDepense(idDepense,nom);
    }
    public void updateBudgetAnnuelDepense(int idDepense, int budget){
        depenseDao.updateBudgetAnnuelDepense(idDepense,budget);
    }
    public void updateCodeDepense(int idDepense, String code){
        depenseDao.updateCodeDepense(idDepense,code);
    }

    public void deleteDepense(int idDepense){
        depenseDao.deleteDepense(idDepense);
    }

    public void insertDepenseFait(DepenseFait depenseFait){
        depenseDao.insertDepenseFait(depenseFait);
    }

    public List<Depense> getListeDepenseFin(int idMois,int annee){
        return depenseDao.getListeDepenseFinal(getListDepense(),idMois,annee);
    }

}
