package com.bisai.bisai.controller.activities.master_detail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bisai.bisai.R;
import com.bisai.bisai.controller.managers.TorneoCallback;
import com.bisai.bisai.controller.managers.TorneoManager;
import com.bisai.bisai.model.Equipo;
import com.bisai.bisai.model.Torneo;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by dam on 24/5/17.
 */

public class TorneoDetailActivity  extends AppCompatActivity implements TorneoCallback{

    private ListView listaEquipos;
    private List<Equipo> equipos;
    private Torneo torneo;
    private Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        extras = getIntent().getExtras();
        Long torneoId = extras.getLong("torneo");

        setContentView(R.layout.activity_detail_torneo);
        equipos = new ArrayList<>();
        listaEquipos = (ListView) findViewById(R.id.listatorneos);
        // al clicar encima de uno de los torneos

        TorneoManager.getInstance().getTorneoById(torneoId, TorneoDetailActivity.this);
    }

    @Override
    public void onSuccessTorneos(List<Torneo> torneoList) {
        // poner log
    }

    @Override
    public void onSuccessTorneo(Torneo torneo){
        this.torneo = torneo;
        this.equipos = torneo.getEquipos();
        listaEquipos.setAdapter(new EquiposAdapter(this, torneo));

    }

    @Override
    public void  onFailure(Throwable t) {

    }

    public class EquiposAdapter extends BaseAdapter {
        private Context context;
        private Torneo torneo = null;
        public EquiposAdapter(Context context, Torneo torneo) {
            this.context=context;
            this.torneo = torneo;
        }
        @Override
        public int getCount() { return torneo.getEquipos().size(); }

        @Override
        public Object getItem(int position) { return torneo.getEquipos().get(position); }

        @Override
        public long getItemId(int position) {
            int id = 0;
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
                myView = inflater.inflate(R.layout.equipo_item_list, parent, false);
                TorneoDetailActivity.EquiposAdapter.ViewHolder holder = new TorneoDetailActivity.EquiposAdapter.ViewHolder();
                holder.tvNombre = (TextView) myView.findViewById(R.id.equipo);
                myView.setTag(holder);


            }

            TorneoListActivity.TorneosAdapter.ViewHolder holder = (TorneoListActivity.TorneosAdapter.ViewHolder) myView.getTag();

            //Vamos asignando los datos
            Equipo equipo = torneo.getEquipos().get(position);
            String nombre = equipo.getNombre() + "";
            holder.tvNombre.setText(nombre);

            return  myView;

        }

    }

}
