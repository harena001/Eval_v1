package com.harena.eval_v1.controllers;

import com.harena.eval_v1.models.Medicaments;
import com.harena.eval_v1.services.MedocService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class AdminController {

    public static MedocService medocService = new MedocService();

    @GetMapping("/Admin/Medocs")
    public String listMedocs(Model model){
        model.addAttribute("listeMedoc",medocService.getListMedicament());
        return "AdminListeMedocs";
    }

    @PostMapping("/verifLoginAdmin")
    public String verifAdmin(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password){
        if (Objects.equals(email, "admin") && Objects.equals(password, "admin")){
            return "redirect:/Admin/Medocs";
        }else{
            return "redirect:/admin";
        }
    }

    @GetMapping("/Admin/newMedoc")
    public String toNewMedoc(){
        return "AdminNouveauMedoc";
    }

    @PostMapping("/Admin/saveMedoc")
    public String saveMedoc(@ModelAttribute("nom")String nom,
                            @ModelAttribute("prix")String prix){
        Medicaments medicaments = new Medicaments();
        medicaments.setNomMedoc(nom);
        medicaments.setPrixMedoc(Integer.parseInt(prix));
        medocService.saveMedoc(medicaments);
        return "redirect:/Admin/Medocs";
    }

    @PostMapping("/Admin/updateNom")
    public String updateNomMedoc(@ModelAttribute("id")String id,
                                 @ModelAttribute("nom")String nom){
        medocService.updateNomMedoc(Integer.parseInt(id),nom);
        return "redirect:/Admin/Medocs";
    }

    @PostMapping("/Admin/updatePrix")
    public String updatePrixMedoc(@ModelAttribute("id")String id,
                                 @ModelAttribute("prix")String prix){
        medocService.updatePrixMedoc(Integer.parseInt(id),Integer.parseInt(prix));
        return "redirect:/Admin/Medocs";
    }

    @PostMapping("/Admin/deleteMedoc")
    public String deleteMedoc(@ModelAttribute("id")String id){
        medocService.deleteMedoc(Integer.parseInt(id));
        return "redirect:/Admin/Medocs";
    }

}
