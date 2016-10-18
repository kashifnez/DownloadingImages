package com.example.manc.downloadingimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imageDisplay;

    public void btnDownloadImage (View view) {

        ImageDownloader task = new ImageDownloader();

        Bitmap myImage;


        try {
            myImage = task.execute("http://www.doglib.com/wp-content/uploads/mi/miniature-miniature-schnauzer-puppy-wallcoo-breed.jpg").get();

            imageDisplay.setImageBitmap(myImage);

        } catch (Exception e) {

            e.printStackTrace();

        }

        Log.i("Message", "Button Clicked!");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         imageDisplay = (ImageView) findViewById(R.id.imageDisplay);


    }


    public class ImageDownloader extends AsyncTask <String, Void, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);

                HttpURLConnection connection = (HttpURLConnection)url.openConnection();

                connection.connect();

                InputStream inputStream = connection.getInputStream();

                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);

                return myBitmap;

            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();
            }

            return null;
        }

    }

}
