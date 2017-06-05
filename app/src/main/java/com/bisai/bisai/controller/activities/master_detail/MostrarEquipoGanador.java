package com.bisai.bisai.controller.activities.master_detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bisai.bisai.R;
import com.bisai.bisai.controller.activities.main.MainActivityMenu;

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
        Button button = (Button) findViewById(R.id.regresoMenuPrincipal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MostrarEquipoGanador.this, MainActivityMenu.class);
                startActivity(i);
            }
        });
    }
}
