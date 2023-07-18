package com.harena.eval_v1.dao;

import com.harena.eval_v1.models.Acte;
import com.harena.eval_v1.models.Depense;
import com.harena.eval_v1.models.DepenseFait;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepenseDao {

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

    public List<Depense> getListDepense(){
        List<Depense> rep = new ArrayList<>();
        Depense depense;
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM depenses;");
            while(res.next()){
                depense = new Depense();
                depense.setId(res.getInt(1));
                depense.setNomDepense(res.getString(2));
                depense.setBudgetAnnuel(res.getInt(3));
                depense.setCode(res.getString(4));
                rep.add(depense);
            }
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rep;
    }

    public void saveDepense(Depense depense) {
        try{
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res;
            String sql = "insert into depenses(nomdepense, budgetannuel, code) values ('"+depense.getNomDepense()+"',"+depense.getBudgetAnnuel()+",'"+depense.getCode()+"')";
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

    public void updateNomDepense(int idDepense, String nom){
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("UPDATE depenses set nomdepense='"+nom+"' where id="+idDepense+";");
            con.close();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
    }
    public void updateBudgetAnnuelDepense(int idDepense, int budgetAnnuel){
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("UPDATE depenses set budgetannuel="+budgetAnnuel+" where id="+idDepense+";");
            con.close();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
    }
    public void updateCodeDepense(int idDepense, String code){
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("UPDATE depenses set code='"+code+"' where id="+idDepense+";");
            con.close();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
    }

    public void deleteDepense(int idDepense){
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("DELETE from depenses where id="+idDepense+";");
            con.close();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
    }

    public void insertDepenseFait(DepenseFait depenseFait){
        try{
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res;
            String sql = "insert into depensefait(iddepense, prix, date) values ("+depenseFait.getIdDepense()+","+depenseFait.getPrix()+",'"+depenseFait.getDate()+"')";
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


    public int getReelParIdDepense(int idMois, int annee,int idDepense, Connection con){
        int rep = 0;
        try {
            //Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT sum FROM v_dashdepense where mois="+idMois+" and annee="+annee+" and iddepense="+idDepense+";");
            while(res.next()){
                rep = res.getInt(1);
            }
            //con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rep;
    }

    public List<Depense> getListeDepenseFinal(List<Depense> liste,int idMois, int annee){
        try {
            Connection con = getConnection();
            for (int i = 0; i < liste.size(); i++) {
                liste.get(i).setReel(getReelParIdDepense(idMois,annee,liste.get(i).getId(),con));
            }
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return liste;
    }

}
