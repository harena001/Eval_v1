package com.harena.eval_v1.services;

import com.harena.eval_v1.dao.EmployeeDAO;

public class EmployeeService {

    public static EmployeeDAO employeeDAO = new EmployeeDAO();

    public boolean verif(String email, String motdepasse){
        return employeeDAO.getById(email,motdepasse);
    }
}
