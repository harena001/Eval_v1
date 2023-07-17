package com.harena.eval_v1.dao;

import com.harena.eval_v1.models.Medicaments;
import com.harena.eval_v1.models.Patient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatientDao {

    public Connection getConnection(){
        Connection conn;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/eval3_v1","postgres","root");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public List<Patient> getListPatients(){
        List<Patient> rep = new ArrayList<>();
        Patient patient;
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM patients;");
            while(res.next()){
                patient = new Patient();
                patient.setId(res.getInt(1));
                patient.setNomPatient(res.getString(2));
                patient.setDateDeNaissance(res.getDate(3));
                patient.setGenre(res.getString(4));
                rep.add(patient);
            }
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rep;
    }

    public void savePatient(Patient patient){
        try{
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res;
            String sql = "insert into patients(nompatient, datenaissance, genre) values ('"+patient.getNomPatient()+"','"+patient.getDateDeNaissance()+"','"+patient.getGenre()+"')";
            //System.out.println(sql);
            res = stmt.executeQuery(sql);
            while(res.next()){
            }
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void updateNomMedoc(int id, String nom){
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("UPDATE medicaments set nommedoc='"+nom+"' where id="+id+";");
            con.close();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
    }

    public void updatePrixMedoc(int id, int prix){
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("UPDATE medicaments set prixmedoc="+prix+" where id="+id+";");
            con.close();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
    }

    public void deletePatient(int id){
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("DELETE from patients where id="+id+";");
            con.close();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
    }

}
