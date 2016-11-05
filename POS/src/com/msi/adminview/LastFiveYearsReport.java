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
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author MSI
 */
public class LastFiveYearsReport {

    public LastFiveYearsReport() {
    }

    public void createBarChart() {
        LastFiveYearsInfo lf=new LastFiveYearsInfo();
        lf.getAllVaues();
        
        String series1 = "Total Sale";
        String series2 = "Profit";
        String series3 = "";

        // column keys...
        String category1 = lf.Year.get(0).toString();
        String category2 = lf.Year.get(1).toString();
        String category3 = lf.Year.get(2).toString();
        String category4 = lf.Year.get(3).toString();
        String category5 = lf.Year.get(4).toString();
        String category6 = "";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(lf.TotalPrice.get(0), series1, category1);
        dataset.addValue(lf.Profit.get(0), series2, category1);
        dataset.addValue(0, "", category1);
        
        dataset.addValue(lf.TotalPrice.get(1), series1, category2);
        dataset.addValue(lf.Profit.get(1), series2, category2);

        
        dataset.addValue(lf.TotalPrice.get(2), series1, category3);
        dataset.addValue(lf.Profit.get(2), series2, category3);

        
        dataset.addValue(lf.TotalPrice.get(3), series1, category4);
        dataset.addValue(lf.Profit.get(3), series2, category4);

        
        dataset.addValue(lf.TotalPrice.get(4), series1, category5);
        dataset.addValue(lf.Profit.get(4), series2, category5);

        JFreeChart chart = ChartFactory.createBarChart("Bar Chart", "Date", "Money", dataset, PlotOrientation.VERTICAL, true, false, false);
        chart.setBackgroundPaint(Color.white);
        chart.getTitle().setPaint(Color.blue);
        CategoryPlot p = chart.getCategoryPlot();
         p.setBackgroundPaint(Color.WHITE);
           p.setRangeGridlinePaint(Color.MAGENTA);

        //p.setForegroundAlpha(TOP_ALIGNMENT);
        ChartPanel fr = new ChartPanel(chart);
        fr.setVisible(true);
        fr.setSize(1000, 480);
        try {
            final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
            final File f = new File("barchartFiveYears.png");
            ChartUtilities.saveChartAsPNG(f, chart, 1000, 280, info);
        } catch (Exception e) {

        }
    }
    
    
    public void createReport(String URL){
        String BarcodeText = "Barcode UPCA";
    String filename = null;
    int sa = 0;
    byte[] person_image = null;
    ImageIcon format = null;
    String currency = "Taka";
        createBarChart();
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
            Paragraph pa = new Paragraph("Last Five years Sales Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 30, Font.BOLD, BaseColor.GRAY));
            pa.setAlignment(Element.ALIGN_CENTER);

            d.add(pa);
            d.add(new Paragraph("\n"));
            
            Image barchart = Image.getInstance("barchartFiveYears.png");
            barchart.scaleAbsolute(520, 300);
            d.add(barchart);
            
            
            d.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

    }

//    public static void main(String[] args) {
//        LastFiveYearsReport l = new LastFiveYearsReport();
//        l.createBarChart();
//        
//        LastFiveYearsInfo lf=new LastFiveYearsInfo();
//        lf.getAllVaues();
//        System.out.print(lf.Year+" "+lf.TotalPrice);
//    }
}
