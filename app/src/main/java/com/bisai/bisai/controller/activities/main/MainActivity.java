package com.bisai.bisai.controller.activities.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bisai.bisai.R;

public class MainActivity extends AppCompatActivity {
    private MenuItem sel24hMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        sel24hMenuItem = menu.findItem(R.id.perfil);
        return true;

    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.perfil:


            case R.id.torneo:


            case R.id.equipo:

        }
        return super.onOptionsItemSelected(item);
    }


}
