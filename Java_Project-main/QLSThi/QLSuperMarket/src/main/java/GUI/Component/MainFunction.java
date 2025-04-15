package GUI.Component;

import BUS.NhomQuyenBUS;
import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

public final class MainFunction extends JToolBar {

    public ButtonToolBar btnAdd, btnDelete, btnEdit, btnDetail, btnNhapExcel, btnXuatExcel, btnHuyPhieu;
    public JSeparator separator1;
    public HashMap<String, ButtonToolBar> btn = new HashMap<>();
    private final NhomQuyenBUS nhomquyenBus = new NhomQuyenBUS();

    public MainFunction(int manhomquyen, String chucnang, String[] listBtn) {
        initData();
        initComponent(manhomquyen, chucnang, listBtn);
    }

    public void initData() {
        /*
         * Permission code:
         * 1 - THÊM (create/import)
         * 2 - XÓA (delete/cancel)
         * 3 - SỬA (update)
         * 4 - XEM (detail/export/phone)
         */

        btn.put("create", new ButtonToolBar("THÊM", "add.svg", 1));
        btn.put("import", new ButtonToolBar("NHẬP EXCEL", "import_excel.svg", 1));

        btn.put("delete", new ButtonToolBar("XÓA", "delete.svg", 2));
        btn.put("cancel", new ButtonToolBar("HUỶ PHIẾU", "cancel.svg", 2));

        btn.put("update", new ButtonToolBar("SỬA", "edit.svg", 3));

        btn.put("detail", new ButtonToolBar("CHI TIẾT", "detail.svg", 4));
        btn.put("export", new ButtonToolBar("XUẤT EXCEL", "export_excel.svg", 4));
        btn.put("phone", new ButtonToolBar("XEM DS", "phone.svg", 4));
    }

    private void initComponent(int manhomquyen, String chucnang, String[] listBtn) {
        this.setBackground(Color.WHITE);
        this.setRollover(true);
        initData();
        for (String btnKey : listBtn) {
            ButtonToolBar button = btn.get(btnKey);
            this.add(button);
            if (!nhomquyenBus.checkPermission(manhomquyen, button.getPermission())) {
                button.setEnabled(false);
            }
        }
    }
}
