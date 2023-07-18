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

    public void saveDepence (String nom){
        depenseDao.saveDepense(nom);
    }

    public void updateDepense(int idDepense, String nom){
        depenseDao.updateDepense(idDepense,nom);
    }

    public void deleteDepense(int idDepense){
        depenseDao.deleteDepense(idDepense);
    }

    public void insertDepenseFait(DepenseFait depenseFait){
        depenseDao.insertDepenseFait(depenseFait);
    }

}
