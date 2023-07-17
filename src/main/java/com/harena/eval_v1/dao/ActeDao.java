package com.harena.eval_v1.dao;

import com.harena.eval_v1.models.Acte;
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
                rep.add(acte);
            }
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rep;
    }

}
