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
public class YearlyReport {

    String BarcodeText = "Barcode UPCA";
    String filename = null;
    int sa = 0;
    byte[] person_image = null;
    ImageIcon format = null;
    String currency = "Taka";

    public void pdfReport(String startYear,String endYear, String URL, String Days) {
        DateChange date = new DateChange();
        Sales sal = new Sales();

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
            Paragraph pa = new Paragraph(Days, FontFactory.getFont(FontFactory.TIMES_BOLD, 30, Font.BOLD, BaseColor.GRAY));
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
            PdfPCell cell = new PdfPCell(new Paragraph("Yearly Sales Report" + "\n", FontFactory.getFont(FontFactory.TIMES_BOLD, 24, Font.BOLD, BaseColor.WHITE)));
            cell.setColspan(4);
            cell.setPaddingBottom(15);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Month", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);

            PdfPCell cell2 = new PdfPCell(new Paragraph("Total", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell2);
            sal.getValuesOfTotalSaleByMonthOfYear(startYear,endYear);
           
                table.addCell("January");
                table.addCell("" + sal.jan + " " + currency);
                table.addCell("February");
                table.addCell("" + sal.feb + " " + currency);
                table.addCell("March");
                table.addCell("" + sal.mar + " " + currency);
                table.addCell("April");
                table.addCell("" + sal.apr + " " + currency);
                table.addCell("May");
                table.addCell("" + sal.may + " " + currency);
                table.addCell("June");
                table.addCell("" + sal.jun + " " + currency);
                table.addCell("July");
                table.addCell("" + sal.jul + " " + currency);
                table.addCell("August");
                table.addCell("" + sal.aug + " " + currency);
                table.addCell("September");
                table.addCell("" + sal.sep + " " + currency);
                table.addCell("October");
                table.addCell("" + sal.oct + " " + currency);
                table.addCell("November");
                table.addCell("" + sal.nov + " " + currency);
                table.addCell("December");
                table.addCell("" + sal.dec + " " + currency);
                

            double sum =sal.jan+sal.feb+sal.mar+sal.apr+sal.may+sal.jun+sal.jul+sal.aug+sal.sep+sal.oct+sal.nov+sal.dec;
          
              
            

            PdfPCell cell3 = new PdfPCell(new Paragraph("Total Sale", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.GRAY)));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell3);

            PdfPCell cell4 = new PdfPCell(new Paragraph(sum + " " + currency, FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.GRAY)));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell4);
            d.add(table);


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
            sal.getValOfSoldAmountOfYear(startYear, endYear);
            for (int i = 0; i < 15; i++) {
                table2.addCell((i + 1) + "");
                table2.addCell(sal.PName.get(i));
                table2.addCell(sal.SoldAmount.get(i) + "");
                table2.addCell(sal.Unit.get(i));
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
            sal.getValOfProductByTotalSoldPriceOfYear(startYear, endYear);
            for (int i = 0; i < 15; i++) {
                table3.addCell((i + 1) + "");
                table3.addCell(sal.PName.get(i));
                table3.addCell(sal.TotalPrice.get(i) + "");
                table3.addCell(sal.Unit.get(i));
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
            sal.getValOfProductsBySoldTimesOfYear(startYear, endYear);
            for (int i = 0; i < 15; i++) {
                table4.addCell((i + 1) + "");
                table4.addCell(sal.PName.get(i));
                table4.addCell(sal.Count.get(i) + "");

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
            sal.getValOfProductByProfitOfYear(startYear, endYear);
            for (int i = 0; i < 15; i++) {
                table5.addCell((i + 1) + "");
                table5.addCell(sal.PName.get(i));
                table5.addCell(sal.Profit.get(i) + "");

            }

            d.add(table5);
            d.add(new Paragraph("\n"));

            double Profit = 0;
            for (int i = 0; i < sal.Profit.size(); i++) {
                Profit = Profit + sal.Profit.get(i);
            }
            
            d.newPage();
            //Barcode
            d.add(new Paragraph(BarcodeText));
            d.add(codeEAN.createImageWithBarcode(CB, BaseColor.BLACK, BaseColor.BLACK));
            d.add(new Paragraph("\n"));

            
             PdfPTable tablel = new PdfPTable(3);

            PdfPCell celll = new PdfPCell(new Paragraph("Product(by Loss)", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.WHITE)));
            celll.setColspan(4);
            celll.setHorizontalAlignment(Element.ALIGN_CENTER);
            celll.setBackgroundColor(BaseColor.GRAY);
            celll.setPaddingBottom(10);
            tablel.addCell(celll);

            PdfPCell cells = new PdfPCell(new Paragraph("Serial No.", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cells.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablel.addCell(cells);

            PdfPCell cellp = new PdfPCell(new Paragraph("Product", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellp.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablel.addCell(cellp);

            PdfPCell cellPr = new PdfPCell(new Paragraph("Loss", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.BLACK)));
            cellPr.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablel.addCell(cellPr);
            ProductByLossOfYear pby =new ProductByLossOfYear();
            pby.getValOfProductByLossOfYear(startYear, endYear);
           // sal.getValOfProductByProfitOfYear(startYear, endYear);
            for (int i = 0; i < pby.PName.size(); i++) {
                tablel.addCell((i + 1) + "");
                tablel.addCell(pby.PName.get(i));
                tablel.addCell(-pby.Profit.get(i) + ""+currency);

            }
double loss=0;

  for (int i = 0; i < pby.Profit.size(); i++) {
                loss = loss - pby.Profit.get(i);
            }
 double ProfitOrLoss = (Profit - loss);

// System.out.print(Profit+"  "+loss);
            String Operator = ProfitOrLoss + "";
            
            
            d.add(tablel);
            d.add(new Paragraph("\n"));
            
            if (Operator.startsWith("-")) {
                Paragraph pTP = new Paragraph("Total Loss: " + (-ProfitOrLoss) + " " + currency, FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.GRAY));

            
                pTP.setAlignment(Element.ALIGN_CENTER);
                d.add(pTP);
            } else {
                Paragraph pTP = new Paragraph("Total Profit: " + ProfitOrLoss + " " + currency, FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.GRAY));
               
                pTP.setAlignment(Element.ALIGN_CENTER);
                d.add(pTP);
            }

            d.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }    
}
