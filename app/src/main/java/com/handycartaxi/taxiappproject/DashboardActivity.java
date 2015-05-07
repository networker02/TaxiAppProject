package com.handycartaxi.taxiappproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.handycartaxi.taxiappproject.webserviceconection.AsyncHttp;
import com.handycartaxi.taxiappproject.webserviceconection.DictionaryImp;
import com.handycartaxi.taxiappproject.webserviceconection.HttpResponseCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DashboardActivity extends Activity {

    GPSTracker gps;
    ScheduledExecutorService executorService;

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
        final Button avisoBtn = (Button) findViewById(R.id.finServicioBtn);
        ImageButton panicButton = (ImageButton) findViewById(R.id.panicButton);
        final Button terminarButton = (Button) findViewById(R.id.terminarSrvBtn);

        gps = new GPSTracker(DashboardActivity.this);

        // check if GPS enabled
        if(gps.canGetLocation()){

            Global.LAT_TAXI = gps.getLatitude();
            Global.LON_TAXI = gps.getLongitude();

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + Global.LAT_TAXI + "\nLong: " + Global.LON_TAXI, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

        updateLocation();


        executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new Runnable() {
            public void run() {
                //put query logic here

                DictionaryImp<String,String> dictionaryImp = new DictionaryImp();
                dictionaryImp.put("TaxiId",""+Global.TAXI_ID);
                System.out.println("Taxi ID "+Global.TAXI_ID);

                AsyncHttp.get("http://" + Global.IP + "/taxwebapp/asignado/checkIfTaxiAssigned", dictionaryImp, new HttpResponseCallback() {

                    @Override
                    public void onFail(String errorMessage, int StatusCode) {
                        System.out.println(errorMessage);
                        Toast.makeText(getApplicationContext(), "No tiene servicio asignado"+Global.PEDIDO, Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void call(String s) {
                        System.out.println(s);


                        MiTaxiEstaAsignado(s);





                    }
                });




            }

    }, 5, 5, TimeUnit.SECONDS);






        ok_busy_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ok_busy_switch.isChecked()){
//ENVIAR WEBSERVICE PARA QUE LE ASIGNEN UN SERVICIO
//                    Toast.makeText(getApplicationContext(), "Yes", Toast.LENGTH_SHORT).show();

                    updateLocation();



//AQUI VA EL UN SERVICIO, EL TAXISTA ESCUCHA SI LE HAN ASIGNADO UN PEDIDO
//poner en un serviciooooooo















                }else{

                    Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_SHORT).show();


                    DictionaryImp<String,String> dictionaryImp = new DictionaryImp();
                                dictionaryImp.put("TaxiId", "" + Global.TAXI_ID);
                                dictionaryImp.put("Longitud",""+Global.LON_TAXI);
                                dictionaryImp.put("Latitud",""+Global.LAT_TAXI);
                    AsyncHttp.get("http://" + Global.IP + "/taxwebapp/Taxi/TaxiUnavailable", dictionaryImp, new HttpResponseCallback() {
                        @Override
                        public void onFail(String errorMessage, int StatusCode) {
                            System.out.println(errorMessage);
                        }

                        @Override
                        public void call(String s) {
                            System.out.println(s);


                        }
                    });



                }

            }
        });






            avisoBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                public void onClick(View v) {

                    if(Global.ID_ASIGNADO!=0){
            //                Intent i = new Intent(getApplicationContext(), EnServicioActivity.class);
            //                startActivity(i);
            //                overridePendingTransition(R.anim.animation1,R.anim.animation2);
                            Toast.makeText(getApplicationContext(), "Unidad en Servicio", Toast.LENGTH_SHORT).show();
                            avisoBtn.setEnabled(false);

                        //Poner aqui ping, para cambiar texto en la app del cliente para decirle que su taxista llegó
                            avisarllegada();



                    }else{
                        Toast.makeText(getApplicationContext(), "Aun no se le ha asignado un servicio", Toast.LENGTH_LONG).show();
                    }

                }

             });




                terminarButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(avisoBtn.isEnabled()){
                            Toast.makeText(getApplicationContext(), "No puede terminar el servicio sin haber recogido al cliente y llegado al destino final", Toast.LENGTH_LONG).show();
                        }else{

                            terminarServicio();

//AQUI
//                            en visual studio, cuando el taxista termina el servicio, delete from asignado where id_taxi=

                        }
                    }
                });



















        panicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//DONE & WORKING
                DictionaryImp<String,String> dictionaryImp = new DictionaryImp();
                dictionaryImp.put("TaxiID",""+Global.TAXI_ID);
                AsyncHttp.get("http://" + Global.IP + "/taxwebapp/Taxi/setpanicTrue", dictionaryImp, new HttpResponseCallback() {
                    @Override
                    public void onFail(String errorMessage, int StatusCode) {
                        System.out.println(errorMessage);
                    }

                    @Override
                    public void call(String s) {
                        System.out.println(s);

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

    private void updateLocation(){
        DictionaryImp<String,String> dictionaryImp = new DictionaryImp();
        dictionaryImp.put("TaxiId",""+Global.TAXI_ID );
        dictionaryImp.put("Longitud",""+Global.LON_TAXI);
        dictionaryImp.put("Latitud",""+Global.LAT_TAXI);
        AsyncHttp.get("http://"+Global.IP+"/taxwebapp/Taxi/UpdateLocation",dictionaryImp,new HttpResponseCallback() {
            @Override
            public void onFail(String errorMessage, int StatusCode) {
                System.out.println("ERROR!!!!!!!!!!!!! "+errorMessage);
            }

            @Override
            public void call(String s) {
                System.out.println(s);

            }
        });
    }

    private void avisarllegada(){

        DictionaryImp<String,String> dictionaryImp = new DictionaryImp();

        //COMENTADO PORQUE HAY QUE PONER ID ASIGNADO
        //DONE & WORKING
        dictionaryImp.put("asignadoid",""+Global.ID_ASIGNADO );

        AsyncHttp.get("http://" + Global.IP + "/taxwebapp/Asignado/setTaxiArrival", dictionaryImp, new HttpResponseCallback() {
            @Override
            public void onFail(String errorMessage, int StatusCode) {
                System.out.println(errorMessage);
            }

            @Override
            public void call(String s) {
                System.out.println(s);

            }
        });
    }

    public void terminarServicio(){


        DictionaryImp<String,String> dictionaryImp = new DictionaryImp();
        dictionaryImp.put("AsignadoID",""+Global.ID_ASIGNADO);
        AsyncHttp.get("http://" + Global.IP + "taxwebapp/Asignado/DestinationArrivalSucessFull", dictionaryImp, new HttpResponseCallback() {
            @Override
            public void onFail(String errorMessage, int StatusCode) {
                System.out.println(errorMessage);
            }

            @Override
            public void call(String s) {
                System.out.println(s);

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.animation1, R.anim.animation2);
                finish();

            }
        });
    }

    public void MiTaxiEstaAsignado(String s){
        try {
            System.out.println("ESTA ASIGNADO "+s);
            JSONObject jsonObject = new JSONObject(s);
            JSONObject jsonItem = jsonObject.getJSONObject("asign");

            if(jsonItem!=null){
                Global.ID_ASIGNADO = jsonItem.getInt("Asignado");
                Global.PEDIDO = jsonItem.getInt("Pedido");
                Global.LON = jsonItem.getDouble("Longitud");
                Global.LAT = jsonItem.getDouble("Latitud");
                Toast.makeText(getApplicationContext(), "Su taxi ha sido asignado a un servicio, Pedido: "+Global.PEDIDO, Toast.LENGTH_LONG).show();

//AQUI
                //EN visual studio, cuando se asigne un taxista a un servicio, la columna id estatus debe cambiar a 2 (En Servicio)
                executorService.shutdown();
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }








}
