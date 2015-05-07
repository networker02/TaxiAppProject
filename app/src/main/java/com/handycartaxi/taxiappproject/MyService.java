package com.handycartaxi.taxiappproject;

/**
 * Created by Joan on 04-May-15.
 */
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.handycartaxi.taxiappproject.webserviceconection.AsyncHttp;
import com.handycartaxi.taxiappproject.webserviceconection.DictionaryImp;
import com.handycartaxi.taxiappproject.webserviceconection.HttpResponseCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class MyService extends Service {

    Global g = new Global();
    Context p = this;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "El Servicio Se Ha Creado", Toast.LENGTH_LONG).show();







  /*      new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                stopSelf();
                Log.d("Destruido!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!","!!!!!!!!!!!!!!!!!!!!!!1");//Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
                Toast.makeText(p,"Servicio Destruido", Toast.LENGTH_LONG).show();
            }

        }, 100000); //<===== Aqui debemos especificar el tiempo */
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // For time consuming an long tasks you can launch a new thread here...
        Toast.makeText(this, " Servicio Iniciado", Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {








        DictionaryImp<String,String> dictionaryImp = new DictionaryImp();
        dictionaryImp.put("Pedido",""+Global.PEDIDO );

        AsyncHttp.get("http://" + Global.IP + "/taxwebapp/Taxi/getTaxiData", dictionaryImp, new HttpResponseCallback() {

            @Override
            public void onFail(String errorMessage, int StatusCode) {
                System.out.println("ENTRO AL FAIL");
            }


            @Override
            public void call(String s) {
                System.out.println("ENTRO AL CALLLLLLL");
                System.out.println(s);
                try {
                    System.out.println("SYSOOOOOOOOOO " + s);
                    JSONObject jsonObject = new JSONObject(s);
                    JSONObject jsonItem = jsonObject.getJSONObject("pedidoC");
                    g.setID_ASIGNADO(jsonItem.getInt("IdAsignado"));
                    g.setTAXI_NAME(jsonItem.getString("TaxiName"));
                    g.setUNIDAD(jsonItem.getInt("Unidad"));
                    g.setCOLOR_VEHICULO(jsonItem.getString("ColorAto"));
                    g.setID_FOTO_TAXISTA(jsonItem.getInt("Foto"));
                    g.setTIEMPO(jsonItem.getInt("Tiempo"));

                    System.out.println("ID ASIGNADO ="+Global.ID_ASIGNADO);
//                    if(Global.ID_ASIGNADO!=0){
//                        stopSelf();
//                    }
                                          /*  Intent i = new Intent(getApplicationContext(), TaxiDetailsActivity.class);
                                            startActivity(i);
                                            overridePendingTransition(R.anim.animation1, R.anim.animation2);
                                            finish(); */

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

            }

        }, 15000);//LA OPERADORA TIENE 15 SEGUNDOS PARA ASIGNAAR UN TAXISTA


    }

    @Override
    public void onDestroy() {

        stopSelf();
        Toast.makeText(p,"Servicio Destruido", Toast.LENGTH_LONG).show();
        Log.d("Destruido!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!","!!!!!!!!!!!!!!!!!!!!!!1");//Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();





    }
}