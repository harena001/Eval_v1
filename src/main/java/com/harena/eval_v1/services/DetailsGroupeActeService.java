package com.harena.eval_v1.services;

import com.harena.eval_v1.dao.DetailsGroupeActeDao;
import com.harena.eval_v1.models.DetailsGroupeActe;

import java.util.Date;
import java.util.List;

public class DetailsGroupeActeService {

    public static DetailsGroupeActeDao detailsGroupeActeDao = new DetailsGroupeActeDao();

    public List<DetailsGroupeActe> getListeDetailsGroupeActe(int idPatient){
        return detailsGroupeActeDao.getListDetailsGroupeActe(idPatient);
    }

    public void saveDetailsGroupeActe(int idPatient, int idActe, int prix, Date date){
        int idGroupeActe = detailsGroupeActeDao.getIdGroupeActe(idPatient);
        if (idGroupeActe == 0){
            detailsGroupeActeDao.NouveauIdGroupeActe(idPatient);
            idGroupeActe = detailsGroupeActeDao.getIdGroupeActe(idPatient);
        }
        detailsGroupeActeDao.saveDetailsGroupeActe(idGroupeActe,idActe,prix,date);
    }

}
