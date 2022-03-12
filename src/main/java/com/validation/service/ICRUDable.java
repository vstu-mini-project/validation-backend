package com.validation.service;

public interface ICRUDable<T> {
     void create(T object);
     void read(T object);
     void update(T object);
     void delete(T object);
}
