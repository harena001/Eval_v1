package com.harena.eval_v1.controllers;

import com.harena.eval_v1.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class EmployeController {

    public static EmployeeService employeeService = new EmployeeService();

    @PostMapping("/Emp/verifLogin")
    public String verifLogin(@RequestParam(name = "email") String email,
                             @RequestParam(name = "password") String password,
                             Model model){
        if (employeeService.verif(email, password)){
            //List<Devis> liste = devisService.getDevisHome();
            //model.addAttribute("liste",liste);
            return "home";
        }else{
            return "redirect:/";
        }
    }

}
