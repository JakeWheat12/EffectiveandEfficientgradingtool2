package com.company;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.ArrayList;


/**
 * A class for creating PDF file using pdfbox library
 * @Author Hyungsuk Kim
 */

public class CreatePDF {

    /**
     * constants for margin, width, and length
     */
    private static final int MAX_LINES = 33;
    private static final int MAX_LENGTH = 65;
    private static final int FONT_SIZE = 16;
    private static final int OFFSET_W = 50;
    private static final int OFFSET_H = 700;
    /**
     * @SPACING spacing between different comments
     * @SPACING_TL spacing between TOO LONG comments
     */
    private static final int SPACING = -23;
    private static final int SPACING_TL = -20;
    /**
     * @TITLE title
     */
    private static final String TITLE = "Rubric / Comments";


    /**
     * Creates a pdf file filled with comments from @src/com/company/MainFrame.java
     * @param path directory to be saved
     * @param list list of comments the User chose
     * @throws IOException
     */
    public static void createPDF_saveAs(String path, ArrayList<String> list) throws IOException{

        int num_lines = 5; // number of lines. starts at 3 because of @TITLE
        PDFont font = PDType1Font.HELVETICA; // font

        // creates PDF
        PDDocument document = new PDDocument();

        // creates a page
        PDPage page = new PDPage();
        document.addPage(page);

        // writes to PDF
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();

        // title
        contentStream.setFont(font, FONT_SIZE*2);
        contentStream.newLineAtOffset(OFFSET_W+120, OFFSET_H+40);
        contentStream.showText(TITLE);
        // content
        contentStream.setFont(font, FONT_SIZE);
        contentStream.newLineAtOffset(-120, SPACING*2);

        list = Helper.RemoveNewLine(list);

        for (String str : list) {
            if (num_lines > MAX_LINES)  { // create a new page if current page is filled
                contentStream.endText();
                contentStream.close();
                // creates a page
                page = new PDPage();
                document.addPage(page);
                // writes to PDF
                contentStream = new PDPageContentStream(document, page);
                contentStream.beginText();
                contentStream.setFont(font, FONT_SIZE);
                contentStream.newLineAtOffset(OFFSET_W, OFFSET_H);
                num_lines = 0; // reinitlaize to 0
            }
            // length check
            while (str.length() > MAX_LENGTH) {
                if (num_lines > MAX_LINES) { // create a new page if current page is filled
                    contentStream.endText();
                    contentStream.close();
                    // creates a page
                    page = new PDPage();
                    document.addPage(page);
                    // writes to PDF
                    contentStream = new PDPageContentStream(document, page);
                    contentStream.beginText();
                    contentStream.setFont(font, FONT_SIZE);
                    contentStream.newLineAtOffset(OFFSET_W, OFFSET_H);
                    num_lines = 0; // reinitlaize to 0
                }
                contentStream.showText(str.substring(0, MAX_LENGTH));
                contentStream.newLineAtOffset(0, SPACING_TL);
                num_lines++;
                str = str.substring(MAX_LENGTH);
            }
            contentStream.showText(str);
            contentStream.newLineAtOffset(0, SPACING);
            num_lines++;
        }

        contentStream.endText();
        // end of writing
        contentStream.close();

        // saves PDF to selected directory
        document.save(path + ".pdf");
        document.close();
    }


}
