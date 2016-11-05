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
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author MSI
 */
public class ProductReportAgainstTime_Chapter_Month {

    String currency = "Taka";
    String BarcodeText = "Barcode UPCA";

    public void CreateReport(String PName, String startTime,String endTime, String URL, String PicName) {
        ProductInfoAgainstTime_Month p = new ProductInfoAgainstTime_Month();
        p.getAllProductMonthlyInfo(startTime,endTime);
        p.getProductMonthlyInfo(PName, startTime,endTime);
        p.getPieChart(PName, startTime,endTime, PicName);

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
            Paragraph pa = new Paragraph("Product Sales Against Time", FontFactory.getFont(FontFactory.TIMES_BOLD, 30, Font.BOLD, BaseColor.GRAY));
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

            PdfPTable table4 = new PdfPTable(3);

            PdfPCell cell13 = new PdfPCell(new Paragraph("Product Sales Against Time", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
            cell13.setColspan(4);
            cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell13.setBackgroundColor(BaseColor.GRAY);
            cell13.setPaddingBottom(10);
            table4.addCell(cell13);

            PdfPCell cell14 = new PdfPCell(new Paragraph("Sold Amount", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell14);

            PdfPCell cell15 = new PdfPCell(new Paragraph("Total Sale", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell15);

            String profit = p.profit + "";
            if (profit.startsWith("")) {
                PdfPCell cell16 = new PdfPCell(new Paragraph("Total Profit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
                cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
                table4.addCell(cell16);
            } else if (profit.startsWith("-")) {
                PdfPCell cell16 = new PdfPCell(new Paragraph("Total Loss", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
                cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
                table4.addCell(cell16);
            }

            double SoldPercentAmount = (p.SoldAmount * 100) / p.AllSoldAmount;
            DecimalFormat df = new DecimalFormat("#.##");
            SoldPercentAmount = Double.valueOf(df.format(SoldPercentAmount));
            double SaleMoneyPercent = (p.TotalSaleMoney * 100) / p.TotalSaleMoneyOfAllProduct;
            SaleMoneyPercent = Double.valueOf(df.format(SaleMoneyPercent));
            double profitPercent = (p.profit * 100) / p.AllProductProfit;
            profitPercent = Double.valueOf(df.format(profitPercent));

            table4.addCell(p.SoldAmount + "(" + SoldPercentAmount + "%) " + p.Unit);
            table4.addCell(p.TotalSaleMoney + "(" + SaleMoneyPercent + "%) " + currency);
            table4.addCell(p.profit + "(" + profitPercent + "%) " + currency);

            d.add(table4);

            d.add(new Paragraph("\n"));

            d.add(new Paragraph("\n"));
            Image barchart = Image.getInstance(PicName);
            barchart.scaleAbsolute(520, 300);
            d.add(barchart);
            d.close();

        } catch (Exception e) {
       //  JOptionPane.showMessageDialog(null,"This product was unsold during the selected month!");
        }

    }
}
