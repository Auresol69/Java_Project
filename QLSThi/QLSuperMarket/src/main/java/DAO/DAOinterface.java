package DAO;

import java.util.ArrayList;

public interface DAOinterface<T> {
    public int insert(T t);

    public int delete(int t);

    public int update(T t);

    public ArrayList<T> selectAll();

    public T selectedByID(int t);

    int getAutoIncrement();

}
