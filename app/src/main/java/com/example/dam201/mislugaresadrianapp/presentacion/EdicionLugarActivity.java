package com.example.dam201.mislugaresadrianapp.presentacion;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dam201.mislugaresadrianapp.R;
import com.example.dam201.mislugaresadrianapp.casouso.CasoUsoLugares;
import com.example.dam201.mislugaresadrianapp.modelo.Lugar;
import com.example.dam201.mislugaresadrianapp.modelo.TipoLugar;

import java.text.DateFormat;
import java.util.Date;

public class EdicionLugarActivity extends AppCompatActivity {

    private EditText nombre;
    private EditText direccion;
    private EditText telefono;
    private EditText url;
    private EditText comentario;
    //1.- declaro spinner
    private Spinner tipo;
    /***************************
     * datos
     * **************************/
    private CasoUsoLugares usoLugar;
    private int pos;
    private Lugar lugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_lugar);
        Bundle extras = getIntent().getExtras();
        pos = extras.getInt("pos", 0);
        usoLugar = new CasoUsoLugares(this, ((Aplicacion) getApplication()).getLugares());//-------------
    /*Recupero el lugar segun su posicion*/
        lugar = usoLugar.retornar(pos);
        actualizaVistas();
    }

    public void actualizaVistas(){
        nombre = findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());
        direccion = findViewById(R.id.direccion);
        direccion.setText(lugar.getDireccion());
        telefono = findViewById(R.id.telefono);
        telefono.setText(Integer.toString(lugar.getTelefono()));
        url = findViewById(R.id.url);
        url.setText(lugar.getUrl());
        comentario = findViewById(R.id.comentario);
        comentario.setText(lugar.getComentario());
        //Trabajar con el spinner
        tipo = findViewById(R.id.spTipo);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, TipoLugar.getNombres());
        adaptador.setDropDownViewResource(android.R.layout.
                simple_spinner_dropdown_item);
        tipo.setAdapter(adaptador);
        tipo.setSelection(lugar.getTipo().ordinal());
    }

    //Trabajando con menús
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editar_lugar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean opExito=true;
        switch (item.getItemId()) {
            case R.id.accion_guardar:
                modificaVistas();
                usoLugar.guardar(pos, lugar);
                finish();
                break;
            case R.id.accion_cancelar:
                usoLugar.cancelar();
                break;
            default:
                opExito=super.onOptionsItemSelected(item);
        }
        return opExito;
    }

    public void modificaVistas(){
        lugar.setNombre(nombre.getText().toString());
        lugar.setTipo(TipoLugar.values()[tipo.getSelectedItemPosition()]);
        lugar.setDireccion(direccion.getText().toString());
        //verifica que no este a 0 lo que significa que se desconoce
        if (telefono.getText().toString().isEmpty())
            lugar.setTelefono(0);
        else lugar.setTelefono(Integer.parseInt(telefono.getText().toString()));
        lugar.setUrl(url.getText().toString());
        lugar.setComentario(comentario.getText().toString());
    }
}
