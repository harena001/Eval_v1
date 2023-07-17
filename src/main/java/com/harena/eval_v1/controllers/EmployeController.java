package com.harena.eval_v1.controllers;

import com.harena.eval_v1.fonctions.Fonction1;
import com.harena.eval_v1.models.DetailsGroupeActe;
import com.harena.eval_v1.models.Patient;
import com.harena.eval_v1.services.ActeService;
import com.harena.eval_v1.services.DetailsGroupeActeService;
import com.harena.eval_v1.services.EmployeeService;
import com.harena.eval_v1.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class EmployeController {

    public static EmployeeService employeeService = new EmployeeService();

    public static PatientService patientService = new PatientService();
    public static DetailsGroupeActeService detailsGroupeActeService = new DetailsGroupeActeService();
    public static Fonction1 fonction1 = new Fonction1();
    public static ActeService acteService = new ActeService();

    @PostMapping("/Emp/verifLogin")
    public String verifLogin(@RequestParam(name = "email") String email,
                             @RequestParam(name = "password") String password,
                             Model model){
        if (employeeService.verif(email, password)){
            //List<Devis> liste = devisService.getDevisHome();
            //model.addAttribute("liste",liste);
            model.addAttribute("listePatient",patientService.getAllPatient());
            return "EmpListePatients";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/Emp/voirDetailActe/{idPatient}")
    public String toDetailActe(@PathVariable("idPatient") int idPatient,
                               Model model){
        Patient patient = patientService.getById(idPatient);
        model.addAttribute("patient",patient);
        List<DetailsGroupeActe> list = detailsGroupeActeService.getListeDetailsGroupeActe(idPatient);
        model.addAttribute("listeDetails",list);
        int totale = fonction1.totalePrix(list);
        model.addAttribute("totale",totale);

        model.addAttribute("listeActe",acteService.getListActe());

        return "EmpVoirDetailActe";
    }

    @PostMapping("/Emp/saveDetailsGroupeActe")
    public String saveDetailsGroupeActe(@RequestParam(name = "idActe") String idActe,
                                        @RequestParam(name = "prix") String prix,
                                        @RequestParam(name = "date") String date,
                                        @RequestParam(name = "idPatient") String idPatient){
        detailsGroupeActeService.saveDetailsGroupeActe(Integer.parseInt(idPatient),Integer.parseInt(idActe),
                Integer.parseInt(prix),fonction1.stringToDate(date));
        return "redirect:/Emp/voirDetailActe/"+idPatient;
    }

}
