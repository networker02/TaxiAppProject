package com.handycartaxi.taxiappproject;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.model.LatLng;
import com.handycartaxi.taxiappproject.webserviceconection.AsyncHttp;
import com.handycartaxi.taxiappproject.webserviceconection.DictionaryImp;
import com.handycartaxi.taxiappproject.webserviceconection.HttpResponseCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class TaxiTypeActivity extends Activity {

    int id_tipo_taxi=1;
//    double lat;
//    double lon;

    int id_compania;
    GPSTracker gps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActionBar().hide();
        setContentView(R.layout.activity_taxi_type);


        Button pedirBtn = (Button) findViewById(R.id.pedirButton2);
        Button cancelBtn = (Button) findViewById(R.id.cancelarbtn);
        final RadioGroup radioGroup;
        TextView compania_textView = (TextView) findViewById(R.id.companiaTextView);

        Bundle bundle = getIntent().getExtras();
        Compania co = (Compania) bundle.getSerializable("compania");
        compania_textView.setText(co.getNombre_compania());

        id_compania = co.id_compania();

        /* Initialize Radio Group and attach click handler */
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();


        /* Attach CheckedChangeListener to radio group */
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if(null!=rb && checkedId > -1){



                    if(rb.getText().equals("Minibus")){
                        id_tipo_taxi=2;
                    }else if(rb.getText().equals("Unidad Confortable")){
                        id_tipo_taxi=3;
                    }else if(rb.getText().equals("Unidad Especial")){
                        id_tipo_taxi=4;
                    }

                    //Mandar por webservice el tipo de Vehiculo seleccionado,
//                    para que en la Base sepan cual tipo asignar
                    //AQUI\

                    Toast.makeText(getApplicationContext(), rb.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        pedirBtn.setOnClickListener(new View.OnClickListener() {




                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), LoadingActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.animation1,R.anim.animation2);


                    gps = new GPSTracker(TaxiTypeActivity.this);

                    // check if GPS enabled
                    if(gps.canGetLocation()){

                        Global.LAT = gps.getLatitude();
                        Global.LON = gps.getLongitude();

                        // \n is for new line
                        Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + Global.LAT + "\nLong: " + Global.LON, Toast.LENGTH_LONG).show();
                    }else{
                        // can't get location
                        // GPS or Network is not enabled
                        // Ask user to enable GPS/network in settings
                        gps.showSettingsAlert();
                    }



                  DictionaryImp<String,String> dictionaryImp = new DictionaryImp();
                    dictionaryImp.put("Latitud",""+Global.LAT);
                    dictionaryImp.put("Longitud",""+Global.LON);
                    dictionaryImp.put("TipoTaxi",""+id_tipo_taxi);
                    dictionaryImp.put("IdCompany",""+id_compania);
                    dictionaryImp.put("Cancelado","false");
                    dictionaryImp.put("Habilitado","true");

                 //   Toast.makeText(getApplicationContext(), "latitud"+lat +" longitud"+lon+" tipo_taxi"+id_tipo_taxi +" compania "+id_compania+" cancelado"+0+ " habilitado"+1, Toast.LENGTH_LONG).show();

                    AsyncHttp.get("http://"+Global.IP+"/taxwebapp/Pedido/insertData", dictionaryImp, new HttpResponseCallback() {
                        @Override
                        public void onFail(String errorMessage, int StatusCode) {
                            System.out.println(errorMessage);
                        }

                        @Override
                        public void call(String s) {
                            System.out.println(s);

                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                Global.setPEDIDO (jsonObject.getInt("id"));
                                System.out.println(Global.PEDIDO);




                                startService(new Intent(getApplicationContext(), MyService.class));

                                //getData();


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    });


                }

                });



        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.animation1,R.anim.animation2);
                finishAffinity();
                Toast.makeText(getApplicationContext(),"Se ha cancelado su solicitud", Toast.LENGTH_SHORT).show();


            }
        });


    }

    public void getData(){
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
                    System.out.println("SYSOOOOOOOOOO "+s);
                    JSONObject jsonObject = new JSONObject(s);
                    JSONObject jsonItem = jsonObject.getJSONObject("pedidoC");
//                    g.setID_ASIGNADO(jsonItem.getInt("IdAsignado"));
//                    g.setTAXI_NAME (jsonItem.getString("TaxiName"));
//                    g.setUNIDAD(jsonItem.getInt("Unidad"));
//                    g.setCOLOR_VEHICULO(jsonItem.getString("ColorAto"));
//                    g.setID_FOTO_TAXISTA(jsonItem.getInt("Foto"));
//                    g.setTIEMPO(jsonItem.getInt("Tiempo"));


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

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            //your code here
            new LatLng(location.getLatitude(), location.getLatitude());
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.taxi_type, menu);
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
