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

import java.util.List;

public class passwordTeamAddPlayer extends AppCompatActivity implements TeamCallback {
    private EditText password;
    private Bundle extras;
    private Long equipoId;
    private Long userId = UserLoginManager.getInstance().getJugador().getId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_team_add_player);

        extras = getIntent().getExtras();
        equipoId = extras.getLong("idTeam");
        password = (EditText) findViewById(R.id.password);

        Button registrarEquipo = (Button) findViewById(R.id.registrar);
        registrarEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();

            }
        });


    }

    private void attemptRegister() {
        String pass = password.getText().toString();

        TeamManager.getInstance().addJugadorEquipo(passwordTeamAddPlayer.this, equipoId, userId, pass);
        Intent i = new Intent(passwordTeamAddPlayer.this, MainActivity.class);
        startActivity(i);



    }

    @Override
    public void onSuccessTeams(List<Equipo> teamList) {

    }

    @Override
    public void onSuccessTeam(Equipo equipo) {
        Toast.makeText(this, "Equipo "+ equipo.getNombre()  +" Se ha unido correctamente ", Toast.LENGTH_LONG);
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
