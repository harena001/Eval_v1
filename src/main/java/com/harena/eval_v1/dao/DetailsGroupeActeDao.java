package com.harena.eval_v1.dao;

import com.harena.eval_v1.models.DetailsGroupeActe;
import com.harena.eval_v1.models.Patient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailsGroupeActeDao {

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

    public List<DetailsGroupeActe> getListDetailsGroupeActe(int idPatient){
        List<DetailsGroupeActe> rep = new ArrayList<>();
        DetailsGroupeActe detailsGroupeActe;
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT nomacte, prix, date FROM v_detailsacte where idpatient="+idPatient+" and statue=false");
            while(res.next()){
                detailsGroupeActe = new DetailsGroupeActe();
                detailsGroupeActe.setNomActe(res.getString(1));
                detailsGroupeActe.setPrix(res.getInt(2));
                detailsGroupeActe.setDate(res.getDate(3));
                rep.add(detailsGroupeActe);
            }
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rep;
    }

    public int getIdGroupeActe(int idPatient) {
        int rep = 0;
        try{
            Connection conn = getConnection();
            Statement stmt1 = conn.createStatement();
            ResultSet res1;
            String sql1 = "select id from groupeacte where idpatient="+idPatient+" and statue=false;";
            res1 = stmt1.executeQuery(sql1);
            while(res1.next()){
                rep = res1.getInt(1);
            }
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return rep;
    }

    public void NouveauIdGroupeActe(int idPatient) {
        try{
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res;
            String sql = "insert into groupeacte(idpatient, statue) values ("+idPatient+",false)";
            System.out.println(sql);
            res = stmt.executeQuery(sql);
            while(res.next()){
            }
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void saveDetailsGroupeActe(int idGroupeActe, int idActe, int prix, Date date) {
        try{
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res;
            String sql = "insert into detailsgroupeacte(idgroupeacte, idacte, prix, date) values ("+idGroupeActe+","+idActe+","+prix+",'"+date+"')";
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
}
