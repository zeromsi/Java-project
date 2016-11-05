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
public class MonthlyBudgetReport {

    String currency = "Taka";
    String BarcodeText = "Barcode UPCA";

    public void getReport(String URL) {
        DecimalFormat df = new DecimalFormat("#.##");
        MonthlyBudgetingInfo m = new MonthlyBudgetingInfo();
        m.getExistingAmount();
        m.getValueOfMonth_PreviousYear();
        m.getValOfMonth_PreviousThree();
        //  m.getValueOfMonth_PreviousYear();
        //  m.getValOfMonth_PreviousThree();
        //  m.getAllValue();

        String temp = null;
        String tempUnit = null;
        double Temp = 0;
        double TempnewPurchasepricet = 0;
        /// System.out.print(m.PNameOfExistingProduct+" "+m.ExistingAmount+" "+m.AmountOfThreeMonth+" "+m.unit);
        for (int j = 0; j < m.ExistingAmount.size(); j++) {
            if (m.ExistingAmount.get(j) < m.AmountOfThreeMonth.get(j)) {
                temp = m.PNameOfExistingProduct.get(j);
                Temp = m.AmountOfThreeMonth.get(j) - m.ExistingAmount.get(j);
                tempUnit = m.unitExistingProduct.get(j);
                TempnewPurchasepricet = m.ProductPurchasePrice.get(j);

                m.newName.add(temp);
                m.newAmount.add(Temp);
                m.unit.add(tempUnit);
                m.newPurchasepricet.add(TempnewPurchasepricet);
            }

        }
        m.getExistingAmountConsideringPreviousYear();
//        System.out.print(m.ExistingAmount);
//        System.out.print(m.newName);
//        System.out.print(m.newAmount);
//        System.out.print(m.unit);
//        System.out.print(m.newPurchasepricet);
//        

        m.getExistingAmountConsideringPreviousYear();

//        System.out.print(m.PNameOfPreviousYearSoldProduct + " " + m.PSoldAmountOfPreviousYearSoldProduct + " " + m.PunitOfPreviousYearSoldProduct + " " + m.PExistingAmountOfPreviousYearSoldProduct + " " + m.PPurchasingPriceOfPreviousYearSoldProduct);
//
//        
        String temp2 = null;
        String tempUnit2 = null;
        double Temp2 = 0;
        double TempnewPurchasepricet2 = 0;
        for (int j = 0; j < m.PExistingAmountOfPreviousYearSoldProduct.size(); j++) {
            if (m.PExistingAmountOfPreviousYearSoldProduct.get(j) < m.PSoldAmountOfPreviousYearSoldProduct.get(j)) {
                temp2 = m.PNameOfPreviousYearSoldProduct.get(j);
                Temp2 = m.PSoldAmountOfPreviousYearSoldProduct.get(j) - m.PExistingAmountOfPreviousYearSoldProduct.get(j);
             

                tempUnit2 = m.PunitOfPreviousYearSoldProduct.get(j);
                TempnewPurchasepricet2 = m.PPurchasingPriceOfPreviousYearSoldProduct.get(j);

                m.newName2.add(temp2);
                m.newAmount2.add(Temp2);
                m.unit2.add(tempUnit2);
                m.newPurchasepricet2.add(TempnewPurchasepricet2);
            }

        }

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
            Paragraph pa = new Paragraph("Monthly Budget Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 30, Font.BOLD, BaseColor.GRAY));
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

            PdfPTable table4 = new PdfPTable(6);

            PdfPCell cell13 = new PdfPCell(new Paragraph("Monthly Budget", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
            cell13.setColspan(7);
            cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell13.setBackgroundColor(BaseColor.GRAY);
            cell13.setPaddingBottom(10);
            table4.addCell(cell13);

            PdfPCell cellID3 = new PdfPCell(new Paragraph("Serial No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellID3.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cellID3);

            PdfPCell cell14 = new PdfPCell(new Paragraph("Product Name", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell14);

            PdfPCell cell15 = new PdfPCell(new Paragraph("Needed Amount", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell15);

            PdfPCell cell16 = new PdfPCell(new Paragraph("Unit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell16);

            PdfPCell cell18 = new PdfPCell(new Paragraph("Purchase price per unit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell18);

            PdfPCell cell17 = new PdfPCell(new Paragraph("Total price", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell17);
            double tempVal = 0;
            double profit1 = 0;
            for (int i = 0; i < m.newName.size(); i++) {
                double a=Math.ceil(m.newAmount.get(i));
              //  tempVal = m.newPurchasepricet.get(i) * m.newAmount.get(i);
                 tempVal = m.newPurchasepricet.get(i) *a;
                table4.addCell((i + 1) + "");
                table4.addCell(m.newName.get(i));
                table4.addCell(a +" ");
               // table4.addCell(m.newAmount.get(i) +" ");
                //table4.addCell(Double.valueOf(df.format(m.newAmount.get(i))) +" ");
                table4.addCell(m.unit.get(i));
                table4.addCell(m.newPurchasepricet.get(i) + " " + currency);
               // table4.addCell(Double.valueOf(df.format(tempVal)) + " " + currency);
                 table4.addCell(tempVal + " " + currency);
                profit1 = profit1 + tempVal;
            }

            double tempVal2 = 0;
            double profit2 = 0;
            for (int i = 0; i < m.newName2.size(); i++) {
                tempVal2 = m.newPurchasepricet2.get(i) * m.newAmount2.get(i);
                table4.addCell((i + 1) + "");
                table4.addCell(m.newName2.get(i));
                table4.addCell(Double.valueOf(df.format(m.newAmount2.get(i))) + " " + currency);
                table4.addCell(m.unit2.get(i));
                table4.addCell(m.newPurchasepricet2.get(i) + "");
                table4.addCell(Double.valueOf(df.format(tempVal2)) + " " + currency);
                profit2 = profit2 + tempVal2;
            }

            d.add(table4);

            double Profit = profit1 + profit2;

            Paragraph pTP = new Paragraph("Total: " + Double.valueOf(df.format(Profit)) + " " + currency, FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.GRAY));
            pTP.setAlignment(Element.ALIGN_CENTER);
            d.add(pTP);

            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("Other's Items are avaiable on the stock."));
            
            d.add(new Paragraph("\n"));

            d.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

    }
}
