package com.handycartaxi.taxiappproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.handycartaxi.taxiappproject.R;
import com.handycartaxi.taxiappproject.webserviceconection.AsyncHttp;
import com.handycartaxi.taxiappproject.webserviceconection.DictionaryImp;
import com.handycartaxi.taxiappproject.webserviceconection.HttpResponseCallback;

public class EnServicioActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActionBar().hide();
        setContentView(R.layout.activity_en_servicio);



        Button finServicioButton = (Button) findViewById(R.id.finServicioBtn);
        Button panicButton = (Button) findViewById(R.id.panicButton1);











        finServicioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(i);
                finishAffinity();
            //PONER WEBSERVICE PARA QUE EL TAXISTA ACTUALICE SU UBICACION Y LE ASIGNEN OTRO SERVICIO

//CONSEGUIR LOS DATOS COMENTADOS
                DictionaryImp<String,String> dictionaryImp = new DictionaryImp();
                //            dictionaryImp.put("TaxiId",taxiId );
                //            dictionaryImp.put("Longitud",longitud);
                //            dictionaryImp.put("Latitud",latitud);
                AsyncHttp.get("http://"+Global.IP+"/taxwebapp/Taxi/UpdateLocation",dictionaryImp,new HttpResponseCallback() {
                    @Override
                    public void onFail(String errorMessage, int StatusCode) {

                    }

                    @Override
                    public void call(String s) {


                    }
                });



            }
        });


        panicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//COMENTADO PORQUE HAY QUE PONER ID PEDIDO

                DictionaryImp<String,String> dictionaryImp = new DictionaryImp();
//                dictionaryImp.put("TaxiID",username );
                AsyncHttp.get("http://"+Global.IP+"/taxwebapp/Taxi/setpanicTrue", dictionaryImp, new HttpResponseCallback() {
                    @Override
                    public void onFail(String errorMessage, int StatusCode) {

                    }

                    @Override
                    public void call(String s) {


                    }
                });

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.en_servicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
