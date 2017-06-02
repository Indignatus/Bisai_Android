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
import android.widget.ListView;
import android.widget.TextView;

import com.bisai.bisai.R;
import com.bisai.bisai.controller.managers.TorneoCallback;
import com.bisai.bisai.controller.managers.TorneoManager;
import com.bisai.bisai.model.Torneo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by dam on 24/5/17.
 */

public class TorneoListActivity extends AppCompatActivity implements TorneoCallback{

    ListView listaTorneos;
    List<Torneo> torneos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torneo_list);
        torneos = new ArrayList<>();
        listaTorneos = (ListView) findViewById(R.id.listatorneos);
        //al clicar encima de uno de los equipos

        TorneoManager.getInstance().getAllTorneos(TorneoListActivity.this);
    }

    @Override
    public void onSuccessTorneos(List<Torneo> torneoList) {
        torneos=torneoList;
        listaTorneos.setAdapter(new TorneosAdapter(this, torneoList));
    }

    @Override
    public void onSuccessTorneo(Torneo torneo) {

    }

    @Override
    public void onFailure(Throwable t) {

    }

    public class TorneosAdapter extends BaseAdapter {
        private Context context;
        private List<Torneo> torneos;
        public TorneosAdapter(Context context, List<Torneo> torneos) {
            this.context=context;
            this.torneos=torneos;
        }
        @Override
        public int getCount() { return torneos.size(); }

        @Override
        public Object getItem(int position) { return torneos.get(position); }

        @Override
        public long getItemId(int position) {
            int id=0;
            return id;
        }

        public class ViewHolder {
            public TextView tvNombre;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View myView = convertView;
            if (myView == null) {
                //Inflo la lista con el layout que he creado (llista_item)
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                myView = inflater.inflate(R.layout.item_list, parent, false);
                TorneoListActivity.TorneosAdapter.ViewHolder holder = new TorneoListActivity.TorneosAdapter.ViewHolder();
                holder.tvNombre = (TextView) myView.findViewById(R.id.nombreequipo);
                myView.setTag(holder);


                listaTorneos.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Context context = view.getContext();
                        Intent intent = new Intent(context, TorneoDetail.class);
                        intent.putExtra("torneo", torneos.get(i).getId());
                        context.startActivity(intent);
                    }
                });

            }


            TorneosAdapter.ViewHolder holder = (TorneosAdapter.ViewHolder) myView.getTag();

            //Vamos asignando los datos
            Torneo torneo = torneos.get(position);
            String nombre = torneo.getNombre() + "";
            holder.tvNombre.setText(nombre);

            return myView;

        }

    }


}
