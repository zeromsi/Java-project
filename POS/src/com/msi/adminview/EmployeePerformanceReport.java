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
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import javax.swing.ImageIcon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author MSI
 */
public class EmployeePerformanceReport {

    String BarcodeText = "Barcode UPCA";
    String filename = null;
    int sa = 0;
    byte[] person_image = null;
    ImageIcon format = null;

    public void CreateBarChart(String startDate,String endDate,String ImageName) {
        EmployeePerformanceInfo e = new EmployeePerformanceInfo();
     
        e.info(startDate,endDate);
        e.mergeMarks(startDate,endDate);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    //  System.out.print(e.empName);
        for(int i=0;i<e.empName.size();i++){
             dataset.setValue(e.TotalMarksPerEmployee.get(i),"Sale",e.empName.get(i));
        }
        JFreeChart chart = ChartFactory.createBarChart("Bar Chart", "Employee", "Marks", dataset, PlotOrientation.VERTICAL, true, false, false);
        chart.setBackgroundPaint(Color.white);
        chart.getTitle().setPaint(Color.black);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.black);
        p.setOrientation(PlotOrientation.HORIZONTAL);
        p.setBackgroundPaint(Color.WHITE);
        //p.setForegroundAlpha(TOP_ALIGNMENT);

        ChartPanel fr = new ChartPanel(chart);
        fr.setVisible(true);
        fr.setSize(1000, e.empName.size()*20);
        
       // System.out.print(e.empName);

        try {
            final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
            final File f = new File(ImageName);
            ChartUtilities.saveChartAsPNG(f, chart, 1000, 280, info);
        } catch (Exception ex) {

        }

    }

    public void Reprot(String URL,String startDate,String endDate,String ImageName) {
CreateBarChart(startDate,endDate,ImageName);
        Document d = new Document();
        String empty = "----------------------------------------------------------------------------------------------------------------------------------";
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
            Paragraph pa = new Paragraph("Employee Performance Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 50, Font.BOLD, BaseColor.GRAY));
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

            
            Image barchart = Image.getInstance(ImageName);
            barchart.scaleAbsolute(520, 300);
            d.add(barchart);
            d.close();

        } catch (Exception ex) {

        }

    }
}
