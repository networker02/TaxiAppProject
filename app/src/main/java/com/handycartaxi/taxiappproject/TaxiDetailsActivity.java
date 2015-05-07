package com.handycartaxi.taxiappproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.handycartaxi.taxiappproject.R;
import com.handycartaxi.taxiappproject.webserviceconection.AsyncHttp;
import com.handycartaxi.taxiappproject.webserviceconection.DictionaryImp;
import com.handycartaxi.taxiappproject.webserviceconection.HttpResponseCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TaxiDetailsActivity extends Activity {

    private static List<Taxista> taxistas = null;
    TextView taxistaNombre;
    TextView taxistaUnidad;
    ImageView taxistaFoto;
    TextView taxistaMatricula;
    TextView taxistaVehiculo;
    TextView minutots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActionBar().hide();
        setContentView(R.layout.activity_taxi_details);

        Button cancelarBtn = (Button) findViewById(R.id.cancelarBtn);
        taxistaFoto = (ImageView) findViewById(R.id.taxistaFoto);
        taxistaNombre = (TextView) findViewById(R.id.taxistaNombre);
        taxistaUnidad = (TextView) findViewById(R.id.taxistaUnidad);
        taxistaMatricula = (TextView) findViewById(R.id.taxistaMatricula);
        taxistaVehiculo = (TextView) findViewById(R.id.taxistaVehiculo);
        minutots = (TextView) findViewById(R.id.minutos);

//TAXISTAS
        taxistas = new ArrayList<Taxista>();
       taxistas.add(new Taxista(R.drawable.ic_drawer,"A11111"));
        taxistas.add(new Taxista(R.drawable.joanvidal, "A12345"));
        taxistas.add(new Taxista(R.drawable.franciscotorvisco, "A54321"));
        taxistas.add(new Taxista(R.drawable.christianmartinez, "A67890"));

        taxistaNombre.setText(Global.TAXI_NAME);
        taxistaUnidad.setText("Unidad "+Global.UNIDAD);
        taxistaVehiculo.setText("Color "+Global.COLOR_VEHICULO);
        minutots.setText(Global.TIEMPO+" minutos");

        Taxista currentTaxi = taxistas.get(Global.ID_FOTO_TAXISTA);
        taxistaFoto.setImageResource(currentTaxi.getTaxistaFoto());
        taxistaMatricula.setText("Placa "+currentTaxi.getTaxistaMatricula());

        //new Taxista(R.drawable.joanvidal,"Joan Vidal",1191,"A12345","Rojo");


        cancelarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Colocar aqui CANCEL para el WebService

//COMENTADO PORQUE HAY QUE PONER ID PEDIDO
                DictionaryImp<String,String> dictionaryImp = new DictionaryImp();
                dictionaryImp.put("PedidoID",""+Global.PEDIDO );

                AsyncHttp.get("http://"+Global.IP+"/taxwebapp/Pedido/canceled", dictionaryImp, new HttpResponseCallback() {
                    @Override
                    public void onFail(String errorMessage, int StatusCode) {
                        System.out.println("ERRRRRRRRRRRRROOOOOOOOOOOOOOOO"+errorMessage);
                    }


                    @Override
                    public void call(String s) {
                        System.out.println(s);

                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.animation1,R.anim.animation2);
                        finishAffinity();
                        Toast.makeText(getApplicationContext(), "Se ha cancelado su solicitud", Toast.LENGTH_SHORT).show();


                    }
                });


            }
        });




//AQUI VA EL SERVICE PARA QUE SE ASIGNEN LOS DATOS DEL TAXISTA RECIBIDOS


 /*       DictionaryImp<String,String> dictionaryImp = new DictionaryImp();
        dictionaryImp.put("PedidoID",""+Global.PEDIDO );

        AsyncHttp.get("http://" + Global.IP + "/taxwebapp/Taxi/getTaxiData", dictionaryImp, new HttpResponseCallback() {

            @Override
            public void onFail(String errorMessage, int StatusCode) {

            }


            @Override
            public void call(String s) {

                   System.out.println(s);
                try {
                    System.out.println(s);
                    JSONObject jsonObject = new JSONObject(s);
                    JSONObject jsonItem = jsonObject.getJSONObject("pedidoC");
                    Global.ID_ASIGNADO = jsonItem.getInt("IdAsignado");
                    Global.TAXI_NAME = jsonItem.getString("TaxiName");
                    Global.UNIDAD = jsonItem.getInt("Unidad");
                    Global.COLOR_VEHICULO = jsonItem.getString("ColorAto");
                    Global.ID_FOTO_TAXISTA = jsonItem.getInt("Foto");
                    Global.TIEMPO = jsonItem.getInt("Tiempo");

                    taxistaNombre.setText(Global.TAXI_NAME);
                    taxistaUnidad.setText("Unidad "+Global.UNIDAD);
                    taxistaVehiculo.setText("Color "+Global.COLOR_VEHICULO);
                    minutots.setText(Global.TIEMPO+" minutos");

                    Taxista currentTaxi = taxistas.get(Global.ID_FOTO_TAXISTA);
                    taxistaFoto.setImageResource(currentTaxi.getTaxistaFoto());
                    taxistaMatricula.setText("Placa "+currentTaxi.getTaxistaMatricula());

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }); */



//PONER SSERVICIO QUE ESCUCHA A QUE EL TAXISTA LLEGUE AL DESTINO
//llegaronAlDestino();

    }


public void llegaronAlDestino(){


//CONSEGUIR ID ASIGNADO
    DictionaryImp<String,String> dictionaryImp = new DictionaryImp();
           dictionaryImp.put("AsignadoID",""+Global.ID_ASIGNADO);

    AsyncHttp.get("http://" + Global.IP + "/taxwebapp/Asignado/DestinationArrivalSucessFull", dictionaryImp, new HttpResponseCallback() {

        @Override
        public void onFail(String errorMessage, int StatusCode) {


        }


        @Override
        public void call(String s) {
            Intent i = new Intent(getApplicationContext(), LlegadaActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.animation1, R.anim.animation2);
            finishAffinity();

        }
    });

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.taxi_details, menu);
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
