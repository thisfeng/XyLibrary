package com.xycode.xylibrary.unit;

/**
 * Created by XY on 2016-09-02.
 */
public class StringData<T> {

    String string;
    T object;

    public StringData(String string, T object) {
        this.string = string;
        this.object = object;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
