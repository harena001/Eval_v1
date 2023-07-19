package com.harena.eval_v1.dao;

import com.harena.eval_v1.models.Acte;
import com.harena.eval_v1.models.DashRecette;
import com.harena.eval_v1.models.Patient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ActeDao {

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

    public List<Acte> getListActe(){
        List<Acte> rep = new ArrayList<>();
        Acte acte;
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM actes;");
            while(res.next()){
                acte = new Acte();
                acte.setId(res.getInt(1));
                acte.setNomActe(res.getString(2));
                acte.setBudgetAnnuel(res.getInt(3));
                acte.setCode(res.getString(4));
                rep.add(acte);
            }
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rep;
    }

    public List<Acte> getListActeParMois(){
        List<Acte> rep = new ArrayList<>();
        Acte acte;
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM actes;");
            while(res.next()){
                acte = new Acte();
                acte.setId(res.getInt(1));
                acte.setNomActe(res.getString(2));
                acte.setBudgetAnnuel(res.getInt(3) / 12);
                acte.setCode(res.getString(4));
                rep.add(acte);
            }
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rep;
    }

    public void saveActe(Acte acte) {
        try{
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res;
            String sql = "insert into actes(nomacte, budgetannuel, code) values ('"+acte.getNomActe()+"',"+acte.getBudgetAnnuel()+",'"+acte.getCode()+"')";
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

    public void updateNomActe(int idActe, String nom){
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("UPDATE actes set nomacte='"+nom+"' where id="+idActe+";");
            con.close();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
    }
    public void updateBudgetAnnuelActe(int idActe, int budgetAnnuel){
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("UPDATE actes set budgetannuel="+budgetAnnuel+" where id="+idActe+";");
            con.close();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
    }
    public void updateCodeActe(int idActe, String code){
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("UPDATE actes set code='"+code+"' where id="+idActe+";");
            con.close();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
    }

    public void deleteActes(int idActe){
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("DELETE from actes where id="+idActe+";");
            con.close();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
    }

    public void validerPayement(int idGroupeActe){
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("UPDATE groupeacte set statue="+true+" where id="+idGroupeActe+";");
            con.close();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
    }

    public int getBudgetAnnuel(int idActe,Connection con){
        int rep = 0;
        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT budgetannuel FROM actes where id="+idActe+";");
            while(res.next()){
                rep = res.getInt(1);
            }
            //con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rep;
    }

    public List<DashRecette> setBudgetAnnuelDash(List<DashRecette> list){
        try {
            Connection con = getConnection();
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setBudget(getBudgetAnnuel(list.get(i).getIdActe(),con));
                //list.get(i).setRealisation( (list.get(i).getReel() * 100 ) / getBudgetAnnuel(list.get(i).getIdActe(),con) );
            }
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Acte> setBudgetPourActe(List<Acte> list){
        try {
            Connection con = getConnection();
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setBudgetAnnuel( (getBudgetAnnuel(list.get(i).getId(),con) ) / 12 );
                //list.get(i).setBudget(getBudgetAnnuel(list.get(i).getIdActe(),con));
                //list.get(i).setRealisation( (list.get(i).getReel() * 100 ) / getBudgetAnnuel(list.get(i).getIdActe(),con) );
            }
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }



    public int getReelParIdActe(int idMois, int annee,int idActe, Connection con){
        int rep = 0;
        try {
            //Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT sum FROM v_dashrecette where mois="+idMois+" and annee="+annee+" and idacte="+idActe+";");
            while(res.next()){
                rep = res.getInt(1);
            }
            //con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rep;
    }

    public List<Acte> getListeActeRecetteFinal(List<Acte> liste,int idMois, int annee){
        try {
            Connection con = getConnection();
            for (int i = 0; i < liste.size(); i++) {
                liste.get(i).setReel(getReelParIdActe(idMois,annee,liste.get(i).getId(),con));
            }
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return liste;
    }

}
