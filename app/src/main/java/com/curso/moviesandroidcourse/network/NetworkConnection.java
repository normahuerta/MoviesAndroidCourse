package com.curso.moviesandroidcourse.network;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.curso.moviesandroidcourse.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 210206561 on 11/03/2017.
 */

public class NetworkConnection extends AsyncTask<Void,Void, Boolean>{

    private final String TAG = NetworkConnection.class.getSimpleName();
    private Context context;
    private String responseStr;

    public NetworkConnection(Context context){
        this.context = context;

    }

    @Override
    protected Boolean doInBackground(Void... params) {
        final String BASE_URL = "http://api.themoviedb.org/3/movie";
        final String POPULAR_PATH ="popular";
        final String API_KEY_PARAM = "api_key";

        //Construcccion de URL
        Uri uriToAPI = Uri.parse(BASE_URL).buildUpon()
                .appendEncodedPath(POPULAR_PATH)
                .appendQueryParameter(API_KEY_PARAM,context.getString(R.string.api_key_value)).build();

        Log.d(TAG,uriToAPI.toString());

        HttpURLConnection urlConnection;
        BufferedReader reader;

        try {
            URL url = new URL(uriToAPI.toString());

            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer =new StringBuffer();

           //Validacion a no conexion
            if (inputStream == null){
                return false; //No hay nada que hacer
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            //Lectura del InputStream
            String line;
            while ((line = reader.readLine()) != null){
                buffer.append(line + "\n");//Se anexa linea
            }

            if (buffer.length() == 0){
                return false;//No tiene lineas, la cadena esta vacia
            }

            responseStr = buffer.toString();
            Log.d(TAG,"Server Response: "+responseStr);
            return true;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(TAG,e.toString());
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG,e.toString());
            return false;
        }
    }
}
