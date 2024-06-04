package com.scholarly.utils;

public interface DataMapper<T, U> {
    U map(T data);
}
