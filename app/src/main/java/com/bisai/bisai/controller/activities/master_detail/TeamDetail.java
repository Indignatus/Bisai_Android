package com.bisai.bisai.controller.activities.master_detail;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bisai.bisai.R;
import com.bisai.bisai.controller.managers.TeamCallback;
import com.bisai.bisai.controller.managers.TeamManager;
import com.bisai.bisai.model.Equipo;
import com.bisai.bisai.model.Jugador;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergi on 15/05/2017.
 */
// detalle de un team son los jugadores  de un equipo
public class TeamDetail extends AppCompatActivity implements TeamCallback {


    private ListView listaJugadores;
    private List<Jugador> jugadores;
    private Equipo equipo;
    private Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        extras = getIntent().getExtras();
        Long equipoId = extras.getLong("team");

        setContentView(R.layout.activity_detail_team);
        jugadores = new ArrayList<>();
        listaJugadores = (ListView) findViewById(R.id.listajugadores);
        // al clicar encima de uno de los equipos

        TeamManager.getInstance().getEquipoById(equipoId, TeamDetail.this);
    }

    @Override
    public void onSuccessTeams(List<Equipo> teamList) {
        // poner log
    }

    @Override
    public void onSuccessTeam(Equipo equipo) {
        this.equipo = equipo;
        this.jugadores = equipo.getJugadors();
        listaJugadores.setAdapter(new JugadoresAdapter(this, equipo));

    }

    @Override
    public void onFailure(Throwable t) {
        extras=extras;
    }

    public class JugadoresAdapter extends BaseAdapter {
        private Context context;
        private Equipo equipo;
        public JugadoresAdapter(Context context, Equipo equipo){
            this.context=context;
            this.equipo = equipo;
        }
        @Override
        public int getCount() {
            return equipo.getJugadors().size();
        }

        @Override
        public Object getItem(int position) {
            return equipo.getJugadors().get(position);
        }

        @Override
        public long getItemId(int position) {
            int id= 0;
            return id;
        }

        public class ViewHolder{
            public TextView nombreJugador;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View myView = convertView;
            if (myView == null) {
                //Inflo la lista con el layout que he creado (llista_item)
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                myView = inflater.inflate(R.layout.jugador_item_list, parent, false);
                JugadoresAdapter.ViewHolder holder = new JugadoresAdapter.ViewHolder();
                holder.nombreJugador = (TextView) myView.findViewById(R.id.jugador);
                myView.setTag(holder);




            }



            TeamDetail.JugadoresAdapter.ViewHolder holder = (TeamDetail.JugadoresAdapter.ViewHolder) myView.getTag();

            //Voy asignando los datos
            Jugador jugador = equipo.getJugadors().get(position);
            String nombre = jugador.getNickName() + "";
            holder.nombreJugador.setText(nombre);

            return myView;
        }
    }
}
