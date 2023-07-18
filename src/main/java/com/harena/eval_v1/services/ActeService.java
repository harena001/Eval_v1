package com.harena.eval_v1.services;

import com.harena.eval_v1.dao.ActeDao;
import com.harena.eval_v1.models.Acte;

import java.util.List;

public class ActeService {

    public static ActeDao acteDao = new ActeDao();

    public List<Acte> getListActe(){
        return acteDao.getListActe();
    }

    public void saveActe(String nom){
        acteDao.saveActe(nom);
    }

    public void updateActe(int idActe, String nom){
        acteDao.updateActe(idActe,nom);
    }

    public void deleteActe(int idActe){
        acteDao.deleteActes(idActe);
    }

    public void validerPayement(int idGroupeActe){
        acteDao.validerPayement(idGroupeActe);
    }

}
