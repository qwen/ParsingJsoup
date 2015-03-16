package com.example.parsingjsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView tvInfo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        MyTask mt = new MyTask();
        mt.execute();
    }

    class MyTask extends AsyncTask<Void,Void, Void> {
        String title;
        protected Void doInBackground(Void... params) {
            Document doc = null;
            try {
                doc = Jsoup.connect("http://free2ride.ru/resort/russia/murmanskaya/bolchoi_vudayvr").get();
            } catch (IOException e) {

                e.printStackTrace();
            }
            //String title = doc.title(); 

             title = String.valueOf(doc.select("p"));  //p- tag-filtr for parsing


            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
        	super.onPostExecute(result);
            tvInfo.setText(title);
        }
    }
}