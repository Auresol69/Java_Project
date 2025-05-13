package BUS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import DAO.ThongKeDAO;
import DTO.ThongKeKhachHangDTO;
import DTO.ThongKeTheoThang;
import DTO.ThongKeTheoTungNgay;

public class ThongKeBUS {
    ThongKeDAO thongKeDAO = ThongKeDAO.getInstance();
    public ArrayList<ThongKeTheoTungNgay> getThongKeTheoTuNgayDenNgay (String start, String end){
        return thongKeDAO.getThongKeTheoTuNgayDenNgay(start , end);
    }
    public ArrayList<ThongKeTheoThang> getThongKeTheoThang (String start, String end){
        return thongKeDAO.getThongKeTheoThang(start,end);
    }
    public ArrayList<ThongKeKhachHangDTO> getAllKH (String start, String end){
        return thongKeDAO.getAllKH(start, end);
    }
    public Map< String,Integer> thongKeTongQuat(ArrayList<ThongKeKhachHangDTO> danhSachKhachHang) {
         Map<String, Integer> loiNhuanTheoKhachHang = new HashMap<>();
        // Duyệt qua danh sách khách hàng để tính tổng
        for (ThongKeKhachHangDTO kh : danhSachKhachHang) {
            String name = kh.getHoTen();
            int loiNhuan = kh.getLoiNhuan();
        if (name == null || name.equalsIgnoreCase("null") || name.trim().isEmpty()) continue;
        // Nếu khách hàng đã có trong map thì cộng thêm lợi nhuận
        if (loiNhuanTheoKhachHang.containsKey(name))  {
            loiNhuanTheoKhachHang.put(name, loiNhuanTheoKhachHang.get(name) + loiNhuan);
        } else {
            loiNhuanTheoKhachHang.put(name, loiNhuan);
        }
    }

    // Trả về Map chứa tổng lợi nhuận của mỗi khách hàng
    return loiNhuanTheoKhachHang;
    }
    public static void main(String[] args) {
        ThongKeBUS tkbus = new ThongKeBUS();
        Map< String,Integer> ar = tkbus.thongKeTongQuat(tkbus.getAllKH("2025-05-01", "2025-05-10"));
        for ( Map.Entry<String, Integer> t : ar.entrySet()){
            System.out.println(t.toString());
        }
        ArrayList<ThongKeKhachHangDTO> tk = tkbus.getAllKH("2025-05-01", "2025-05-10");
        for (ThongKeKhachHangDTO t : tk){
            System.out.println(t.toString());
        }
    }
}
