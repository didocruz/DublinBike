package com.example.dionisio.dublinbike;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadTask task = new DownloadTask();
        task.execute("https://api.jcdecaux.com/vls/v1/stations?contract=Dublin&apiKey=e85abc09fa9848f6e595b2df0a9da288c53c5891");



        Button Map = (Button)findViewById(R.id.Map);
        Button Weather=(Button)findViewById(R.id.Weather);
        Button About=(Button)findViewById(R.id.About);
        Button List=(Button)findViewById(R.id.ListButton);


        Weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent weather = new Intent(MainActivity.this,WeatherActivity.class);
                startActivity(weather);
            }
        });

        Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);

            }






        });

        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);

            }






        });

        List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, List.class);
                startActivity(intent);

            }






        });


    }

    public void list(View view) {
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            String dataParsed="";
            String singleParsed="";
            URL url;
            HttpURLConnection urlConnection = null;


            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;

                    result += current;

                    data = reader.read();

                }

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


            String nameStore[] = new String[100];
            ArrayList<String> listView = new ArrayList<String>();


            try {

                JSONObject jsonObject = new JSONObject(result);

                String dublinBikeInfo = jsonObject.getString("name");

                Log.i("dublin_Bike", dublinBikeInfo);

                JSONArray JA = new JSONArray(dublinBikeInfo);

                for (int i = 0; i < JA.length(); i++) {



                    JSONObject JO = (JSONObject) JA.get(i);


                    String stationName= JO.getString("number");
                    String stationNumber= JO.getString("name");




                    Log.i("number", stationNumber);
                    Log.i("name", stationName);
                  //  Log.i("number", jsonPart.getString("number"));
                   // Log.i("address", jsonPart.getString("address"));
                       // Log.i("latitude", jsonPart.getString("latitude"));
                   // Log.i("longitude", jsonPart.getString("longitude"));

                }

                Log.i("name", String.valueOf(listView));


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

}
