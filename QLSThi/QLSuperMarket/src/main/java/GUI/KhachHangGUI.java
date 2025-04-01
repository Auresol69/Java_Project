package GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class KhachHangGUI extends JPanel{
     private JTable tbl;
    private JTextField txtMaKH,txtHoKH,txtTenKH,txtDiaChi,txtSDT;
    private JTextField sortMaKH,sortHoKH,sortTenKH;
    private DefaultTableModel model;
    private int DEFALUT_WIDTH;
    private boolean EditOrAdd = true;//Cờ cho button Cofirm True:ADD || False:Edit
    public KhachHangGUI(int width)
    {
        DEFALUT_WIDTH = width;
        init();
    }
    public void init(){
        
    }
}
