package GUI.BaoBao.DAO;

import java.util.ArrayList;
import java.util.List;

import GUI.BaoBao.DTO.DetailEntryFormDTO;

public class DetailEntryFormDAO {
    private DataBase db = new DataBase();

    public void createDetailEntryForm(int maphieunhap, int soluongnhap, int dongianhap, int masp) {
        DetailEntryFormDTO detailEntryFormDTO = new DetailEntryFormDTO(maphieunhap, dongianhap, soluongnhap,
                masp);
        db.insertQuery("INSERT INTO detail_entry_form(maphieunhap,masp,dongianhap,soluongnhap) VALUES (?,?,?,?)",
                detailEntryFormDTO.getMaphieunhap(), detailEntryFormDTO.getMasp(), detailEntryFormDTO.getDongianhap(),
                detailEntryFormDTO.getSoluongnhap());
    }

    public ArrayList<DetailEntryFormDTO> getDetailEntryFormByID(int id) {
        List<Object[]> data = db.selectQuery("SELECT * FROM detail_entry_form WHERE maphieunhap = ?",
                id);

        ArrayList<DetailEntryFormDTO> detailEntryFormDTOs = new ArrayList<>();

        for (Object[] objects : data) {
            // System.out.println(Arrays.toString(objects)); DEBUG

            detailEntryFormDTOs.add(new DetailEntryFormDTO(Integer.parseInt(objects[0].toString()),
                    Integer.parseInt(objects[2].toString()), Integer.parseInt(objects[4].toString()),
                    Integer.parseInt(objects[1].toString())));
        }

        return detailEntryFormDTOs;
    }
}
