package DAO;

import java.util.ArrayList;


public interface ChiTietInterface<T> {
    public int insert(ArrayList<T> t);
    public int delete(int t);
    public int update(ArrayList<T> t, int pk);
    public ArrayList<T> selectAll(int t);
}
