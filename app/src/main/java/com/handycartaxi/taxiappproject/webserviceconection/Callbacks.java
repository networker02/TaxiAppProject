package com.handycartaxi.taxiappproject.webserviceconection;

/**
 * Created by Joan on 04-May-15.
 */
public class Callbacks {

    public static interface Action<Parameter>
    {
        void call(Parameter parameter);
    }

    public static interface Action2<Parameter1,Parameter2>
    {
        void call(Parameter1 parameter1, Parameter2 parameter2);
    }

}
