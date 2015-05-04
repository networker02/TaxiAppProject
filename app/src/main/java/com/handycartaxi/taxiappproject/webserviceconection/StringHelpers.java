package com.handycartaxi.taxiappproject.webserviceconection;

/**
 * Created by Joan on 04-May-15.
 */
public class StringHelpers {
    private StringHelpers(){}

    public static boolean isNullOrEmpty(String value)
    {
        if ( value == null || value.equals(""))
        {
            return true;
        }
        else
            return  false;
    }
}
