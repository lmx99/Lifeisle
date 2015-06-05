package com.lifeisle.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;


public class PDFWriter {

    private String contentString;
    private Font font;

    public PDFWriter(String contentString) throws IOException, DocumentException {
        this.contentString = contentString;

        BaseFont baseFont = BaseFont.createFont("font/STKAITI.TTF", BaseFont.IDENTITY_H,
                BaseFont.NOT_EMBEDDED);
        font = new Font(baseFont, 10, Font.NORMAL);
    }


    public void appendBarCode(String barCodePath, String outputFilePath)
            throws DocumentException, IOException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(outputFilePath));

        document.setPageSize(new Rectangle(165f, 800f));
        document.setMargins(5f, 5f, 10f, 10f);
        document.open();
        addMetaData(document);
        addContent(document, barCodePath);
        document.close();
    }



    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document) {
        document.addTitle("Lifeisle Order");
        document.addSubject("Order that to be delivered");
        document.addKeywords("Lifeisle, order");
        document.addAuthor("Lifeisle");
        document.addCreator("Lifeisle");
    }

    private void addContent(Document document, String barCodePath)
            throws DocumentException, IOException {
        Paragraph content = new Paragraph();

        addEmptyLine(content, 1);
        content.add(new Paragraph(contentString, font));

        addEmptyLine(content, 1);
        Image barcodeImage = Image.getInstance(barCodePath);
        content.add(barcodeImage);

        document.add(content);
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }


    public static void main(String[] args) {
        try {
            new PDFWriter("你好").appendBarCode("E:/test/barcode.png", "E:/test/out.pdf");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
