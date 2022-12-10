package com.example.aplicacionpelis.vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicacionpelis.Aplicacion;
import com.example.aplicacionpelis.R;
import com.example.aplicacionpelis.casos_uso.CasosUsoPelicula;
import com.example.aplicacionpelis.datos.PeliculasBD;
import com.example.aplicacionpelis.modelo.Pelicula;

public class VistaPeliculaActivity extends AppCompatActivity {
    private PeliculasBD peliculas;
    private AdaptadorPeliculasDB adaptador;
    private CasosUsoPelicula usoPelicula;
    private int pos, _id=-1;
    private Pelicula pelicula;
    private TextView txtNombre, txtDirector, txtSinopsis, txtAnio, txtGenero, txtPais;
    private ImageView logoGenero;
    final static int RESULTADO_EDITAR = 1;// variable tipo unmrerico para compararlo con una varible que esta en otro metodo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//inicializa
        setContentView(R.layout.vista_pelicula);
        peliculas = ((Aplicacion)getApplication()).peliculas;
        adaptador =((Aplicacion)getApplication()).adaptador;
        usoPelicula = new CasosUsoPelicula(this, peliculas,adaptador);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) pos = extras.getInt("pos", 0);
        else pos =0;
        _id= adaptador.idPosicion(pos);
        pelicula = adaptador.posicionDB(pos);//peliculas.elemento(pos);
        actualizaVistas();
    }
    public void actualizaVistas() {
        txtNombre = findViewById(R.id.nombrePelicula);
        txtNombre.setText(pelicula.getNombrePelicula());//modifica el texto del textview por el nombre de la pelicula que trae
        txtDirector = findViewById(R.id.txtdirector);
        txtDirector.setText(pelicula.getDirector());
        txtSinopsis = findViewById(R.id.sinopsis);
        txtSinopsis.setText(pelicula.getSinopsis());
        txtAnio=findViewById(R.id.anioEstreno);
        txtAnio.setText(Integer.toString(pelicula.getAnioLanzamiento()));
        logoGenero = findViewById(R.id.logo_tipo);
        logoGenero.setImageResource(pelicula.getTipoGenero().getImagen());
        txtGenero=findViewById(R.id.generoPelicula);
        txtGenero.setText(pelicula.getTipoGenero().getTexto());
        txtPais = findViewById(R.id.paisOrigen);
        txtPais.setText(pelicula.getPaisOrigen());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//para mostrar el menu vista
        getMenuInflater().inflate(R.menu.menu_vista,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//seleccióna las opciones demenu
        switch (item.getItemId()) {
            case R.id.accion_editar:
                usoPelicula.editar(pos,RESULTADO_EDITAR);
                return true;
            case R.id.accion_borrar:
                int id = adaptador.idPosicion(pos);
                usoPelicula.borrar(id);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {//cuando se vaya a la parte de edicion ellos devuelven algo
        if (requestCode == RESULTADO_EDITAR) {// el busca el elemento para actualizar lavista
            pelicula= peliculas.elemento(_id);
            pos=adaptador.posicionId(_id);
            actualizaVistas();
            findViewById(R.id.scrollView1).invalidate();//obligar al layout que repite o muestra la modificacion
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
