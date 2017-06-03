package com.bisai.bisai.controller.activities.master_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bisai.bisai.R;
import com.bisai.bisai.controller.activities.main.MainActivity;
import com.bisai.bisai.controller.activities.main.MainActivityMenu;
import com.bisai.bisai.controller.managers.TeamCallback;
import com.bisai.bisai.controller.managers.TeamManager;
import com.bisai.bisai.controller.managers.TorneoCallback;
import com.bisai.bisai.controller.managers.TorneoManager;
import com.bisai.bisai.controller.managers.UserLoginManager;
import com.bisai.bisai.model.Equipo;
import com.bisai.bisai.model.Jugador;
import com.bisai.bisai.model.Torneo;

import java.util.ArrayList;
import java.util.List;

public class SeleccionEquipoForJoinTeam extends AppCompatActivity implements TeamCallback , TorneoCallback {

    private Bundle extras;
    ListView listaEquipos;
    List<Equipo> equipos;
    private Long torneoId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_player_list);
        equipos= new ArrayList<>();
        listaEquipos = (ListView) findViewById(R.id.listaequipos);
        extras = getIntent().getExtras();
        torneoId = extras.getLong("torneo");


        // al clicar encima de uno de los equipos

        TeamManager.getInstance().getAllTeams(SeleccionEquipoForJoinTeam.this);
    }

    @Override
    public void onSuccessTeams(List<Equipo> teamList) {
        List<Equipo> equiposs = new ArrayList<>();
        boolean isHear;
        for (Equipo e : teamList){
            isHear = false;
            for (Jugador j : e.getJugadors()){
                if(j.getId() == UserLoginManager.getInstance().getJugador().getId()){
                    isHear = true;
                }
            }
            if (isHear){
                equiposs.add(e);
            }

        }
        equipos=equiposs;
        listaEquipos.setAdapter(new SeleccionEquipoForJoinTeam.TeamsAdapter(this, equipos));
    }

    @Override
    public void onSuccessTeam(Equipo equipo) {


    }

    @Override
    public void onSuccessTorneos(List<Torneo> torneoList) {


    }

    @Override
    public void onSuccessTorneo(Torneo torneo) {

    }

    @Override
    public void onFailure(Throwable t) {

    }

    public class TeamsAdapter extends BaseAdapter {
        private Context context;
        private List<Equipo> equipos;
        public TeamsAdapter(Context context, List<Equipo> equipos){
            this.context=context;
            this.equipos=equipos;
        }
        @Override
        public int getCount() {
            return equipos.size();
        }

        @Override
        public Object getItem(int position) {
            return equipos.get(position);
        }

        @Override
        public long getItemId(int position) {
            int id= 0;
            return id;
        }

        public class ViewHolder{
            public TextView tvNombre;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View myView = convertView;
            if (myView == null) {
                //Inflo la lista con el layout que he creado (llista_item)
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                myView = inflater.inflate(R.layout.item_list, parent, false);
                TeamsAdapter.ViewHolder holder = new TeamsAdapter.ViewHolder();
                holder.tvNombre = (TextView) myView.findViewById(R.id.nombreequipo);
                myView.setTag(holder);



                listaEquipos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        long idEquipo = equipos.get(i).getId();
                        Intent a = new Intent(SeleccionEquipoForJoinTeam.this, MainActivityMenu.class);
                        TorneoManager.getInstance().addEquipoTorneo(SeleccionEquipoForJoinTeam.this, torneoId, idEquipo);
                        startActivity(a);
                    }
                });

            }



            TeamsAdapter.ViewHolder holder = (TeamsAdapter.ViewHolder) myView.getTag();

            //Voy asignando los datos

                Equipo equipo = equipos.get(position);
                String nombre = equipo.getNombre() + "";
                holder.tvNombre.setText(nombre);

            return myView;
        }
    }
}
