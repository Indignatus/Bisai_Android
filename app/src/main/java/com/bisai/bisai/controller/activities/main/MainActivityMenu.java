package com.bisai.bisai.controller.activities.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bisai.bisai.R;
import com.bisai.bisai.controller.activities.login.LoginActivity;
import com.bisai.bisai.controller.activities.master_detail.AddPlayerActivity;
import com.bisai.bisai.controller.activities.master_detail.MostrarEquipoGanador;
import com.bisai.bisai.controller.activities.master_detail.TeamListActivity;
import com.bisai.bisai.controller.activities.master_detail.TeamPlayerListActivity;
import com.bisai.bisai.controller.activities.master_detail.TorneoDetailPendientes;
import com.bisai.bisai.controller.activities.master_detail.TorneoListActivity;
import com.bisai.bisai.controller.activities.master_detail.TorneosFinalizados;
import com.bisai.bisai.controller.managers.TeamManager;
import com.bisai.bisai.controller.managers.TorneoCallback;
import com.bisai.bisai.controller.managers.TorneoManager;
import com.bisai.bisai.controller.managers.UserLoginManager;
import com.bisai.bisai.model.Torneo;

import java.util.List;

public class MainActivityMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TorneoCallback {
    ListView listaTorneos;
    List<Torneo> torneos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listaTorneos = (ListView) findViewById(R.id.listaTorneosPendientes);
        TorneoManager.getInstance().getTorneoPendienteJugador(MainActivityMenu.this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);

        TextView nikname = (TextView) findViewById(R.id.perfilJugadorName);
        TextView correo = (TextView) findViewById(R.id.perfilCorreoJugador);

        nikname.setText(UserLoginManager.getInstance().getJugador().getNickName());
        correo.setText(UserLoginManager.getInstance().getJugador().getUser().getEmail());

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(MainActivityMenu.this, LoginActivity.class);
            // todo borrar el codigo
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_equiposOun) {
            Intent i = new Intent(MainActivityMenu.this, TeamPlayerListActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_shearchTeam) {
            Intent i = new Intent(MainActivityMenu.this, TeamListActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(MainActivityMenu.this, AddPlayerActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_buscar_torneos) {
            Intent i = new Intent(MainActivityMenu.this, TorneoListActivity.class);
            startActivity(i);
        }else if(id == R.id.nav_buscar_resultado_torneos){
            Intent i = new Intent(MainActivityMenu.this, TorneosFinalizados.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSuccessTorneos(List<Torneo> torneoList) {
        torneos=torneoList;
        TextView textoAgendaTorneo = (TextView) findViewById(R.id.textoAgendaTorneo);
        if(torneoList.size()==0 || torneoList == null){
            textoAgendaTorneo.setText("Actualmente no estás apuntado a ningun Torneo");
        }else{
            textoAgendaTorneo.setText("Lista torneos pendientes:");
        }
        listaTorneos.setAdapter(new MainActivityMenu.TorneosAdapter(this, torneoList));
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
                TorneosAdapter.ViewHolder holder = new TorneosAdapter.ViewHolder();
                holder.tvNombre = (TextView) myView.findViewById(R.id.nombreequipo);
                myView.setTag(holder);


                listaTorneos.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent a = new Intent(MainActivityMenu.this, TorneoDetailPendientes.class);
                        a.putExtra("nombre", torneos.get(i).getNombre());
                        a.putExtra("descipcion", torneos.get(i).getDescripcion());
                        a.putExtra("fecha", torneos.get(i).getFechaInicio());
                        a.putExtra("juego", torneos.get(i).getJuego().getNombre());
                        startActivity(a);
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
