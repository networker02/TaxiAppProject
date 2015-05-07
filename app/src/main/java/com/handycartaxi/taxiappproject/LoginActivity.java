package com.handycartaxi.taxiappproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.handycartaxi.taxiappproject.webserviceconection.AsyncHttp;
import com.handycartaxi.taxiappproject.webserviceconection.DictionaryImp;
import com.handycartaxi.taxiappproject.webserviceconection.HttpResponseCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends Activity {

    boolean ok = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActionBar().hide();
        setContentView(R.layout.activity_login);


        ImageButton loginBtn = (ImageButton) findViewById(R.id.loginButton);
        ImageButton limpiarnBtn = (ImageButton) findViewById(R.id.limpiarButton);
        final EditText userField = (EditText)findViewById(R.id.userField);
        final EditText passField = (EditText) findViewById(R.id.passField);

        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                checkCredentials(userField.getText().toString(), passField.getText().toString());

            }
        });

        limpiarnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userField.setText("");
                passField.setText("");

            }
        });
    }


  /*  public static boolean checkCredentials(String username, String password){
        if (username.equalsIgnoreCase("j") && password.equalsIgnoreCase("123")){
            return true;
        }else{
            return false;
        }
    } */

    public  void checkCredentials(String username, String password){



        DictionaryImp<String,String> dictionaryImp = new DictionaryImp();
        dictionaryImp.put("username",username );
        dictionaryImp.put("password",password);
        AsyncHttp.get("http://"+Global.IP+"/taxwebapp/UserLogin/IsLoginCorrect",dictionaryImp,new HttpResponseCallback() {
            @Override
            public void onFail(String errorMessage, int StatusCode) {
                System.out.println(errorMessage);

            }

            @Override
            public void call(String s) {
                System.out.println(s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    boolean a = jsonObject.getBoolean("isLoginCorrect");
                    System.out.println(a);

                    if(a){
                        Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.animation1, R.anim.animation2);
                    }else{
                        Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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
