package com.example.aplicacionpelis.vista;

import static com.example.aplicacionpelis.modelo.Genero.ACCION;
import static com.example.aplicacionpelis.modelo.Genero.AVENTURAS;
import static com.example.aplicacionpelis.modelo.Genero.CIENCIA_FICCION;
import static com.example.aplicacionpelis.modelo.Genero.COMEDIA;
import static com.example.aplicacionpelis.modelo.Genero.TERROR;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacionpelis.R;
import com.example.aplicacionpelis.datos.RepositorioPeliculas;
import com.example.aplicacionpelis.modelo.Pelicula;

public class AdaptadorPeliculas extends RecyclerView.Adapter<AdaptadorPeliculas.ViewHolder>{
    protected RepositorioPeliculas peliculas; //repositorio peliculas para el crud
    protected View.OnClickListener onClickListener;// para los eventos
    public AdaptadorPeliculas(RepositorioPeliculas peliculas) {
        this.peliculas = peliculas;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre, sinopsis, anio;
        public ImageView foto;
        //Creamos nuestro ViewHolder, con los tipos de elementos a modificar
        public ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            sinopsis = itemView.findViewById(R.id.sinopsisTxtView);
            foto = itemView.findViewById(R.id.foto);
            anio = itemView.findViewById(R.id.anioEstrenoTxtView);
        }
        // Personalizamos un ViewHolder a partir de un objeto
        public void personaliza(Pelicula pelicula) {//recibe un parametro de peliculas
            nombre.setText(pelicula.getNombrePelicula());//cambiamos por el nombre de la pelicula
            sinopsis.setText(pelicula.getSinopsis());
            anio.setText(Integer.toString(pelicula.getAnioLanzamiento()));
            int id = R.drawable.movie;//Para recorrer el switch
            switch (pelicula.getTipoGenero()){
                case ACCION:id= R.drawable.accion; break;//llama las constantes que anunciamos anteriormente
                case COMEDIA:id= R.drawable.comedia; break;
                case TERROR:id= R.drawable.terror; break;
                case AVENTURAS:id= R.drawable.aventuras; break;
                case CIENCIA_FICCION:id= R.drawable.cienciaficcion; break;
            }
            foto.setImageResource(id);//Imageview para cambiar el icono
            foto.setScaleType(ImageView.ScaleType.FIT_END);
        }
    }
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    //estos métodos son propios del ViewHolder y deben ser importados
    //Creamos el ViewHolder con la vista de un elemento sin personalizar
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflamos la vista desde el xml
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista, parent, false);
        vista.setOnClickListener(onClickListener);
        return new ViewHolder(vista);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pelicula pelicula = peliculas.elemento(position);
        holder.personaliza(pelicula);//llamamos el metodo personaliza
    }
    @Override
    public int getItemCount() {//para saber cuantos registros va dibujar
        return peliculas.tamanio();
    }

}
