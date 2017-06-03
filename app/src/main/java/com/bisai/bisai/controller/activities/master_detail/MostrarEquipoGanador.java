package com.bisai.bisai.controller.activities.master_detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bisai.bisai.R;

public class MostrarEquipoGanador extends AppCompatActivity {
    private Bundle extras;
    private String ganador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_equipo_ganador);

        extras = getIntent().getExtras();
        ganador = extras.getString("ganador");

        TextView nombreGanador = (TextView) findViewById(R.id.ganador);
        nombreGanador.setText(ganador);
    }
}
