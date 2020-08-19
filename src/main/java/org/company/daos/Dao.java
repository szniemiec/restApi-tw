package org.company.daos;

import java.util.List;

public interface Dao<T> {

    public boolean create(T t);

    public T readById(int id);

    public boolean update(T t);

    public boolean delete(int id);

    public List<T> getAllElements();

}
