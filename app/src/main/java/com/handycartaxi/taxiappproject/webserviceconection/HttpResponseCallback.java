package com.handycartaxi.taxiappproject.webserviceconection;

/**
 * Created by Joan on 04-May-15.
 */
public interface HttpResponseCallback extends Callbacks.Action<String> {
    void onFail(String errorMessage, int StatusCode);
}
