package com.harena.eval_v1.services;

import com.harena.eval_v1.dao.ActeDao;
import com.harena.eval_v1.dao.DepenseDao;
import com.harena.eval_v1.fonctions.Fonction2;
import com.harena.eval_v1.models.Acte;
import com.harena.eval_v1.models.Depense;
import com.harena.eval_v1.models.DepenseFait;

import java.util.List;

public class DepenseService {

    public static DepenseDao depenseDao = new DepenseDao();
    Fonction2 fonction2 = new Fonction2();

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
        return depenseDao.getListeDepenseFinal(depenseDao.getListDepenseParMois(),idMois,annee);
    }

    public void savePlusieur(int jour, int[] tabIntMois, int annee, int idDepense, int prix){
        DepenseFait[] tabDep = fonction2.toTabDepense(idDepense,prix,jour,annee,tabIntMois);
        for (int i = 0; i < tabIntMois.length; i++) {
            insertDepenseFait(tabDep[i]);
        }
    }

    public void insertPlusieurDepenseFait(List<DepenseFait> list){
        depenseDao.insertPlusieurDepenseFait(list);
    }

    public void insertCSV(List<String[]> data){
        List<DepenseFait> list = fonction2.dataCsvtoList(data);
        list = depenseDao.setIdDepensePourCSV(list);
        insertPlusieurDepenseFait(list);
    }

}
