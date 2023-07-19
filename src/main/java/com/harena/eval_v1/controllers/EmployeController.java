package com.harena.eval_v1.controllers;

import com.harena.eval_v1.fonctions.Fonction1;
import com.harena.eval_v1.fonctions.Fonction2;
import com.harena.eval_v1.models.*;
import com.harena.eval_v1.services.*;
import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class EmployeController {

    public static EmployeeService employeeService = new EmployeeService();

    public static PatientService patientService = new PatientService();
    public static DetailsGroupeActeService detailsGroupeActeService = new DetailsGroupeActeService();
    public static Fonction1 fonction1 = new Fonction1();
    public static ActeService acteService = new ActeService();
    public static DepenseService depenseService = new DepenseService();
    public static Fonction2 fonction2 = new Fonction2();

    @PostMapping("/Emp/verifLogin")
    public String verifLogin(@RequestParam(name = "email") String email,
                             @RequestParam(name = "password") String password,
                             Model model){
        if (employeeService.verif(email, password)){
            //List<Devis> liste = devisService.getDevisHome();
            //model.addAttribute("liste",liste);
            model.addAttribute("listePatient",patientService.getAllPatient());
            return "redirect:/Emp/toPageRecette";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/Emp/toPageRecette")
    public String toRecettePage(Model model){
        model.addAttribute("listePatient",patientService.getAllPatient());
        return "EmpListePatients";
    }

    @GetMapping("/Emp/Search")
    public String search(Model model,
                         @RequestParam(name = "str") String str){
        List<Patient> liste = patientService.getAllPatient();
        model.addAttribute("listePatient",fonction2.search(liste,str));
        return "EmpListePatients";
    }

    @GetMapping("/Emp/logout")
    public String empLogout(){
        return "redirect:/";
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

        //model.addAttribute("idGroupeActe",list.get(0).getIdGroupeActe());

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

    @GetMapping("/Emp/Depenses")
    public String toFormDepense(Model model){
        model.addAttribute("liste",depenseService.getListDepense());
        return "EmpFormDepense";
    }

    @PostMapping("/Emp/ajoutDepense")
    public String insertDepenseFait(@RequestParam(name = "idDepense") String idDepense,
                                    @RequestParam(name = "prix") String prix,
                                    @RequestParam(name = "jour") String jour,
                                    @RequestParam(name = "annee") String annee,
                                    @RequestParam("mois") List<String> mois){

        int jourf = Integer.parseInt(jour);
        int prixf = Integer.parseInt(prix);
        int anneef = Integer.parseInt(annee);
        int idDep = Integer.parseInt(idDepense);

        int[] tabIntMois = fonction2.listToTabInt(mois);
        depenseService.savePlusieur(jourf,tabIntMois,anneef,idDep,prixf);
        return "redirect:/Emp/Depenses";
    }

    @PostMapping("/Emp/validePayement")
    public String validerPayement(@RequestParam(name = "idPatient") String idPatient) throws DocumentException, IOException {
        int idGroupeActe = detailsGroupeActeService.getIdGroupeActeAuDebut(Integer.parseInt(idPatient));
        if (idGroupeActe == 0){
            return "redirect:/Emp/voirDetailActe/"+idPatient;
        }

        PDFGenerator pdfGenerator = new PDFGenerator();
        String nomFichier = idGroupeActe+"_Facture_Patientid_"+idPatient+".pdf";
        pdfGenerator.generatePDF("D:\\S6\\Evaluation\\Evaluation-Juillet\\"+nomFichier,Integer.parseInt(idPatient));

        acteService.validerPayement(idGroupeActe);

        return "redirect:/Emp/toPageRecette";
    }



    ////////////////////////// CSV ////////////////

    public CSVImporter csvImporter = new CSVImporter();
    @PostMapping("/import-csv")
    public String importCSV(@RequestParam("file") MultipartFile file, Model model) {
        try {
            List<String[]> data = csvImporter.importCSV(file);

            depenseService.insertCSV(data);

            model.addAttribute("data", data);
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs d'importation CSV
        }
        return "redirect:/Emp/Depenses";
    }


}
