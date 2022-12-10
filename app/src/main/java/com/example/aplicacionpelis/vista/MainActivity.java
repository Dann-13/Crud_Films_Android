package com.example.aplicacionpelis.vista;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacionpelis.Aplicacion;
import com.example.aplicacionpelis.R;
import com.example.aplicacionpelis.casos_uso.CasosUsoPelicula;
import com.example.aplicacionpelis.datos.PeliculasBD;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private CasosUsoPelicula casosUsoPelicula;
    private RecyclerView recyclerView;
    private AdaptadorPeliculasDB adaptador;
    private PeliculasBD peliculas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Inicializar los objetos
        peliculas =((Aplicacion) getApplication()).peliculas;//para obtener la inicializacion de los objetos
        adaptador = ((Aplicacion) getApplication()).adaptador;
        casosUsoPelicula= new CasosUsoPelicula(this,peliculas,adaptador);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptador);
        //barra de acciones
        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbar_Main);
        setSupportActionBar(toolbar);//soporte la barra accion
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout_Main);
        toolbar.setTitle(getTitle());//para que colapse es esconder
        //Boton flotante FAB circular
        /**/
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                casosUsoPelicula.nuevo();
            }
        });
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = (Integer) v.getTag();
                casosUsoPelicula.mostrar(posicion);//llama el metodo para mostrarlo
            }
        });

    }

}
