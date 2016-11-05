/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.adminview;

import com.itextpdf.text.BadElementException;
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
import com.msi.DAO.EmployeeDAO;
import com.msi.connection.Connection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.entity.StandardEntityCollection;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author MSI
 */
public class SpecificEmployeeReport {


    String BarcodeText = "Barcode UPCA";


    public void CreateReport(String id) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("com/msi/xml/AdminSpring.xml");
        EmployeeDAO empDAO = (EmployeeDAO) context.getBean("EmployeeDAO");
        Employee emp = new Employee();
        EmployeeCategory empCat = new EmployeeCategory();
        emp.setEID(id);
        empDAO.Report(emp,empCat);
        context.close();
        //  getValues(id);

        Document d = new Document();
        String empty = "----------------------------------------------------------------------------------------------------------------------------------";
        try {

            PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(".\\EmployeeReport\\report.pdf"));
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
            Paragraph pa = new Paragraph("Employee Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 50, Font.BOLD, BaseColor.GRAY));
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
            d.add(emp.getIm());
            d.add(new Paragraph("               Employee ID  :" + emp.getEID()));
            d.add(new Paragraph("               Name         :" + emp.getEName()));
            d.add(new Paragraph("               Age          :" + emp.getEAge()));
            d.add(new Paragraph("               Salary       :" + emp.getESalary()));
            d.add(new Paragraph("               Category     :" + empCat.getECName()));
            d.add(new Paragraph("               Join Date     :" + emp.getJoinDate()));

            d.close();

        } catch (Exception e) {

        }

    }

}
