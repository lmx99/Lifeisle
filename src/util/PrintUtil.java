package util;


import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PrintUtil {

	/**
	 * 打印
	 */
	public static void print() {
		PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
		DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(flavor, attributeSet);
		PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
		PrintService service = ServiceUI.printDialog(null, 200, 200, printServices,
				defaultService, flavor, attributeSet);
		if (service != null) {
			try {
				DocPrintJob printJob = service.createPrintJob();
				attributeSet.add(MediaSizeName.ISO_A4);
				FileInputStream fis = new FileInputStream("E:\\BaiduYunDownload\\20.pdf");
				DocAttributeSet das = new HashDocAttributeSet();
				Doc doc = new SimpleDoc(fis, flavor, das);
				printJob.print(doc, attributeSet);
				Thread.sleep(10 * 1000);
			} catch (FileNotFoundException | InterruptedException | PrintException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("打印失败");
		}
	}


	public static void main(String[] args) {
		print();
	}

}

