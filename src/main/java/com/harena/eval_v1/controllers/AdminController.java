package com.harena.eval_v1.controllers;

import com.harena.eval_v1.fonctions.Fonction1;
import com.harena.eval_v1.models.*;
import com.harena.eval_v1.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
public class AdminController {

    public static MedocService medocService = new MedocService();
    public static PatientService patientService = new PatientService();
    public static Fonction1 fonction1 = new Fonction1();
    public static ActeService acteService = new ActeService();
    public static DepenseService depenseService = new DepenseService();
    public static DashRecetteService dashRecetteService = new DashRecetteService();

    @GetMapping("/Admin/Medocs")
    public String listMedocs(Model model){
        model.addAttribute("listeMedoc",medocService.getListMedicament());
        return "AdminListeMedocs";
    }

    @PostMapping("/Admin/ToDash1")
    public String toDash1(@ModelAttribute("idMois")String idMois,
                          @ModelAttribute("annee")String annee){
        return "redirect:/Admin/Dash/"+idMois+"/"+annee;
    }

    @GetMapping("/Admin/Dash/{idMois}/{annee}")
    public String toDachboard(@PathVariable("idMois") String idMois,
                              @ModelAttribute("annee")String anne,
                              Model model){

        int mois = Integer.parseInt(idMois);
        int annee = Integer.parseInt(anne);

        //List<DashRecette> liste = dashRecetteService.getDashRecette(mois,annee);
        //liste = fonction1.setPourcentage(liste);
        List<Acte> liste = acteService.getListeActeRecetteFin(mois,annee);
        liste = fonction1.setPourcentage(liste);
        model.addAttribute("listeRecette",liste);

        int totalReel = fonction1.totaleReel(liste);
        model.addAttribute("totalReel",totalReel);
        int totalBudget = fonction1.totaleBudget(liste);
        model.addAttribute("totalBudget",totalBudget);
        int totalRea = fonction1.totaleRealisation(liste);
        model.addAttribute("totalRea",totalRea);

        List<Depense> listeDepense = depenseService.getListeDepenseFin(mois,annee);
        listeDepense = fonction1.setPourcentageDepense(listeDepense);
        model.addAttribute("listeDepense",listeDepense);

        int totalReeld = fonction1.totaleReeld(listeDepense);
        model.addAttribute("totalReeld",totalReeld);
        int totalBudgetd = fonction1.totaleBudgetd(listeDepense);
        model.addAttribute("totalBudgetd",totalBudgetd);
        int totalRead = fonction1.totaleRealisationd(listeDepense);
        model.addAttribute("totalRead",totalRead);

        model.addAttribute("totalReelBenef",totalReel + totalReeld);
        model.addAttribute("totalBudgetBenef",totalBudget + totalBudgetd);
        model.addAttribute("totalReaBenef",(totalRea + totalRead) / 2);

        return "index";
    }

    @PostMapping("/verifLoginAdmin")
    public String verifAdmin(@RequestParam(name = "email") String email,
                             @RequestParam(name = "password") String password,
                             @ModelAttribute("idMois")String idMois,
                             @ModelAttribute("annee")String annee){
        if (Objects.equals(email, "admin") && Objects.equals(password, "admin")){
            return "redirect:/Admin/Dash/"+idMois+"/"+annee;
        }else{
            return "redirect:/admin";
        }
    }

    @GetMapping("/Admin/logout")
    public String AdminLogout(){
        return "redirect:/";
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
    public String saveActe(@RequestParam(name = "nom") String nom,
                           @RequestParam(name = "budget") String budget,
                           @RequestParam(name = "code") String code){
        Acte acte = new Acte();
        acte.setNomActe(nom);
        acte.setBudgetAnnuel(Integer.parseInt(budget));
        acte.setCode(code);
        acteService.saveActe(acte);
        return "redirect:/Admin/Actes";
    }

    @PostMapping("/Admin/updateNomActe")
    public String updateActe(@RequestParam(name = "idActe") String idActe,
                             @RequestParam(name = "nom") String nom){
        acteService.updateNomActe(Integer.parseInt(idActe),nom);
        return "redirect:/Admin/Actes";
    }
    @PostMapping("/Admin/updateBudgetAnnuelActe")
    public String updateBudgetAnnuelActe(@RequestParam(name = "idActe") String idActe,
                             @RequestParam(name = "budget") String budget){
        acteService.updateBudgetAnnuelActe(Integer.parseInt(idActe),Integer.parseInt(budget));
        return "redirect:/Admin/Actes";
    }
    @PostMapping("/Admin/updateCodeActe")
    public String updateCodeActe(@RequestParam(name = "idActe") String idActe,
                             @RequestParam(name = "code") String code){
        acteService.updateCodeActe(Integer.parseInt(idActe),code);
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
    public String saveDepense(@RequestParam(name = "nom") String nom,
                              @RequestParam(name = "budget") String budget,
                              @RequestParam(name = "code") String code){
        Depense depense = new Depense();
        depense.setNomDepense(nom);
        depense.setBudgetAnnuel(Integer.parseInt(budget));
        depense.setCode(code);
        depenseService.saveDepense(depense);
        return "redirect:/Admin/Depenses";
    }

    @PostMapping("/Admin/updateNomDepense")
    public String updateNomDepense(@RequestParam(name = "idDepense") String idDepense,
                             @RequestParam(name = "nom") String nom){
        depenseService.updateNomDepense(Integer.parseInt(idDepense),nom);
        return "redirect:/Admin/Depenses";
    }
    @PostMapping("/Admin/updateBudgetAnnuelDepense")
    public String updateBudgetAnnuelDepense(@RequestParam(name = "idDepense") String idDepense,
                                   @RequestParam(name = "budget") String budget){
        depenseService.updateBudgetAnnuelDepense(Integer.parseInt(idDepense),Integer.parseInt(budget));
        return "redirect:/Admin/Depenses";
    }
    @PostMapping("/Admin/updateCodeDepense")
    public String updateCodeDepense(@RequestParam(name = "idDepense") String idDepense,
                                   @RequestParam(name = "code") String code){
        depenseService.updateCodeDepense(Integer.parseInt(idDepense),code);
        return "redirect:/Admin/Depenses";
    }

    @PostMapping("/Admin/deleteDepense")
    public String deleteDepense(@RequestParam(name = "idDepense") String idDepense){
        depenseService.deleteDepense(Integer.parseInt(idDepense));
        return "redirect:/Admin/Depenses";
    }

}
