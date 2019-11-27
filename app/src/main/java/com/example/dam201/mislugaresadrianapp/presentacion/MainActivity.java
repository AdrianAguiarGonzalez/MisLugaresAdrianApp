package com.example.dam201.mislugaresadrianapp.presentacion;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dam201.mislugaresadrianapp.R;
import com.example.dam201.mislugaresadrianapp.casouso.CasoUsoActividades;
import com.example.dam201.mislugaresadrianapp.casouso.CasoUsoLugares;

public class MainActivity extends AppCompatActivity {

    private CasoUsoActividades usoAplicaciones;
    private CasoUsoLugares usoLugares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usoAplicaciones = new CasoUsoActividades(this);
        usoLugares = new CasoUsoLugares(this,((Aplicacion) getApplication()).getLugares() );

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean correcto = true;
        int id = item.getItemId();
        switch (id) {
            case R.id.menuAcercaDe:
                actividadAcercaDe(null);
                break;
            case R.id.menu_buscar:
                actividadVistaLugar(null);
                break;
            case R.id.menuSettings:
                actividadPreferencias(null);
                break;
            default:
                Toast mensa = Toast.makeText(this, "Nombre Apellido Alumno: Opción en construccion",
                        Toast.LENGTH_SHORT);
                mensa.show();
                correcto = super.onOptionsItemSelected(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void actividadAcercaDe(View view){
        usoAplicaciones.lanzarAcercaDe(null);//Lanzar a método da clase CasoUsoActividades

    }

    public void pararAplicacion(View view){
        finish();
    }

    public void actividadPreferencias(View view){
        usoAplicaciones.lanzarPreferencias(null);
    }

    public void actividadVistaLugar(View view){
        final EditText entrada = new EditText(this);
        //bloqueamos a numeros
        entrada.setInputType(InputType.TYPE_CLASS_NUMBER);
        entrada.setText("0");
        new AlertDialog.Builder(this)
                .setTitle("Selección de lugar")
                .setMessage("indica su id:")
                .setView(entrada)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        int id= Integer.parseInt (entrada.getText().toString());
                        if (id<usoLugares.numeroLugares())
                            usoLugares.mostrar(id);
                            //desde aquí no se puede enviar un toast
                        else smsErrores("Nombre Apellido Alumno: Posicion no existe");
                    }})
                .setNegativeButton("Cancelar", null)
                .show();

    }

    public void smsErrores(String error){
        Toast mensa = Toast.makeText(this, error,
                Toast.LENGTH_SHORT);
        mensa.show();

    }

    public void actividadListaLugares(View view){
        Intent i = new Intent(this, TipoLugarActivity.class);
        this.startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

}
