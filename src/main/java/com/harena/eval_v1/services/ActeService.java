package com.harena.eval_v1.services;

import com.harena.eval_v1.dao.ActeDao;
import com.harena.eval_v1.models.Acte;

import java.util.List;

public class ActeService {

    public static ActeDao acteDao = new ActeDao();

    public List<Acte> getListActe(){
        return acteDao.getListActe();
    }

}
