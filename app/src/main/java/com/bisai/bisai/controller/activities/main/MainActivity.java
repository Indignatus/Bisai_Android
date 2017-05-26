package com.bisai.bisai.controller.activities.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bisai.bisai.R;
import com.bisai.bisai.controller.activities.master_detail.TeamListActivity;
import com.bisai.bisai.controller.activities.master_detail.TorneoListActivity;
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

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TorneoListActivity.class);
                startActivity(i);
            }
        });
        TextView nikname = (TextView) findViewById(R.id.nikname);

        // sigleton como es 1 en toda la aplicación sobre el UserLoginMaeger tenemos que hacer un getInstance
        nikname.setText("Logueado como: " + UserLoginManager.getInstance().getJugador().getNickName());
    }


}
