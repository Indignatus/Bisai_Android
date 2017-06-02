package com.bisai.bisai.controller.activities.master_detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bisai.bisai.R;
import com.bisai.bisai.controller.activities.main.MainActivity;
import com.bisai.bisai.controller.managers.TeamCallback;
import com.bisai.bisai.controller.managers.TeamManager;
import com.bisai.bisai.controller.managers.UserLoginManager;
import com.bisai.bisai.model.Equipo;

import java.util.Date;
import java.util.List;

public class AddPlayerActivity extends AppCompatActivity implements TeamCallback{

    private EditText nombreEquipo;
    private EditText pasword;
    private EditText password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        nombreEquipo = (EditText) findViewById(R.id.nameEquipo);
        pasword = (EditText) findViewById(R.id.pass);
        password2 = (EditText) findViewById(R.id.pass2);

        Button registrarEquipo = (Button) findViewById(R.id.addEquipo);
        registrarEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();

            }
        });



    }

    private void attemptRegister() {
        boolean cancel = false;
        String name = nombreEquipo.getText().toString();
        String pass = pasword.getText().toString();
        String pass2 = password2.getText().toString();



        Equipo equipo = new Equipo();
        // todo meter jugador que esta creado el equipo en el equipo
        // todo meter la fecha de hoy
        equipo.setPassword(pasword.getText().toString());
        equipo.setNombre(nombreEquipo.getText().toString());

        if(!pass.equals(pass2)){
            cancel = true;
            // cambiar de color en rojo las contraseñas
        }
        if(pass.equals("")){
            cancel = true;
            // cambiar de color el nombre a rojo
        }
        if(name.equals("")){
            cancel = true;
            // cambiar color fondo nombre rojo
        }

        if(cancel){
            nombreEquipo.setText("Esto no va");
            // mostrar toast erroneo
        }else{
            equipo.getJugadors().add(UserLoginManager.getInstance().getJugador());
            TeamManager.getInstance().registerAccount(AddPlayerActivity.this, equipo);
            Intent i = new Intent(AddPlayerActivity.this, MainActivity.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(),"Equipo creado " ,Toast.LENGTH_LONG);
        }

    }

    @Override
    public void onSuccessTeams(List<Equipo> teamList) {

    }

    @Override
    public void onSuccessTeam(Equipo equipo) {
        Toast.makeText(this, "Equipo "+ equipo.getNombre()  +" creado correctamente", Toast.LENGTH_LONG);
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(this, "Error al crear el equipo", Toast.LENGTH_LONG);
    }
}
