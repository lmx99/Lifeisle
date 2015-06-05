import com.itextpdf.text.DocumentException;
import com.lifeisle.barcode.BarcodeGenerator;
import com.lifeisle.pdf.PDFReader;
import com.lifeisle.pdf.PDFWriter;
import org.jbarcode.encode.InvalidAtributeException;

import java.io.IOException;

public class Tester {




    public static void main(String[] args) {
        try {
            String content = new PDFReader("E:/test/1/2.pdf").readString();
            System.out.println(content);

            String barcode = "012345678912";
            String barcodePath = "E:/test/barcode.png";
            BarcodeGenerator.generateEAN13(barcode, barcodePath);
            new PDFWriter(content).appendBarCode(barcodePath, "E:/test/out.pdf");
        } catch (IOException | DocumentException | InvalidAtributeException e) {
            e.printStackTrace();
        }
    }
}
