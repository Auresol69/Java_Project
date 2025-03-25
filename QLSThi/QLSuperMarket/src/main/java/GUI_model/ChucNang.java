/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package GUI_model;
import javax.swing.JLabel;

public class ChucNang {
    private int maChucNang;
    private String tenChucNang;
    private String icon;
    public int getMaChucNang() {
        return maChucNang;
    }
    
    public void setMaChucNang(int maChucNang) {
        this.maChucNang = maChucNang;
    }

    public String getTenChucNang() {
        return tenChucNang;
    }

    public void setTenChucNang(String tenChucNang) {
        this.tenChucNang = tenChucNang;
    }

    public String geticon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public ChucNang(int maChucNang,String tenChucNang,String icon){
        this.maChucNang = maChucNang;
        this.tenChucNang = tenChucNang;
        this.icon = icon;
    }
}
