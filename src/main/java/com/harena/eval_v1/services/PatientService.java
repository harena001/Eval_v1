package com.harena.eval_v1.services;

import com.harena.eval_v1.dao.PatientDao;
import com.harena.eval_v1.models.Patient;

import java.util.Date;
import java.util.List;

public class PatientService {

    public static PatientDao patientDao = new PatientDao();

    public List<Patient> getAllPatient(){
        return patientDao.getListPatients();
    }

    public Patient getById(int id){
        return patientDao.getById(id);
    }

    public void savePatient(Patient patient){
        patientDao.savePatient(patient);
    }

    public void updateNomPatient(int id, String nom){
        patientDao.updateNomPatient(id,nom);
    }
    public void updateDateNaissance(int id, Date date){
        patientDao.updateDateNaissance(id,date);
    }
    public void updateGenre(int id, String genre){
        patientDao.updateGenrePatient(id,genre);
    }

}
