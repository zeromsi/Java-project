/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msi.adminview;

import com.itextpdf.text.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author MSI
 */
public class Employee {

    private String EID, EName, JoinDate, password, username;

    private int ESalary, EAge;
    byte[] EImage;

    public ImageIcon getFormat() {
        return format;
    }

    public void setFormat(ImageIcon format) {
        this.format = format;
    }

    public Image getIm() {
        return im;
    }

    public void setIm(Image im) {
        this.im = im;
    }
    ImageIcon format;
    Image im;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getEImage() {
        return EImage;
    }

    public void setEImage(byte[] EImage) {
        this.EImage = EImage;
    }

    public String getEID() {
        return EID;
    }

    public void setEID(String EID) {
        this.EID = EID;
    }

    public String getEName() {
        return EName;
    }

    public void setEName(String EName) {
        this.EName = EName;
    }

    public String getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(String JoinDate) {
        this.JoinDate = JoinDate;
    }

    public int getESalary() {
        return ESalary;
    }

    public void setESalary(int ESalary) {
        this.ESalary = ESalary;
    }

    public int getEAge() {
        return EAge;
    }

    public void setEAge(int EAge) {
        this.EAge = EAge;
    }
}
