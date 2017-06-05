package com.bisai.bisai.controller.activities.master_detail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bisai.bisai.R;
import com.bisai.bisai.controller.managers.TeamCallback;
import com.bisai.bisai.controller.managers.TeamManager;
import com.bisai.bisai.controller.managers.UserLoginManager;
import com.bisai.bisai.model.Equipo;
import com.bisai.bisai.model.Jugador;

import java.util.ArrayList;
import java.util.List;

public class TeamListActivity extends AppCompatActivity implements TeamCallback {

    ListView listaEquipos;
    List<Equipo> equipos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);
        equipos= new ArrayList<>();
        listaEquipos = (ListView) findViewById(R.id.listaequipos);
        // al clicar encima de uno de los equipos

        TeamManager.getInstance().getAllTeams(TeamListActivity.this);
        final EditText textoBuscar = (EditText) findViewById(R.id.textoBuscadorEquipos);
        Button button = (Button) findViewById(R.id.buscadorEquipos);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
String palabraPalabradora = textoBuscar.getText().toString();
                if(palabraPalabradora.equals("")){
                    TeamManager.getInstance().getAllTeams(TeamListActivity.this);
                }else{
                    TeamManager.getInstance().getAllTorneosBuscar(palabraPalabradora,TeamListActivity.this);
                }

            }
        });
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
            if (!isHear){
                equiposs.add(e);
            }

        }
        equipos=equiposs;
        listaEquipos.setAdapter(new TeamsAdapter(this, equipos));
    }

    @Override
    public void onSuccessTeam(Equipo equipo) {

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
                        Context context = view.getContext();
                        Intent intent = new Intent(context, TeamDetail.class);
                        intent.putExtra("team", equipos.get(i).getId());
                        context.startActivity(intent);
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