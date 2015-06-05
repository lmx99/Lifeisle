package com.lifeisle.barcode;

import org.jbarcode.JBarcode;
import org.jbarcode.encode.EAN13Encoder;
import org.jbarcode.encode.InvalidAtributeException;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Jekton Luo
 * @version 0.01 6/3/2015.
 */
public class BarcodeGenerator {

    public static void generateEAN13(String barcode, String outputPath)
            throws InvalidAtributeException, IOException {

        JBarcode jBarcode = new JBarcode(EAN13Encoder.getInstance(), WidthCodedPainter.getInstance(),
                EAN13TextPainter.getInstance());
        BufferedImage bufferedImage = jBarcode.createBarcode(barcode);

        FileOutputStream outputStream = new FileOutputStream(outputPath);
        ImageUtil.encodeAndWrite(bufferedImage, "png", outputStream, 96, 96);
        outputStream.close();
    }


    public static void main(String[] args) {
        try {
            BarcodeGenerator.generateEAN13("123456789012", "E:/test/barcode.png");
        } catch (InvalidAtributeException | IOException e) {
            e.printStackTrace();
        }
    }
}
