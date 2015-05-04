package com.handycartaxi.taxiappproject.webserviceconection;

/**
 * Created by Joan on 04-May-15.
 */
public class Utilities {

    public static String combinePaths(String... paths)
    {
        String absolutePath = "";
        for(String path : paths)
        {
            if (absolutePath.endsWith("/") && path.startsWith("/"))
            {
                absolutePath += path.substring(0,path.length() - 1);
            }
            else if(!absolutePath.endsWith("/") && !path.startsWith("/") && !absolutePath.equals(""))
            {
                absolutePath += "/" + path;
            }
            else{
                absolutePath += path;
            }
        }

        return absolutePath;
    }


    public static String appendParametersToURL(String url, DictionaryImp<String, String> params)
    {
        if(!url.endsWith("?") && params != null && !params.isEmpty())
            url += "?";

        // if the url contains parameters
        // and the user pass more parameters, then append
        // the dictionary paramerers
        if(params != null && !params.isEmpty()) {
            /*if(!url.contains("&")) {
                url += "&";
            }*/

            for(PairKeyValue<String,String> element : params)
            {
                url += element.getKey() + "=" + element.getValue() + "&";
            }

            url = url.substring(0,url.length() - 1);
        }

        return url;
    }
}
