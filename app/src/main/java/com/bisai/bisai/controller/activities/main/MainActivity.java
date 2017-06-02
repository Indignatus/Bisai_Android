package com.bisai.bisai.controller.activities.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bisai.bisai.R;
import com.bisai.bisai.controller.activities.master_detail.AddPlayerActivity;
import com.bisai.bisai.controller.activities.master_detail.PerfilUser;
import com.bisai.bisai.controller.activities.master_detail.TeamListActivity;
import com.bisai.bisai.controller.activities.master_detail.TeamPlayerListActivity;
import com.bisai.bisai.controller.managers.UserLoginManager;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                               Intent i = new Intent(MainActivity.this, TeamListActivity.class);
                                startActivity(i);
                            }
                  });

        Button button2 = (Button) findViewById(R.id.crearEquipo);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddPlayerActivity.class);
                startActivity(i);
            }
        });

        Button button3 = (Button) findViewById(R.id.verEquiposDeJugador);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TeamPlayerListActivity.class);
                startActivity(i);
            }
        });

        TextView nikname = (TextView) findViewById(R.id.nikname);

        // sigleton como es 1 en toda la aplicación sobre el UserLoginMaeger tenemos que hacer un getInstance
        nikname.setText(UserLoginManager.getInstance().getJugador().getNickName());
    }


}
