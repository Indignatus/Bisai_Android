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
import com.bisai.bisai.controller.activities.main.MainActivityMenu;
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
        View focusView = null;
        pasword.setError(null);
        password2.setError(null);
        nombreEquipo.setError(null);
        String name = nombreEquipo.getText().toString();
        String pass = pasword.getText().toString();
        String pass2 = password2.getText().toString();



        Equipo equipo = new Equipo();
        // todo meter jugador que esta creado el equipo en el equipo
        // todo meter la fecha de hoy
        equipo.setPassword(pasword.getText().toString());
        equipo.setNombre(nombreEquipo.getText().toString());

        Toast toast1 = Toast.makeText(getApplicationContext(), "Error al crear el equipo", Toast.LENGTH_SHORT);
        Toast toast2 = Toast.makeText(getApplicationContext(), "Equipo " + name+ " Creado correctamente", Toast.LENGTH_SHORT);

        if(!pass.equals(pass2)){
            pasword.setError("Las contraseñas no coinciden");
            password2.setError("Las contraseñas no coinciden");
            focusView = pasword;
            cancel = true;
            // cambiar de color en rojo las contraseñas
        }
        if(pass.equals("")){
            pasword.setError("La contraseña no puede estar vacía");
            focusView = pasword;
            cancel = true;
        }
        if(pass.equals("")){
            password2.setError("Tienes que repetir la contraseña");
            focusView = pasword;
            cancel = true;
        }
        if(name.equals("")){
            nombreEquipo.setError("El nombre del equipo no puede estar vacío");
            focusView = nombreEquipo;
            cancel = true;
        }

        if(cancel){
            focusView.requestFocus();
            toast1.show();

        }else{
            equipo.getJugadors().add(UserLoginManager.getInstance().getJugador());
            TeamManager.getInstance().registerAccount(AddPlayerActivity.this, equipo);
            Intent i = new Intent(AddPlayerActivity.this, MainActivityMenu.class);
            startActivity(i);
            toast2.show();
        }

    }

    @Override
    public void onSuccessTeams(List<Equipo> teamList) {

    }

    @Override
    public void onSuccessTeam(Equipo equipo) {

    }

    @Override
    public void onFailure(Throwable t) {

    }
}
