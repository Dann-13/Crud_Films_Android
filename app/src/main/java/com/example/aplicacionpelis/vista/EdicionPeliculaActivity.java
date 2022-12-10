package com.example.aplicacionpelis.vista;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplicacionpelis.Aplicacion;
import com.example.aplicacionpelis.R;
import com.example.aplicacionpelis.casos_uso.CasosUsoPelicula;
import com.example.aplicacionpelis.datos.PeliculasBD;
import com.example.aplicacionpelis.modelo.Genero;
import com.example.aplicacionpelis.modelo.Pelicula;
import com.google.android.material.textfield.TextInputEditText;

public class EdicionPeliculaActivity extends AppCompatActivity {
    private PeliculasBD peliculas;
    private AdaptadorPeliculasDB adaptador;
    private CasosUsoPelicula usoPelicula;
    private int pos, _id;
    private Pelicula pelicula;
    private TextInputEditText nombrePelicula, directorPelicula, paisOrigen, anioEstreno, sinopsis;
    private Spinner genero;
    private Toast msnToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edicion_pelicula);//inicializamos los objetos
        peliculas = ((Aplicacion) getApplication()).peliculas;
        adaptador=((Aplicacion)getApplication()).adaptador;
        usoPelicula = new CasosUsoPelicula(this, peliculas,adaptador);
        Bundle extras = getIntent().getExtras();//obtiene los extras para traerlos
        pos = extras.getInt("pos", -1);//por defecto cuando voy a editar un registro entonces pos va cambiar y valer un valor diferente de -1 y cuando vaya a crear un registro id vatener un valor de una llave primaria
        _id=extras.getInt("_id",-1);
        if (_id!=-1){// va crear un unevo registro
            setTitle("Nueva Película");
            pelicula= peliculas.elemento(_id);
        }
        else pelicula = adaptador.posicionDB(pos); // lo que tenga la varaible pos
        actualizaVistas();
    }
    public void actualizaVistas(){
        nombrePelicula = findViewById(R.id.inputEditTextnombrePelicula);
        nombrePelicula.setText(pelicula.getNombrePelicula());
        directorPelicula = findViewById(R.id.inputEditTextDirector);
        directorPelicula.setText(pelicula.getDirector());
        paisOrigen=findViewById(R.id.inputEditTextOrigen);
        paisOrigen.setText(pelicula.getPaisOrigen());
        anioEstreno=findViewById(R.id.inputEditTextAnio);
        anioEstreno.setText(Integer.toString(pelicula.getAnioLanzamiento()));
        sinopsis =findViewById(R.id.inputEditTextSinopsis);
        sinopsis.setText(pelicula.getSinopsis());
        genero=findViewById(R.id.spinnerGenero);//array para trabajar con el spinner lo que tiene en el enumerado
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Genero.getNombres());
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genero.setAdapter(adaptador);
        genero.setSelection(pelicula.getTipoGenero().ordinal());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edicion,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.accion_guardar:
                pelicula.setNombrePelicula(nombrePelicula.getText().toString());
                pelicula.setTipoGenero(Genero.values()[genero.getSelectedItemPosition()]);
                pelicula.setDirector(directorPelicula.getText().toString());

                pelicula.setAnioLanzamiento(Integer.parseInt(anioEstreno.getText().toString()));
                pelicula.setSinopsis(sinopsis.getText().toString());
                pelicula.setPaisOrigen(paisOrigen.getText().toString());
                msnToast = Toast.makeText(getApplicationContext(),"Cambios guardados exitosamente",Toast.LENGTH_LONG);
                msnToast.setGravity(Gravity.CENTER,0,0);
                msnToast.show();
                if (_id==-1) _id=adaptador.idPosicion(pos);
                usoPelicula.guardar(_id, pelicula);
                finish();
                return true;
            case R.id.accion_cancelar:
                if (_id!=-1) peliculas.borrar(_id);
                msnToast = Toast.makeText(getApplicationContext(),"Canceló la edición no ay cambios",Toast.LENGTH_LONG);
                msnToast.setGravity(Gravity.CENTER,0,0);
                msnToast.show();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
