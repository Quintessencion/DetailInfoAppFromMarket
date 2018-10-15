package com.simbhome.detailinfoappfrommarket;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MyTask().execute();
    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            Document document;
            try {
                document = Jsoup.connect("https://play.google.com/store/apps/details?id=ru.vtb.invest" + "&hl=en")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get();

                String title = document.selectFirst("h1.AHFaub > span").ownText();
                String subTitle = document.selectFirst("div.i4sPve > span.T32cc > a.hrTbp").ownText();
                String iconUrl = document.selectFirst("div.dQrBL > img").attr("src");
                String rate = document.selectFirst("div.BHMmbe").ownText();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
