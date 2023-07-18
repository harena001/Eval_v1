package com.harena.eval_v1.dao;

import com.harena.eval_v1.models.Acte;
import com.harena.eval_v1.models.DashRecette;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DashRecetteDao {

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

    public List<DashRecette> getListDashRecette(int idMois, int annee){
        List<DashRecette> rep = new ArrayList<>();
        DashRecette dashRecette;
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT idacte, nomacte, sum FROM v_dashrecette where mois="+idMois+" and annee="+annee+";");
            while(res.next()){
                dashRecette = new DashRecette();
                dashRecette.setIdActe(res.getInt(1));
                dashRecette.setNomActe(res.getString(2));
                dashRecette.setReel(res.getInt(3));
                rep.add(dashRecette);
            }
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rep;
    }





}
