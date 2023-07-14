/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ASSIGNMENT;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author Admin
 */
public class ASS_PACKAGE implements Serializable{

    private int maSp;
    private String tenSp;
    private int sizeSp;
    private BigDecimal soTien;
    private int chonthem;
    private int dogion;
    private int soLuong;
    private BigDecimal downGia;

    public ASS_PACKAGE() {
    }

    public ASS_PACKAGE(int maSp, String tenSp, int sizeSp, BigDecimal soTien, int chonthem, int dogion, int soLuong, BigDecimal downGia) {
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.sizeSp = sizeSp;
        this.soTien = soTien;
        this.chonthem = chonthem;
        this.dogion = dogion;
        this.soLuong = soLuong;
        this.downGia = downGia;
    }

    public int getDogion() {
        return dogion;
    }

    public void setDogion(int dogion) {
        this.dogion = dogion;
    }
    
    public int getChonthem() {
        return chonthem;
    }

    public void setChonthem(int chonthem) {
        this.chonthem = chonthem;
    }

    public int getSizeSp() {
        return sizeSp;
    }

    public void setSizeSp(int sizeSp) {
        this.sizeSp = sizeSp;
    }

    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    
    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }



    public BigDecimal getSoTien() {
        return soTien;
    }

    public void setSoTien(BigDecimal soTien) {
        this.soTien = soTien;
    }

    public BigDecimal getDownGia() {
        return downGia;
    }

    public void setDownGia(BigDecimal downGia) {
        this.downGia = downGia;
    }

    @Override
    public String toString() {
        return maSp + "," + tenSp + "," + sizeSp + "," + soTien + "," + soLuong + "," + downGia + "\n";
    }

    
}
