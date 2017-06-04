package com.bisai.bisai.controller.activities.master_detail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.bisai.bisai.R;
import com.bisai.bisai.controller.managers.TorneoCallback;
import com.bisai.bisai.controller.managers.TorneoManager;
import com.bisai.bisai.model.Equipo;
import com.bisai.bisai.model.Torneo;

import java.util.List;

public class TorneoDetailPendientes extends AppCompatActivity implements TorneoCallback {
    private Bundle extras;
    private String nombreTorneo;
    private String descripcionTorneo;
    private String fechaTorneo;
    private String nombreJuegoTorneo;
    private ListView listaEquipos;
    private List<Equipo> equipos;
    private Torneo torneo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torneo_detail_pendientes);
        extras = getIntent().getExtras();
        nombreTorneo = extras.getString("nombre");
        descripcionTorneo = extras.getString("descripcion");
        fechaTorneo = extras.getString("fecha");
        nombreJuegoTorneo = extras.getString("juego");
        listaEquipos = (ListView) findViewById(R.id.listaEquiposInscritosTorneo);

        TextView nameT = (TextView) findViewById(R.id.torneoName);
        TextView descriptionT = (TextView) findViewById(R.id.description);
        TextView fechaT = (TextView) findViewById(R.id.fecha);
        TextView juegoT = (TextView) findViewById(R.id.juego);
        nameT.setText(nombreTorneo);
        descriptionT.setText("Descripcion del Torneo: "+descripcionTorneo);
        fechaT.setText("Fecha inicio del torneo: "+fechaTorneo);
        juegoT.setText("Este torneo es sobre el Juego: "+nombreJuegoTorneo);
    }

    @Override
    public void onSuccessTorneos(List<Torneo> torneoList){
        // poner log
    }

    @Override
    public void onSuccessTorneo(Torneo torneo) {
        final Long torneoId = extras.getLong("torneo");
        TorneoManager.getInstance().getTorneoById(torneoId, TorneoDetailPendientes.this);

        this.torneo = torneo;
        this.equipos = torneo.getEquipos();
        listaEquipos.setAdapter(new EquiposAdapter(this, torneo));

    }

    @Override
    public void onFailure(Throwable t) { extras=extras; }

    public class EquiposAdapter extends BaseAdapter {
        private Context context;
        private Torneo torneo;
        public EquiposAdapter(Context context, Torneo torneo){
            this.context=context;
            this.torneo = torneo;
        }
        @Override
        public int getCount() { return torneo.getEquipos().size();}

        @Override
        public Object getItem(int position) { return torneo.getEquipos().get(position);}

        @Override
        public long getItemId(int position){
            int id = 0;
            return id;
        }

        public class ViewHolder{
            public TextView nombreEquipo;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View myView = convertView;
            if (myView == null) {
                //Inflamos la lista cone el layout que se ha creado (llista_item)
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                myView = inflater.inflate(R.layout.equipo_item_list, parent, false);
                EquiposAdapter.ViewHolder holder = new EquiposAdapter.ViewHolder();
                holder.nombreEquipo = (TextView) myView.findViewById(R.id.equipo);
                myView.setTag(holder);



            }


            TorneoDetail.EquiposAdapter.ViewHolder holder = (TorneoDetail.EquiposAdapter.ViewHolder) myView.getTag();

            //Voy asignando los datos
            Equipo equipo = torneo.getEquipos().get(position);
            String nombre = equipo.getNombre() + "";
            holder.nombreEquipo.setText(nombre);

            return  myView;
        }

    }
}
