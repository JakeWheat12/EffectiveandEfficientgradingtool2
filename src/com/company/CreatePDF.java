package com.company;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


/**
 * A class for creating PDF file using pdfbox library
 *
 * @Author Hyungsuk Kim
 */

public class CreatePDF {


    // @todo will also receive a list of comments
    public static void createPDF(String path) throws IOException{

        // @todo implement PDF creation and save

        // creates PDF and a page
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // writes to PDF
        contentStream.setFont(PDType1Font.COURIER, 12);
        contentStream.beginText();
        contentStream.showText("Testing pdf text insertion");
        contentStream.endText();
        contentStream.close();

        // saves PDF to selected directory
        document.save(path + ".pdf");
        document.close();
    }

}
