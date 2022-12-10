package com.example.aplicacionpelis;

import android.app.Application;

import com.example.aplicacionpelis.datos.PeliculasBD;
import com.example.aplicacionpelis.vista.AdaptadorPeliculasDB;

public class Aplicacion extends Application {
    public PeliculasBD peliculas;
    public AdaptadorPeliculasDB adaptador;
    @Override
    public void onCreate() {
        super.onCreate();//inicializar
        peliculas = new PeliculasBD(this);
        adaptador=new AdaptadorPeliculasDB(peliculas, peliculas.extraeCursor());
    }
}
