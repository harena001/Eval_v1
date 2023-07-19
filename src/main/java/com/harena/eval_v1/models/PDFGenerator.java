package com.harena.eval_v1.models;

import com.harena.eval_v1.fonctions.Fonction1;
import com.harena.eval_v1.fonctions.Fonction2;
import com.harena.eval_v1.services.DetailsGroupeActeService;
import com.harena.eval_v1.services.PatientService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PDFGenerator {

    public static DetailsGroupeActeService detailsGroupeActeService = new DetailsGroupeActeService();
    public static PatientService patientService = new PatientService();
    public Fonction1 fonction1 = new Fonction1();

    public void generatePDF(String filePath, int idPatient) throws IOException, DocumentException {

        List<DetailsGroupeActe> liste = detailsGroupeActeService.getListeDetailsGroupeActe(idPatient);
        Patient patient = patientService.getById(idPatient);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));

        document.open();

        // Ajoutez le contenu du PDF ici

        ///Brand
        Image image = Image.getInstance("C:\\Users\\ASUS\\Pictures\\Clinique-ko\\Brand.PNG");
        document.add(image);
        ///Ligne
        /*LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineWidth(1f);
        lineSeparator.setLineColor(BaseColor.BLACK);
        Paragraph lineParagraph = new Paragraph();
        lineParagraph.add(lineSeparator);
        lineParagraph.setAlignment(Element.ALIGN_CENTER);
        document.add(lineParagraph);*/
        ///Patient
        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12); // Police en gras
        Paragraph paragraph = new Paragraph();
        paragraph.setFont(boldFont);

        paragraph.add(patient.getNomPatient());
        document.add(paragraph);

        Paragraph paragraphAge = new Paragraph();
        paragraphAge.add(patient.getAge()+" ans");
        document.add(paragraphAge);

        Paragraph paragraphEspace = new Paragraph();
        paragraphEspace.add(" ");
        document.add(paragraphEspace);

        ///Table acte
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        Paragraph paragraphDate = new Paragraph();
        paragraphDate.add("Date");
        Paragraph paragraphNomActe = new Paragraph();
        paragraphNomActe.add("Nom acte");
        Paragraph paragraphPrix = new Paragraph();
        paragraphPrix.add("Prix");

        PdfPCell cell1 = new PdfPCell(new Phrase("Date"));
        PdfPCell cell2 = new PdfPCell(new Phrase("Nom Acte"));
        PdfPCell cell3 = new PdfPCell(new Phrase("Prix"));
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);

        for (int i = 0; i < liste.size(); i++) {
            PdfPCell cell4 = new PdfPCell(new Phrase(String.valueOf(liste.get(i).getDate())));
            PdfPCell cell5 = new PdfPCell(new Phrase(liste.get(i).getNomActe()));
            PdfPCell cell6 = new PdfPCell(new Phrase(liste.get(i).getPrix() + " Ar"));
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
        }

        PdfPCell cell7= new PdfPCell(new Phrase(""));
        PdfPCell cell8= new PdfPCell(new Phrase("Totale :"));
        PdfPCell cell9 = new PdfPCell(new Phrase(fonction1.totalePrix(liste) + " Ar"));
        table.addCell(cell7);
        table.addCell(cell8);
        table.addCell(cell9);



        document.add(table);

        document.close();
    }

}
