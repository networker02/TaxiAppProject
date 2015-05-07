package com.handycartaxi.taxiappproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.MapFragment;
import com.handycartaxi.taxiappproject.webserviceconection.AsyncHttp;
import com.handycartaxi.taxiappproject.webserviceconection.DictionaryImp;
import com.handycartaxi.taxiappproject.webserviceconection.HttpResponseCallback;

public class DashboardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (savedInstanceState == null) {
//            getFragmentManager().beginTransaction().replace(R.id.fragment, new MapFragment()).commit();
//        }
        getActionBar().hide();

     //   new MapsActivity().setUpMap();
        setContentView(R.layout.activity_dashboard);



        final Switch ok_busy_switch = (Switch) findViewById(R.id.ok_busy_switch);
        Button avisoBtn = (Button) findViewById(R.id.finServicioBtn);
        ImageButton panicButton = (ImageButton) findViewById(R.id.panicButton);

        ok_busy_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ok_busy_switch.isChecked()){
//ENVIAR WEBSERVICE PARA QUE LE ASIGNEN UN SERVICIO
                    Toast.makeText(getApplicationContext(), "Yes", Toast.LENGTH_SHORT).show();


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

//AQUI VA EL UN SERVICIO, EL TAXISTA ESCUCHA SI LE HAN ASIGNADO UN PEDIDO




                }else{
 //ENVIAR WEBSERVICE PARA QUE NO LE ASIGNEN UN SERVICIO
                    Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_SHORT).show();

//CONSEGUIR LOS DATOS COMENTADOS
                    DictionaryImp<String,String> dictionaryImp = new DictionaryImp();
                    //            dictionaryImp.put("TaxiId",taxiId );
                    //            dictionaryImp.put("Longitud",longitud);
                    //            dictionaryImp.put("Latitud",latitud);
                    AsyncHttp.get("http://"+Global.IP+"/taxwebapp/Taxi/TaxiUnavailable",dictionaryImp,new HttpResponseCallback() {
                        @Override
                        public void onFail(String errorMessage, int StatusCode) {

                        }

                        @Override
                        public void call(String s) {


                        }
                    });



                }

            }
        });


        avisoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), EnServicioActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.animation1,R.anim.animation2);
                Toast.makeText(getApplicationContext(), "Unidad en Servicio", Toast.LENGTH_SHORT).show();


//Poner aqui ping, para cambiar texto en la app del cliente para decirle que su taxista llegó


                DictionaryImp<String,String> dictionaryImp = new DictionaryImp();

//COMENTADO PORQUE HAY QUE PONER ID ASIGNADO

  //              dictionaryImp.put("asignadoid",username );

                AsyncHttp.get("http://"+Global.IP+"/taxwebapp/Asignado/setTaxiArrival", dictionaryImp, new HttpResponseCallback() {
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
 //PONER AQUI WEBSERVICE PARA AVISAR PANICO O PELIGRO

//COMENTADO PORQUE HAY QUE PONER ID PEDIDO

                DictionaryImp<String,String> dictionaryImp = new DictionaryImp();
//                dictionaryImp.put("TaxiID",username );
                AsyncHttp.get("http://"+Global.IP+"/taxwebapp/Taxi/setpanicTrue",dictionaryImp,new HttpResponseCallback() {
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
        getMenuInflater().inflate(R.menu.dashboard, menu);
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
