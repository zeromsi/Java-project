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
public class Product {

    String PID, PName, Unit;
    double PPrice, PAmount, purchasePricePerUnit, TotalPurchasingPrice, totalSoldAmount, TotalSoldPrice, profit;
 byte[] product_image;
    Image im;

    public ImageIcon getFormat() {
        return format;
    }

    public void setFormat(ImageIcon format) {
        this.format = format;
    }
    ImageIcon format;

    public double getTotalPurchasingPrice() {
        return TotalPurchasingPrice;
    }

    public void setTotalPurchasingPrice(double TotalPurchasingPrice) {
        this.TotalPurchasingPrice = TotalPurchasingPrice;
    }

    public double getTotalSoldAmount() {
        return totalSoldAmount;
    }

    public void setTotalSoldAmount(double totalSoldAmount) {
        this.totalSoldAmount = totalSoldAmount;
    }

    public double getTotalSoldPrice() {
        return TotalSoldPrice;
    }

    public void setTotalSoldPrice(double TotalSoldPrice) {
        this.TotalSoldPrice = TotalSoldPrice;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public Image getIm() {
        return im;
    }

    public void setIm(Image im) {
        this.im = im;
    }
   
    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public double getPPrice() {
        return PPrice;
    }

    public void setPPrice(double PPrice) {
        this.PPrice = PPrice;
    }

    public double getPAmount() {
        return PAmount;
    }

    public void setPAmount(double PAmount) {
        this.PAmount = PAmount;
    }

    public double getPurchasePricePerUnit() {
        return purchasePricePerUnit;
    }

    public void setPurchasePricePerUnit(double purchasePricePerUnit) {
        this.purchasePricePerUnit = purchasePricePerUnit;
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public byte[] getProduct_image() {
        return product_image;
    }

    public void setProduct_image(byte[] product_image) {
        this.product_image = product_image;
    }
}
