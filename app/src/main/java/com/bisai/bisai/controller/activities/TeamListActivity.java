package com.bisai.bisai.controller.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bisai.bisai.R;
import com.bisai.bisai.controller.activities.main.MainActivity;
import com.bisai.bisai.controller.managers.TeamCallback;
import com.bisai.bisai.controller.managers.TeamManager;
import com.bisai.bisai.model.Equipo;

import java.util.List;

public class TeamListActivity extends AppCompatActivity implements TeamCallback {

    ListView listaEquipos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);
        listaEquipos = (ListView) findViewById(R.id.listaequipos);
        TeamManager.getInstance().getAllTeams(TeamListActivity.this);
    }

    @Override
    public void onSuccessTeam(List<Equipo> teamList) {
        listaEquipos.setAdapter(new TeamListActivity.ProductsAdapter(this, teamList));
    }

    @Override
    public void onFailure(Throwable t) {

    }

    public class ProductsAdapter extends BaseAdapter {
        private Context context;
        private List<Equipo> equipos;
        public ProductsAdapter(Context context, List<Equipo> equipos){
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
                TeamListActivity.ProductsAdapter.ViewHolder holder = new TeamListActivity.ProductsAdapter.ViewHolder();
                holder.tvNombre = (TextView) myView.findViewById(R.id.nombreequipo);
                myView.setTag(holder);
            }
            TeamListActivity.ProductsAdapter.ViewHolder holder = (TeamListActivity.ProductsAdapter.ViewHolder) myView.getTag();

            //Voy asignando los datos
            Equipo equipo = equipos.get(position);
            String nombre = equipo.getNombre() + "";
            holder.tvNombre.setText(nombre);

            return myView;
        }
    }
}