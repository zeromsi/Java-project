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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author MSI
 */
public class LastDaysGeneralReport {

    String BarcodeText = "Barcode UPCA";
    String filename = null;
    int sa = 0;
    byte[] person_image = null;
    ImageIcon format = null;
    String currency = "Taka";

    public void LastDayspdfReport(String dateString, String FileName, String days) {
        DateChange date = new DateChange();
        InsufficientAmountProducts ias = new InsufficientAmountProducts();
        LastDaysBestPoductsBySoldAmount l7bpsa = new LastDaysBestPoductsBySoldAmount();
        l7bpsa.bestProducts(dateString);
        LastDaysBestProductsByTotalPrice l7bptp = new LastDaysBestProductsByTotalPrice();
        l7bptp.getBestProducts(dateString);
        LastDaysBestProductsBySoldTimes l7bpst = new LastDaysBestProductsBySoldTimes();
        l7bpst.bestProducts(dateString);
        LastDaysBestProductsByProfit l7bpp = new LastDaysBestProductsByProfit();
        l7bpp.bestProducts(dateString);
        LastDaysLossyProducts tlp = new LastDaysLossyProducts();
        tlp.getInfo(dateString);
        //  ias.getInsufficientAmountProduct();
        BarChartOfLastDays b7 = new BarChartOfLastDays();
        b7.getBarChart1(dateString);
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
            Paragraph pa = new Paragraph(days, FontFactory.getFont(FontFactory.TIMES_BOLD, 30, Font.BOLD, BaseColor.GRAY));
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
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            PdfPTable table = new PdfPTable(2);
            PdfPCell cell = new PdfPCell(new Paragraph("Day by Day Sales Report" + "\n", FontFactory.getFont(FontFactory.TIMES_BOLD, 24, Font.BOLD, BaseColor.WHITE)));
            cell.setColspan(4);
            cell.setPaddingBottom(15);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Date", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);

            PdfPCell cell2 = new PdfPCell(new Paragraph("Total", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell2);
            for (int i = 0; i < b7.allDate.size(); i++) {
                table.addCell(b7.allDate.get(i));
                table.addCell("" + b7.totalPrice.get(i) + " " + currency);
            }

            double sum = 0;
            for (int i = 0; i < b7.totalPrice.size(); i++) {
                sum = sum + b7.totalPrice.get(i);
            }

            PdfPCell cell3 = new PdfPCell(new Paragraph("Total Sale", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.GRAY)));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell3);

            PdfPCell cell4 = new PdfPCell(new Paragraph(sum + " " + currency, FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.GRAY)));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell4);
            d.add(table);

            Paragraph BarChartP = new Paragraph("Sales report in Bar Chart:", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.GRAY));
            BarChartP.setAlignment(Element.ALIGN_CENTER);
            d.add(BarChartP);

            d.add(new Paragraph("\n"));
            Image barchart = Image.getInstance("barchartdaysfull1.png");
            barchart.scaleAbsolute(520, 300);
            d.add(barchart);

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

            for (int i = 0; i < 15; i++) {
                table2.addCell((i + 1) + "");
                table2.addCell(l7bpsa.L7BestSaleProduct.get(i));
                table2.addCell(l7bpsa.L7TBestSaleProductAmount.get(i) + "");
                table2.addCell(l7bpsa.L7TBestSaleProductUnit.get(i));
            }

            d.add(table2);
            d.add(new Paragraph("\n"));
            PdfPTable table3 = new PdfPTable(4);

            PdfPCell cell9 = new PdfPCell(new Paragraph("Best selling product(by Total Price)", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
            cell9.setColspan(4);
            cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell9.setBackgroundColor(BaseColor.GRAY);
            cell9.setPaddingBottom(10);
            table3.addCell(cell9);

            PdfPCell cellID2 = new PdfPCell(new Paragraph("Serial No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellID2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell(cellID2);

            PdfPCell cell10 = new PdfPCell(new Paragraph("Product", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell(cell10);

            PdfPCell cell11 = new PdfPCell(new Paragraph("Total Price", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell(cell11);

            PdfPCell cell12 = new PdfPCell(new Paragraph("Unit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell(cell12);

            for (int i = 0; i < 15; i++) {
                table3.addCell((i + 1) + "");
                table3.addCell(l7bptp.L7BestSaleProduct.get(i));
                table3.addCell(l7bptp.L7TBestSaleProductTotalPrice.get(i) + "");
                table3.addCell(l7bptp.L7TBestSaleProductUnit.get(i));
            }

            d.add(table3);
            d.newPage();
            //Barcode
            d.add(new Paragraph(BarcodeText));
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
            d.add(new Paragraph("\n"));

            PdfPTable table4 = new PdfPTable(3);

            PdfPCell cell13 = new PdfPCell(new Paragraph("Best selling product(by Total sold Times)", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
            cell13.setColspan(4);
            cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell13.setBackgroundColor(BaseColor.GRAY);
            cell13.setPaddingBottom(10);
            table4.addCell(cell13);

            PdfPCell cellID3 = new PdfPCell(new Paragraph("Serial No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellID3.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cellID3);

            PdfPCell cell14 = new PdfPCell(new Paragraph("Product", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell14);

            PdfPCell cell15 = new PdfPCell(new Paragraph("Sold Times", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell15);

            for (int i = 0; i < 15; i++) {
                table4.addCell((i + 1) + "");
                table4.addCell(l7bpst.L7BestSaleProduct.get(i));
                table4.addCell(l7bpst.L7TBestSaleProductSoldTimes.get(i) + "");

            }

            d.add(table4);

            d.add(new Paragraph("\n"));

            PdfPTable table5 = new PdfPTable(3);

            PdfPCell cell16 = new PdfPCell(new Paragraph("Best selling product(by Profit)", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
            cell16.setColspan(4);
            cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell16.setBackgroundColor(BaseColor.GRAY);
            cell16.setPaddingBottom(10);
            table5.addCell(cell16);

            PdfPCell cellID4 = new PdfPCell(new Paragraph("Serial No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellID4.setHorizontalAlignment(Element.ALIGN_CENTER);
            table5.addCell(cellID4);

            PdfPCell cell17 = new PdfPCell(new Paragraph("Product", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
            table5.addCell(cell17);

            PdfPCell cell18 = new PdfPCell(new Paragraph("Profit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
            table5.addCell(cell18);

            for (int i = 0; i < 15; i++) {
                table5.addCell((i + 1) + "");
                table5.addCell(l7bpp.L7BestSaleProduct.get(i));
                table5.addCell(l7bpp.L7TBestSaleProductProfit.get(i) + "");

            }

            d.add(table5);
            d.add(new Paragraph("\n"));
   d.newPage();
            //Barcode
            d.add(new Paragraph(BarcodeText));
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
            d.add(new Paragraph("\n"));
            PdfPTable tablel = new PdfPTable(4);

            PdfPCell cell1l = new PdfPCell(new Paragraph("Product By Loss", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
            cell1l.setColspan(5);
            cell1l.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1l.setBackgroundColor(BaseColor.GRAY);
            cell1l.setPaddingBottom(10);
            tablel.addCell(cell1l);

            PdfPCell cellIDl = new PdfPCell(new Paragraph("Product", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellIDl.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablel.addCell(cellIDl);

            PdfPCell celll = new PdfPCell(new Paragraph("Sold Amount", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            celll.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablel.addCell(celll);

            PdfPCell celll2 = new PdfPCell(new Paragraph("Loss", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            celll2.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablel.addCell(celll2);

            PdfPCell celll3 = new PdfPCell(new Paragraph("Loss Per Unit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            celll3.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablel.addCell(celll3);

            for (int i = 0; i < tlp.ProductNameArrayList.size(); i++) {

                tablel.addCell(tlp.ProductNameArrayList.get(i));
                tablel.addCell(tlp.ProductAmountArraList.get(i) + " " + tlp.Unit.get(i));
                tablel.addCell(tlp.LossArraList.get(i) + " " + currency);

                tablel.addCell(tlp.LossPerProductArraList.get(i) + " " + currency);

            }

            d.add(tablel);

            double TodayProfit = 0;
            for (int i = 0; i < l7bpp.L7TBestSaleProductProfit.size(); i++) {
                TodayProfit = TodayProfit + l7bpp.L7TBestSaleProductProfit.get(i);
            }

            double TotalLoss = 0;
            for (int i = 0; i < tlp.LossArraList.size(); i++) {
                TotalLoss = TotalLoss - tlp.LossArraList.get(i);
            }

            String ProfitOrLossString;
            double ProfitOrLoss = (TodayProfit + TotalLoss);

            String Operator = ProfitOrLoss + "";

            if (Operator.startsWith("-")) {
                Paragraph pTP = new Paragraph("Total Loss: " + (-ProfitOrLoss) + " " + currency, FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.GRAY));

                ProfitOrLossString = "------------------------Total Loss: " + (-ProfitOrLoss) + " " + currency;
                pTP.setAlignment(Element.ALIGN_CENTER);
                d.add(pTP);
            } else {
                Paragraph pTP = new Paragraph("Total Profit: " + ProfitOrLoss + " " + currency, FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.GRAY));
                ProfitOrLossString = "------------------------Total Profit was: " + (ProfitOrLoss) + " " + currency;
                pTP.setAlignment(Element.ALIGN_CENTER);
                d.add(pTP);
            }

            d.newPage();
            //Barcode
            d.add(new Paragraph(BarcodeText));
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
            d.add(new Paragraph("\n"));

            PdfPTable table6 = new PdfPTable(4);
            PdfPCell cell19 = new PdfPCell(new Paragraph("Product with Insufficient Amount", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
            cell19.setColspan(4);
            cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell19.setBackgroundColor(BaseColor.GRAY);
            cell19.setPaddingBottom(10);
            table6.addCell(cell19);

            PdfPCell cellID5 = new PdfPCell(new Paragraph("Serial No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellID5.setHorizontalAlignment(Element.ALIGN_CENTER);
            table6.addCell(cellID5);

            PdfPCell cell20 = new PdfPCell(new Paragraph("Product", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell20.setHorizontalAlignment(Element.ALIGN_CENTER);
            table6.addCell(cell20);

            PdfPCell cell21 = new PdfPCell(new Paragraph("Existing Amount", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
            table6.addCell(cell21);

            PdfPCell cell22 = new PdfPCell(new Paragraph("Unit", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
            table6.addCell(cell22);

            for (int i = 0; i < ias.insufficientProductNameArrayList.size(); i++) {
                table6.addCell((i + 1) + "");
                table6.addCell(ias.insufficientProductNameArrayList.get(i));
                table6.addCell(ias.InsufficientProductAmountArraList.get(i) + "");
                table6.addCell(ias.insufficientProductUnitArrayList.get(i) + "");

            }

            d.add(table6);

            d.newPage();
//Barcode
            d.add(new Paragraph(BarcodeText));
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
            d.add(new Paragraph("\n"));

            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));
            d.add(new Paragraph("\n"));

            d.add(new Paragraph("Short Description:", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.GRAY)));
            d.add(new Paragraph(empty));

            d.add(new Paragraph("Top 5 sold products(by number of times) were:" + l7bpst.L7BestSaleProduct.get(0) + "," + l7bpst.L7BestSaleProduct.get(1) + "," + l7bpst.L7BestSaleProduct.get(2) + "," + l7bpst.L7BestSaleProduct.get(3) + "," + l7bpst.L7BestSaleProduct.get(4)));
            d.add(new Paragraph("------------------------More people need these products."));
            d.add(new Paragraph("Top 5 sold products(by sold amount) were:" + l7bpsa.L7BestSaleProduct.get(0) + "," + l7bpsa.L7BestSaleProduct.get(1) + "," + l7bpsa.L7BestSaleProduct.get(2) + "," + l7bpsa.L7BestSaleProduct.get(3) + "," + l7bpsa.L7BestSaleProduct.get(4)));
            d.add(new Paragraph("------------------------You need to keep more amount of  these products."));

            d.add(new Paragraph("Top 5 profitable products were:" + l7bpp.L7BestSaleProduct.get(0) + "," + l7bpp.L7BestSaleProduct.get(1) + "," + l7bpp.L7BestSaleProduct.get(2) + "," + l7bpp.L7BestSaleProduct.get(3) + "," + l7bpp.L7BestSaleProduct.get(4)));
            d.add(new Paragraph("------------------------These items helps you to make more profit."));
            d.add(new Paragraph(ProfitOrLossString));
            d.add(new Paragraph("---------------------------------------------Best of luck for Next Days----------------------------------------------"));
            d.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

}
