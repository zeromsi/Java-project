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
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.Date;

/**
 *
 * @author MSI
 */
public class LastDaysReportOfSoldAmount {
    
    String currency="Taka";
    String BarcodeText = "Barcode UPCA";
    DateChange date=new DateChange();
    
     void LastDaysSoldAmountReport(String dateString,String FileName) {
         
        LastDaysAllProductWithSoldAmount l7bpsa = new LastDaysAllProductWithSoldAmount();
        l7bpsa.bestProducts(dateString);
// l7bpp.L7BestSaleProduct.clear();
// l7bpp.L7TBestSaleProductProfit.clear();

        //BarChartOfLast30Days b7 = new BarChartOfLast30Days();
        String empty = "----------------------------------------------------------------------------------------------------------------------------------";

        Document d = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(FileName));
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
            Paragraph pa = new Paragraph("Last 30 days Sales Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 30, Font.BOLD, BaseColor.GRAY));
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

            PdfPTable table2 = new PdfPTable(4);

            PdfPCell cell5 = new PdfPCell(new Paragraph("Best selling product(by sold Amount)", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
            cell5.setColspan(4);
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setBackgroundColor(BaseColor.GRAY);
            cell5.setPaddingBottom(10);
            table2.addCell(cell5);

            PdfPCell cellID = new PdfPCell(new Paragraph("Serial No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellID.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(cellID);

            PdfPCell cell6 = new PdfPCell(new Paragraph("Product", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(cell6);

            PdfPCell cell7 = new PdfPCell(new Paragraph("Amount", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(cell7);

            PdfPCell cell8 = new PdfPCell(new Paragraph("Unit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(cell8);

            for (int i = 0; i < l7bpsa.L7BestSaleProduct.size(); i++) {
                table2.addCell((i + 1) + "");
                table2.addCell(l7bpsa.L7BestSaleProduct.get(i));
                table2.addCell(l7bpsa.L7TBestSaleProductAmount.get(i) + "");
                table2.addCell(l7bpsa.L7TBestSaleProductUnit.get(i));
            }

            d.add(table2);

            d.close();

        } catch (Exception e) {

        }

    }
    
}
