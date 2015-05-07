package com.handycartaxi.taxiappproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.handycartaxi.taxiappproject.webserviceconection.AsyncHttp;
import com.handycartaxi.taxiappproject.webserviceconection.DictionaryImp;
import com.handycartaxi.taxiappproject.webserviceconection.HttpResponseCallback;

public class LoadingActivity extends Activity {
    int myProgress = 0;
    ProgressBar  myProgressBar;
    boolean cancelWasPressed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActionBar().hide();
        setContentView(R.layout.activity_loading);

        Button cancelBtn = (Button) findViewById(R.id.cancelBtn);
        myProgressBar = (ProgressBar) findViewById(R.id.progressBar);



        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i_1);
                overridePendingTransition(R.anim.animation1,R.anim.animation2);
                Toast.makeText(getApplicationContext(),"Se ha cancelado su solicitud", Toast.LENGTH_SHORT).show();
                cancelWasPressed = false;
                finishAffinity();
                //Colocar aqui cancel para el WebService
//                showDialog();


//COMENTADO PORQUE HAY QUE PONER ID PEDIDO
                DictionaryImp<String,String> dictionaryImp = new DictionaryImp();
                dictionaryImp.put("Pedido",""+Global.PEDIDO );

                AsyncHttp.get("http://"+Global.IP+"/taxwebapp/Pedido/canceled", dictionaryImp, new HttpResponseCallback() {
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



    public AlertDialog showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

        builder.setTitle("Information")
                .setIcon(
                        getResources().getDrawable(
                                android.R.drawable.alert_light_frame))
                .setMessage("Message saved as draft.");

        return builder.create();
    }


    @Override
    public void onBackPressed() {
        cancelWasPressed = false;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.loading, menu);
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
