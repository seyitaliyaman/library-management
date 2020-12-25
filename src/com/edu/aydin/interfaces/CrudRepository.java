package com.edu.aydin.interfaces;

import java.util.List;

public interface CrudRepository<T> {
    void save(T t);

    void save(T t, String id);

    void update(String id, T t);

    void delete(String id);

    List<T> getAll();

    T getById(String id);
}
