/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.adminview;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.msi.DAO.StockDAO;
import java.io.FileOutputStream;
import java.util.Date;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author MSI
 */
public class ProductStockInfoReport {
    String currency = "Taka";
    String BarcodeText = "Barcode UPCA";
    public void createReport(String URL) {
          
           ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
            StockDAO allProDAO = (StockDAO) context.getBean("StockDAO");
            Stock stock =new Stock();
            allProDAO.AllProductStock(stock);
        String empty = "----------------------------------------------------------------------------------------------------------------------------------";

        Document d = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(URL));
            d.open();

            //Barcode 
            PdfContentByte CB = writer.getDirectContent();
            BarcodeEAN codeEAN = new BarcodeEAN();
            codeEAN.setCode("1234567891011");
            d.add(new Paragraph(BarcodeText));
            codeEAN.setCodeType(Barcode.UPCA);
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));

            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));

            Image image = Image.getInstance("src\\com\\msi\\image\\Capture.JPG");
            image.scaleAbsolute(550, 200);
            image.setAlignment(Element.ALIGN_CENTER);

            d.add(image);

            d.add(new Paragraph(empty));
            Paragraph pa = new Paragraph("Supplier and their products report", FontFactory.getFont(FontFactory.TIMES_BOLD, 30, Font.BOLD, BaseColor.GRAY));
            pa.setAlignment(Element.ALIGN_CENTER);

            d.add(pa);
            d.add(new Paragraph("\n"));

            Paragraph pDate = new Paragraph(new Date().toString());
            pDate.setAlignment(Element.ALIGN_CENTER);
            d.add(pDate);
            d.add(new Paragraph("\n"));
            d.add(new Paragraph(empty));

            d.newPage();
            //Barcode
            d.add(new Paragraph(BarcodeText));
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
            d.add(new Paragraph("\n"));

            PdfPTable table4 = new PdfPTable(4);
            PdfPCell cellID3 = new PdfPCell(new Paragraph("Serial No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellID3.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cellID3);

            PdfPCell cell14 = new PdfPCell(new Paragraph("Name", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell14);

            PdfPCell cell15 = new PdfPCell(new Paragraph("Amount", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell15);

            PdfPCell cell16 = new PdfPCell(new Paragraph("Unit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell16);

            for (int i = 0; i < stock.PName.size(); i++) {

                PdfPCell cell1 = new PdfPCell(new Phrase((i + 1) + ""));
                PdfPCell cell2 = new PdfPCell(new Phrase(stock.PName.get(i)));
                if (stock.PAmount.get(i) <= 5) {
                    cell1.setBackgroundColor(BaseColor.RED);
                    cell2.setBackgroundColor(BaseColor.RED);
                } else if (stock.PAmount.get(i) > 5 && stock.PAmount.get(i) < 10) {
                    cell1.setBackgroundColor(BaseColor.YELLOW);

                    cell2.setBackgroundColor(BaseColor.YELLOW);
                } else if (stock.PAmount.get(i) >= 10) {
                    cell1.setBackgroundColor(BaseColor.GREEN);

                    cell2.setBackgroundColor(BaseColor.GREEN);
                }
                table4.addCell(cell1);
                table4.addCell(cell2);
                //  table4.addCell(PName.get(i));
                table4.addCell(stock.PAmount.get(i) + "");
                table4.addCell(stock.Unit.get(i));

            }

            d.add(table4);

            d.add(new Paragraph("\n"));
context.close();
            d.close();
            

        } catch (Exception e) {

        }

    }
}
