package com.example.aplicacionpelis.vista;

import android.database.Cursor;

import com.example.aplicacionpelis.datos.PeliculasBD;
import com.example.aplicacionpelis.datos.RepositorioPeliculas;
import com.example.aplicacionpelis.modelo.Pelicula;

public class AdaptadorPeliculasDB extends AdaptadorPeliculas{
    protected Cursor cursor;

    public AdaptadorPeliculasDB(RepositorioPeliculas peliculas, Cursor cursor) {//como extiende de adaptador
        super(peliculas);//herencia
        this.cursor=cursor;
    }
    public Cursor getCursor() {
        return cursor;
    }
    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }
    public Pelicula posicionDB(int posicion){//para saber a apartir de la posicion de la base de datos sacar un registro de alli
        cursor.moveToPosition(posicion);//buscan por la posicion y el la saca
        return PeliculasBD.extraePelicula(cursor);//retorna un objeto
    }
    public int idPosicion(int posicion) {//para saber la llave primaria lo que va devolver el metodo busca por id
        cursor.moveToPosition(posicion);
        return cursor.getInt(0);
    }
    public int posicionId(int id) {//recorrer la posicion hasta que coincida
        int pos = 0;
        while (pos < getItemCount() && idPosicion(pos) != id) pos++;
        if (pos >= getItemCount()) return -1;//-1 es por si hay un error
        else return pos;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int posicion) { //
        //super.onBindViewHolder(holder, posicion); Aquí debemos comentarlo
        Pelicula pelicula= posicionDB(posicion);
        holder.personaliza(pelicula);
        holder.itemView.setTag(new Integer(posicion));//para poder acceder a los clic
    }
    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

}
