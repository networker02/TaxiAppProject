package com.handycartaxi.taxiappproject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CompaniasListActivity extends Activity {

    private static List<Compania> companias = new ArrayList<Compania>();
    private ListView list;
    private static boolean ONCE=true;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        list = (ListView)findViewById(R.id.taxiCompanylistView);





        if(ONCE){
            fillCompaniesList();
            ONCE=false;
        }
        fillListView();
        registerClicks();

        Bundle bund = getIntent().getExtras();
        if (bund == null) {
            Log.d("Intent nulllllllllllllllllllllllllllllllllllllllllllllllllllllll", "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
        }else{
            Compania co = (Compania) bund.getSerializable("new");

            companias.add(co);
            list.invalidateViews();
        }





    }


    private void fillCompaniesList(){
        companias.add(new Compania("Apolo Taxi",R.drawable.apolotaxilogo4,1));
        companias.add(new Compania("Fama Taxi",R.drawable.famataxilogo,2));
        companias.add(new Compania("Excelente Taxi", R.drawable.excelentetaxilogo,3));
        companias.add(new Compania("Rueda Taxi",R.drawable.ruedataxilogo2,4));
        companias.add(new Compania("Aero Taxi",R.drawable.aerotaxilogo,5));
        companias.add(new Compania("Antena Taxi",R.drawable.antenataxilogo,6));
//        companias.add(new Compania("Excelente Taxi",R.drawable.apolotaxilogo3));
//        companias.add(new Compania("Excelente Taxi",R.drawable.ruedataxilogo2));
//        companias.add(new Compania("Excelente Taxi",R.drawable.aerotaxilogo));
//        companias.add(new Compania("Excelente Taxi",R.drawable.excelentetaxilogo));
//        companias.add(new Compania("Excelente Taxi",R.drawable.apolotaxilogo3));
//        companias.add(new Compania("Excelente Taxi",R.drawable.ruedataxilogo2));
//        companias.add(new Compania("Excelente Taxi",R.drawable.aerotaxilogo));

    }


    private void fillListView(){
        ArrayAdapter<Compania> adapter = new MyListAdapter();
        list.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        list.invalidateViews();
    }


    private class MyListAdapter extends ArrayAdapter<Compania> {
        public MyListAdapter() {
            super(CompaniasListActivity.this, R.layout.activity_list, companias);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
            }

            Compania currentCompany = companias.get(position);

            ImageView imageView = (ImageView)itemView.findViewById(R.id.item_logo);
            imageView.setImageResource(currentCompany.getLogoId());

            return itemView;
        }
    }

    private void registerClicks() {

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicker, int position,
                                    long id) {


                Intent itemClick = new Intent(getApplicationContext(), TaxiTypeActivity.class);
                itemClick.putExtra("compania",companias.get(position));

                startActivity(itemClick);
                overridePendingTransition(R.anim.animation1,R.anim.animation2);

            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list, menu);
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
