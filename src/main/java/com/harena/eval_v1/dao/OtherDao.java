package com.harena.eval_v1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OtherDao {

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

    public List<Integer> getListAnneeValide() {
        List<Integer> rep = new ArrayList<>();
        try{
            Connection conn = getConnection();
            Statement stmt1 = conn.createStatement();
            ResultSet res1;
            String sql1 = "select annee from v_dashRecette union select annee from v_dashDepense;";
            res1 = stmt1.executeQuery(sql1);
            while(res1.next()){
                rep.add(res1.getInt(1));
            }
            conn.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return rep;
    }
}
