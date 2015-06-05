package com.lifeisle.pdf;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import java.io.IOException;

public class PDFReader {

    private String filePath;
    private String contentString;

    public PDFReader(String filePath) {
        this.filePath = filePath;
    }

//    public String readString() throws IOException{
//        if (contentString != null) return contentString;
//
//        PDDocument document;
//        String result;
//
//        FileInputStream fileInputStream = new FileInputStream(filePath);
//        PDFParser pdfParser = new PDFParser(fileInputStream);
//        pdfParser.parse();
//
//        document = pdfParser.getPDDocument();
//        PDFTextStripper stripper = new PDFTextStripper();
//        result = stripper.getText(document);
//
//        fileInputStream.close();
//        document.close();
//
//        contentString = result;
//        return result;
//    }


    public String readString() throws IOException{
        if (contentString != null) return contentString;

        PdfReader reader = new PdfReader(filePath);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        StringBuilder stringBuilder = new StringBuilder();
        TextExtractionStrategy strategy;
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i,
                    new SimpleTextExtractionStrategy());
            stringBuilder.append(strategy.getResultantText());
        }

        contentString = stringBuilder.toString();

        return contentString;
    }




    public  static void main(String[] args) throws IOException {
        try {
            PDFReader operator = new PDFReader("E:/test/1/1.pdf");
            System.out.println(operator.readString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
