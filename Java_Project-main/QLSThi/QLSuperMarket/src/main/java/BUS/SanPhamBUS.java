/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ProductTypeDAO;
import DAO.SanPhamDAO;
import DAO.SupplierDAO;
import DTO.SanPhamDTO;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class SanPhamBUS {
    private ArrayList<SanPhamDTO> dssp ;
    public SanPhamBUS()
    {
        
    }
    public void listSP()
    {
        SanPhamDAO spDAO = new SanPhamDAO();
        dssp = new ArrayList<>();
        dssp = spDAO.list();
    }
    public boolean addSP(SanPhamDTO sp) {
        // Kiểm tra trống
        if (sp.getMaSP().isEmpty() || sp.getTenSP().isEmpty() || sp.getMaLoai().isEmpty() ||
            sp.getMaNCC().isEmpty() || sp.getImg().isEmpty() || sp.getSoLuong() <= 0 || sp.getDonGia() <= 0) {
            JOptionPane.showMessageDialog(null, "Không được để trống ô nào và số lượng, đơn giá phải lớn hơn 0!");
            return false;
        }
    
        // Kiểm tra mã nhà cung cấp
        if (!checkMaNCC(sp.getMaNCC())) {
            JOptionPane.showMessageDialog(null, "Mã nhà cung cấp không tồn tại!");
            return false;
        }
    
        // Kiểm tra mã loại sản phẩm
        if (!checkMaLoai(sp.getMaLoai())) {
            JOptionPane.showMessageDialog(null, "Mã loại sản phẩm không tồn tại!");
            return false;
        }
    
        // Kiểm tra mã sản phẩm trùng
        if (checkMasp(sp.getMaSP())) {
            JOptionPane.showMessageDialog(null, "Mã sản phẩm đã tồn tại!");
            return false;
        }
    
        // Thêm vào danh sách và cơ sở dữ liệu
        SanPhamDAO spDAO = new SanPhamDAO();
        boolean isAddedToDB = spDAO.add(sp); // Thêm vào cơ sở dữ liệu
    
        if (isAddedToDB) {
            dssp.add(sp); // Chỉ thêm vào danh sách nếu thêm vào DB thành công
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm vào cơ sở dữ liệu thất bại!");
            return false;
        }
    }
    public void deleteSP(String idSP) {
        for (SanPhamDTO sp : dssp) {
            if (sp.getMaSP().equals(idSP)) {
                dssp.remove(sp);
                SanPhamDAO spDAO = new SanPhamDAO();
                spDAO.delete(idSP);
                System.out.println("Xóa sản phẩm thành công: " + idSP);
                return;
            }
        }
    }
    
    public boolean setSP(SanPhamDTO sp) {
        // Kiểm tra mã sản phẩm tồn tại
        if (!checkMasp(sp.getMaSP())) {
            JOptionPane.showMessageDialog(null, "Mã sản phẩm không tồn tại!");
            return false;
        }
    
        // Kiểm tra mã loại sản phẩm
        if (!checkMaLoai(sp.getMaLoai())) {
            JOptionPane.showMessageDialog(null, "Mã loại sản phẩm không tồn tại!");
            return false;
        }
    
        // Kiểm tra mã nhà cung cấp
        if (!checkMaNCC(sp.getMaNCC())) {
            JOptionPane.showMessageDialog(null, "Mã nhà cung cấp không tồn tại!");
            return false;
        }
    
        // Cập nhật danh sách và cơ sở dữ liệu
        for (int i = 0; i < dssp.size(); i++) {
            if (dssp.get(i).getMaSP().equals(sp.getMaSP())) {
                dssp.set(i, sp);
                SanPhamDAO spDAO = new SanPhamDAO();
                boolean isUpdated = spDAO.set(sp); // Sửa trong cơ sở dữ liệu
                return isUpdated; // Trả về kết quả sửa
            }
        }
        return false; // Trả về false nếu không tìm thấy sản phẩm
    }
    public SanPhamDTO getSP(String masp)
    {
        for(SanPhamDTO sp : dssp)
        {
            if(sp.getMaSP().equals(masp))
            {
                return sp;
            }
        }
        return null;
    }
    public boolean updateSL(String masp,int sl)
    {
        for(SanPhamDTO sp : dssp)
         {
             if(sp.getMaSP().equals(masp))
             {
                int old = sp.getSoLuong();
                if(sl > old)
                {
                    JOptionPane.showMessageDialog(null, "Không đủ hàng");
                    return false;
                }
                old -= sl;
                sp.setSoLuong(old);
                SanPhamDAO spDAO = new SanPhamDAO();
                spDAO.set(sp);
                System.out.println(sp.getSoLuong());
                return true;
             }
         }
         return false;
    }
    public boolean checkSL(String masp , int sl)
    {
        for(SanPhamDTO sp : dssp)
         {
             if(sp.getMaSP().equals(masp))
             {
                if(sl > sp.getSoLuong())
                {
                    JOptionPane.showMessageDialog(null, "Không đủ hàng");
                    return false;
                }
             }
         }
         return true;
    }
    public boolean checkMasp(String masp) {
        for (SanPhamDTO sp : dssp) {
            if (sp.getMaSP().equals(masp)) {
                return true; // Mã sản phẩm đã tồn tại
            }
        }
        return false; // Mã sản phẩm không tồn tại
    }
    public boolean checkMaLoai(String maloai) {
        ProductTypeDAO productTypeDAO = new ProductTypeDAO();
        return productTypeDAO.exists(maloai); // Kiểm tra mã loại trong bảng producttype
    }
    public boolean checkMaNCC(String mancc) {
        SupplierDAO supplierDAO = new SupplierDAO();
        return supplierDAO.exists(mancc); // Kiểm tra mã nhà cung cấp trong bảng supplier
    }
    public ArrayList<SanPhamDTO> searchSP(String masp,String maloai,String mansx,int max,int min)
    {
        ArrayList<SanPhamDTO> search = new ArrayList<>();
        masp = masp.isEmpty()?masp = "": masp;
        maloai = maloai.isEmpty()?maloai = "": maloai;
        mansx = mansx.isEmpty()?mansx = "": mansx;
        for(SanPhamDTO sp : dssp)
        {
            if( sp.getMaSP().contains(masp) && 
                sp.getMaLoai().contains(maloai) &&
                sp.getMaNCC().contains(mansx) &&
                sp.getDonGia() >= min && 
                sp.getDonGia() <= max)
            {
                search.add(sp);
            }
        }
        return search;
    }
    public ArrayList<SanPhamDTO> getList() {
        return dssp;
    }
    
    public void ExportExcelDatabase(){
        SanPhamDAO spDAO = new SanPhamDAO();
        spDAO.ExportExcelDatabase();
    }
    
    public void ImportExcelDatabase(File file){
        SanPhamDAO spDAO = new SanPhamDAO();
        spDAO.ImportExcelDatabase(file);
    }
    
}
