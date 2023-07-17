package com.harena.eval_v1.dao;

import com.harena.eval_v1.models.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeDAO {

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

    public boolean getById(String email, String motdepasse){
        Employee emp = new Employee();
        boolean rep = false;
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM employees where email='"+email+"' and motdepasse='"+motdepasse+"';");
            rep = res.next();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rep;
    }


}
