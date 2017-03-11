package com.curso.moviesandroidcourse;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.curso.moviesandroidcourse.network.NetworkConnection;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkConnection connection = new NetworkConnection(this);
        connection.execute();
    }
}
