package com.harena.eval_v1.dao;

import com.harena.eval_v1.models.Medicaments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedocDao {

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

    public List<Medicaments> getListMedicament(){
        List<Medicaments> rep = new ArrayList<>();
        Medicaments medicaments;
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM medicaments;");
            while(res.next()){
                medicaments = new Medicaments();
                medicaments.setId(res.getInt(1));
                medicaments.setNomMedoc(res.getString(2));
                medicaments.setPrixMedoc(res.getInt(3));
                rep.add(medicaments);
            }
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rep;
    }

    public void saveMedoc(Medicaments medicaments){
        try{
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res;
            String sql = "insert into medicaments(nommedoc, prixmedoc) values ('"+medicaments.getNomMedoc()+"',"+medicaments.getPrixMedoc()+")";
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

    public void deleteMedoc(int id){
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("DELETE from medicaments where id="+id+";");
            con.close();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
    }

}
