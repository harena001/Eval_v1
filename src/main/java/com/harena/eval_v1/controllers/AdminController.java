package com.harena.eval_v1.controllers;

import com.harena.eval_v1.fonctions.Fonction1;
import com.harena.eval_v1.models.Medicaments;
import com.harena.eval_v1.models.Patient;
import com.harena.eval_v1.services.ActeService;
import com.harena.eval_v1.services.DepenseService;
import com.harena.eval_v1.services.MedocService;
import com.harena.eval_v1.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class AdminController {

    public static MedocService medocService = new MedocService();
    public static PatientService patientService = new PatientService();
    public static Fonction1 fonction1 = new Fonction1();
    public static ActeService acteService = new ActeService();
    public static DepenseService depenseService = new DepenseService();

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

    @GetMapping("/Admin/Patient")
    public String toListePatients(Model model){
        model.addAttribute("listePatient",patientService.getAllPatient());
        return "AdminListePatients";
    }

    @GetMapping("/Admin/newPatient")
    public String toNewPatient(){
        return "AdminFormPatient";
    }

    @PostMapping("/Admin/savePatient")
    public String savePatient(@ModelAttribute("nom")String nom,
                              @ModelAttribute("date")String date,
                              @ModelAttribute("genre")String genre,
                              Model model){
        Patient patient = new Patient();
        patient.setNomPatient(nom);
        patient.setDateDeNaissance(fonction1.stringToDate(date));
        patient.setGenre(genre);
        patientService.savePatient(patient);
        return "redirect:/Admin/Patient";
    }

    @GetMapping("/Admin/modif/{idPatient}")
    public String toFormModifPatient(@PathVariable("idPatient") int idPatient,
                                     Model model){
        model.addAttribute("patient",patientService.getById(idPatient));
        return "AdminModifPatient";
    }

    @PostMapping("/Admin/updatePatient")
    public String updatePatient(@ModelAttribute("id") String id,
                                @ModelAttribute("nom") String nom,
                                @ModelAttribute("date") String date,
                                @ModelAttribute("genre") String genre){
        if (!Objects.equals(nom, "")){
            patientService.updateNomPatient(Integer.parseInt(id),nom);
        }
        if (!Objects.equals(date, "")){
            patientService.updateDateNaissance(Integer.parseInt(id),fonction1.stringToDate(date));
        }
        if (!Objects.equals(genre, "")){
            patientService.updateGenre(Integer.parseInt(id),genre);
        }
        return "redirect:/Admin/Patient";
    }

    @GetMapping("/Admin/Actes")
    public String toPageActes(Model model){
        model.addAttribute("liste",acteService.getListActe());
        return "AdminListeActes";
    }

    @PostMapping("/Admin/newActe")
    public String saveActe(@RequestParam(name = "nom") String nom){
        acteService.saveActe(nom);
        return "redirect:/Admin/Actes";
    }

    @PostMapping("/Admin/updateActe")
    public String updateActe(@RequestParam(name = "idActe") String idActe,
                             @RequestParam(name = "nom") String nom){
        acteService.updateActe(Integer.parseInt(idActe),nom);
        return "redirect:/Admin/Actes";
    }

    @PostMapping("/Admin/deleteActe")
    public String deleteActe(@RequestParam(name = "idActe") String idActe){
        acteService.deleteActe(Integer.parseInt(idActe));
        return "redirect:/Admin/Actes";
    }



    @GetMapping("/Admin/Depenses")
    public String toPageDepenses(Model model){
        model.addAttribute("liste",depenseService.getListDepense());
        return "AdminListeDepenses";
    }

    @PostMapping("/Admin/newDepense")
    public String saveDepense(@RequestParam(name = "nom") String nom){
        depenseService.saveDepence(nom);
        return "redirect:/Admin/Depenses";
    }

    @PostMapping("/Admin/updateDepense")
    public String updateDepense(@RequestParam(name = "idDepense") String idDepense,
                             @RequestParam(name = "nom") String nom){
        depenseService.updateDepense(Integer.parseInt(idDepense),nom);
        return "redirect:/Admin/Depenses";
    }

    @PostMapping("/Admin/deleteDepense")
    public String deleteDepense(@RequestParam(name = "idDepense") String idDepense){
        depenseService.deleteDepense(Integer.parseInt(idDepense));
        return "redirect:/Admin/Depenses";
    }

}
