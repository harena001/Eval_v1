package com.harena.eval_v1.services;

import com.harena.eval_v1.dao.MedocDao;
import com.harena.eval_v1.models.Medicaments;
import org.springframework.aop.target.LazyInitTargetSource;

import java.util.List;

public class MedocService {

    public static MedocDao medocDao = new MedocDao();

    public List<Medicaments> getListMedicament(){
        return medocDao.getListMedicament();
    }

    public void saveMedoc(Medicaments medicaments){
        medocDao.saveMedoc(medicaments);
    }

    public void updateNomMedoc(int id, String nom){
        medocDao.updateNomMedoc(id,nom);
    }

    public void updatePrixMedoc(int id, int prix){
        medocDao.updatePrixMedoc(id,prix);
    }

    public void deleteMedoc(int id){
        medocDao.deleteMedoc(id);
    }

}
