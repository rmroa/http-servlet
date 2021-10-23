package com.rm.http.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {

    List<T> findAll();

    Optional<T> findById(K id);

    T save(T entity);

    void update(T entity);

    boolean delete();
}
