package com.handycartaxi.taxiappproject.webserviceconection;

/**
 * Created by Joan on 04-May-15.
 */
public interface ICallback<T> {
    void callback(T parameter);
}
