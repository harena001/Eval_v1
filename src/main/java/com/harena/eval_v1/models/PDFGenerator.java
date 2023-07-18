package com.harena.eval_v1.models;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class PDFGenerator {

    public void generatePDF(String filePath) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));

        document.open();

        // Ajoutez le contenu du PDF ici
        document.add(new Paragraph("Hello, PDF!"));

        Image image = Image.getInstance("/Image/FB_IMG_1663002087596.jpg");
        image.scaleAbsolute(200, 200);

        Image image1 = Image.getInstance("/Image/pexels-media-1724888-1663760805099.jpeg");
        image1.scaleAbsolute(200, 200);


        PdfPTable table = new PdfPTable(2); // 2 colonnes pour l'exemple
        table.setWidthPercentage(100); // Utilisez toute la largeur de la page
        // Supprimez la bordure de la table
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        // Ajoutez les cellules à la table avec la bordure supprimée
        PdfPCell cell1 = new PdfPCell(new Phrase("Contenu de la cellule 1"));
        cell1.setBorder(PdfPCell.NO_BORDER);
        cell1.addElement(image);
        table.addCell(cell1);

        PdfPCell cell2 = new PdfPCell(new Phrase("Contenu de la cellule 2"));
        cell2.setBorder(PdfPCell.NO_BORDER);
        cell2.addElement(image1);
        table.addCell(cell2);

        document.add(table);

        document.close();
    }

}
