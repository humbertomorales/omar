package com.alsea.humberto.earthquake;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ListView lvData;
    private String url= "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson";
    private CustomAdapter adapter;
    private Context context;
    private MyAsync myAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

      lvData = (ListView) findViewById(R.id.listView);

    }

    @Override
    protected void onResume() {
        super.onResume();

       myAsync = new MyAsync();
        myAsync.execute();

        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object obj = parent.getItemAtPosition(position);



                Intent intent = new Intent(MainActivity.this, DetailedInfoActiviy.class);

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class MyAsync extends AsyncTask<String,Void,Void>{

        String json;
        ArrayList resul;

        @Override
        protected Void doInBackground(String... params) {
            WSAccess ws = new WSAccess();
            json = ws.makeCall(url);
            if(json !=null)
            resul = ws.parseQuakeParse(json);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            adapter = new CustomAdapter(context,resul);

            lvData.setAdapter(adapter);
        }
    }
}
