package com.bisai.bisai.controller.activities.master_detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bisai.bisai.R;
import com.bisai.bisai.controller.managers.UserLoginManager;
import com.bisai.bisai.model.User;

public class PerfilUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_user);

        TextView nikname = (TextView) findViewById(R.id.perfilNicname);
        TextView correo = (TextView) findViewById(R.id.perfilCorreo);
        TextView nombre = (TextView) findViewById(R.id.perfilName);
        TextView apellido = (TextView) findViewById(R.id.perfilApellido);


        nikname.setText(UserLoginManager.getInstance().getJugador().getNickName());
        correo.setText(UserLoginManager.getInstance().getJugador().getUser().getEmail());
        nombre.setText(UserLoginManager.getInstance().getJugador().getUser().getFirstName());
        apellido.setText(UserLoginManager.getInstance().getJugador().getUser().getLastName());
    }



}
