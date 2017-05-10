package com.bisai.bisai.controller.activities.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.bisai.bisai.R;

public class MainActivity extends AppCompatActivity {
    private MenuItem menuEquipoTorneo;
    ImageButton masterkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menuEquipoTorneo = menu.findItem(R.id.torneo);
        return true;

    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.perfil:

            case R.id.torneo:
                    Intent masterkey = new Intent(MainActivity.this, TorneoListActivity.class);
                    startActivity(masterkey);

            case R.id.equipo:

        }
        return super.onOptionsItemSelected(item);
    }

    public void changeEquipos(boolean selEquipos){
        if (selEquipos){

        }
        else {

        }
        //this.selEquipos = selEquipos;
    }


}
