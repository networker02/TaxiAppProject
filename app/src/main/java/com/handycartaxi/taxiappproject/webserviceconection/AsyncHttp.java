package com.handycartaxi.taxiappproject.webserviceconection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Joan on 04-May-15.
 */
public class AsyncHttp {

    public static void get(String url, HttpResponseCallback httpResponseCallback)  {
        get(url, null, httpResponseCallback);
    }

    public static void get(String url, DictionaryImp<String,String> params, HttpResponseCallback callback) {

        if(StringHelpers.isNullOrEmpty(url))
        {
            throw new NullPointerException("url");
        }

        if(params != null && params.isEmpty() == false)
        {
            url = Utilities.appendParametersToURL(url, params);
        }

        InternalAsyncHttpRequest internalAsyncHttpRequest = new InternalAsyncHttpRequest(url,callback);
        internalAsyncHttpRequest.setMethodType(0);
        internalAsyncHttpRequest.execute();
    }

    public static void getFileHttp(String url, Callbacks.Action<Bitmap> callbackBitmap){
        getFileHttp(url, null, callbackBitmap);
    }

    public static void getFileHttp(String url, DictionaryImp<String, String> params, Callbacks.Action<Bitmap> action){
        if (StringHelpers.isNullOrEmpty(url)){
            throw new NullPointerException("url cannot be null");
        }

        if(params != null && !params.isEmpty()) {
            url = Utilities.appendParametersToURL(url, params);
        }

        InternalAsyncDownloader downloader = new InternalAsyncDownloader(url,action);
        downloader.execute();
    }

    public static void post(String url){
        post(url,null,null);
    }

    public static void post(String url,HttpResponseCallback httpResponseCallback){
        post(url,null,httpResponseCallback);
    }

    public static void post(String url,DictionaryImp<String,String> params, HttpResponseCallback httpResponseCallback){
        if (StringHelpers.isNullOrEmpty(url)){
            throw new NullPointerException("url cannot be null");
        }

        if(params != null && !params.isEmpty()) {
            url = Utilities.appendParametersToURL(url, params);
        }

        InternalAsyncHttpRequest internalAsyncHttpRequest = new InternalAsyncHttpRequest(url, httpResponseCallback);
        internalAsyncHttpRequest.setMethodType(1);
        internalAsyncHttpRequest.execute();
    }



    private static class InternalAsyncHttpRequest extends AsyncTask<HttpParams,Integer,String>
    {

        public static final int HTTP_GET = 0, HTTP_POST = 1;

        private HttpUriRequest method;
        private String url;
        private HttpResponseCallback callback;
        private int type, statusCode = 200;

        public InternalAsyncHttpRequest(String url)
        {
            this.url = url;
        }

        public InternalAsyncHttpRequest(String url, HttpResponseCallback callback)
        {
            this.url = url;
            this.callback = callback;
        }

        public void setHttpRequest(HttpUriRequest method)
        {
            this.method = method;
        }

        public void setHttpResponseCallback(HttpResponseCallback onTextReceived)
        {
            this.callback = onTextReceived;
        }

        public InternalAsyncHttpRequest setMethodType(int type)
        {
            this.type = type;
            return this;
        }

        private HttpUriRequest createHttpMethod(int type)
        {

            if (this.method != null)
                return this.method;

            else if (type == 0)
                return new HttpGet(this.url);
            else if (type == 1)
                return new HttpPost(this.url);
            else
                return new HttpGet(this.url);

        }

        @Override
        protected String doInBackground(HttpParams... params) {

            HttpClient client = new DefaultHttpClient();
            HttpResponse response = null;
            try {
                response = client.execute(createHttpMethod(this.type));
                statusCode = response.getStatusLine().getStatusCode();
                String responseText = EntityUtils.toString(response.getEntity());

                return responseText;
            } catch (Exception e) {
                e.printStackTrace();

                if((statusCode = response.getStatusLine().getStatusCode()) != 200)
                {
                    return response.getStatusLine().getReasonPhrase();
                }
                else
                    return  e.getMessage();


            }

        }

        @Override
        protected void onPostExecute(String s) {
            if (this.callback != null)
            {
                if (this.statusCode == 200)
                    this.callback.call(s);
                else
                    this.callback.onFail(s, statusCode);
            }

        }
    }

    private static class InternalAsyncDownloader extends AsyncTask<Void,Integer,Bitmap>
    {
        private Callbacks.Action<Bitmap> onFileReceived;
        private String url;

        public InternalAsyncDownloader(String url, Callbacks.Action<Bitmap> onFileReceived)
        {
            this.url = url;
            if (onFileReceived == null)
            {
                throw new NullPointerException("callback has not been provided");
            }
            else if (StringHelpers.isNullOrEmpty(url))
            {
                throw new NullPointerException("url has not been provided");
            }

            this.onFileReceived = onFileReceived;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {

            try {
                URL urlObject = new URL(this.url);
                InputStream is = (InputStream)urlObject.getContent();
                Bitmap bitmap = BitmapFactory.decodeStream(is);

                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            this.onFileReceived.call(bitmap);
        }
    }

}
