package com.handycartaxi.taxiappproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

public class LoadingActivity extends Activity {
    int myProgress = 0;
    ProgressBar  myProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        myProgressBar = (ProgressBar) findViewById(R.id.progressBar);





    Runnable myThread = new Runnable() {

                public void run() {
            //don't hard code things, use a constant for max progress value
            while ( myProgress<100 ){
                try{
                    myHandle.sendMessage(myHandle.obtainMessage());
                    //same
                    Thread.sleep( 50 );
                } catch(Exception ex){
                    //never live an empty catch statement, you are missing exceptions and
                    //can't correct them
                    Log.e("MyCurrentClass", "Error during async data processing");
                }//catch
            }//while
            //start new activity here

                    Intent i = new Intent(getApplicationContext(), TaxiDetailsActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.animation1, R.anim.animation2);
        }//met

        Handler myHandle = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                myProgress++;
                myProgressBar.setProgress(myProgress);
            }
        };
    };

        new Thread(myThread).start();
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
