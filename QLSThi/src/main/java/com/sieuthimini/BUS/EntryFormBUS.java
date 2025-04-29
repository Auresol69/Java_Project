package com.sieuthimini.BUS;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.sieuthimini.DAO.EntryFormDAO;
import com.sieuthimini.DTO.AccountDTO;
import com.sieuthimini.DTO.EntryFormDTO;
import com.sieuthimini.DTO.SupplierDTO;
import com.sieuthimini.ExtendClasses.MessageBox;
import com.toedter.calendar.JDateChooser;

public class EntryFormBUS {
    public EntryFormBUS() {

    }

    public int createEntryForm(SupplierDTO supplierDTO, AccountDTO accountDTO, int loinhuan) {
        if (supplierDTO == null || accountDTO == null) {
            System.out.println("Lỗi: Thiếu thông tin nhà cung cấp hoặc tài khoản.");
            return -1;
        }
        if (loinhuan == 0) {
            System.out.println("Lỗi: % Lợi nhuận không được để trống.");
            return -1;
        }

        if (supplierDTO.getMancc() == 0) {
            System.out.println("Lỗi: ID nhà cung cấp không được để trống.");
            return -1;
        }

        if (accountDTO.getMaaccount() == 0) {
            System.out.println("Lỗi: Tên người dùng không được để trống.");
            return -1;
        }

        return new EntryFormDAO().createEntryForm(supplierDTO, accountDTO, loinhuan);

    }

    public EntryFormDTO getEntryFormByID(int id) {
        if (id == -1)
            return null;
        Object[] data = new EntryFormDAO().getEntryFormByID(id);
        return new EntryFormDTO(Integer.parseInt(data[3].toString()),
                Integer.parseInt(data[2].toString()),
                Integer.parseInt(data[0].toString()),
                data[1].toString(), Float.parseFloat(data[4].toString()));
    }

    public List<EntryFormDTO> searchEntryForm(AccountDTO account, SupplierDTO supplier, JDateChooser fromDate,
            JDateChooser toDate) {

        if (fromDate.getDate() != null && toDate.getDate() != null)
            if (fromDate.getDate().after(toDate.getDate())) {
                MessageBox.showError("Thời gian bắt đầu không được lớn hơn thời gian kết thúc.");
                return new ArrayList<>();
            }

        return new EntryFormDAO().searchEntryForm(account, supplier, fromDate, toDate);
    }

    public void huyEntryForm(EntryFormDTO entryFormDTO) {
        if (entryFormDTO == null) {
            MessageBox.showError("Hãy chọn 1 phiếu nhập để hủy.");
            return;
        }
        if (MessageBox.showConfirmDialog("Bạn có chắc chắn muốn hủy phiếu nhập?",
                "Xác nhận hủy phiếu nhập") == JOptionPane.YES_OPTION) {
            boolean success = new EntryFormDAO().huyEntryForm(entryFormDTO);
            if (success) {
                MessageBox.showOkDialog("Hủy phiếu nhập thành công.", "Thành công");
            } else {
                MessageBox.showError("Không tìm thấy phiếu nhập để hủy.");
            }
        } else {
            MessageBox.showOkDialog("Đã cancel hủy phiếu nhập", "Thất bại");
        }
    }
}
