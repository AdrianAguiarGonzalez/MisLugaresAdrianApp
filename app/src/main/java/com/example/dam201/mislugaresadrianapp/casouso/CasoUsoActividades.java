package com.example.dam201.mislugaresadrianapp.casouso;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.dam201.mislugaresadrianapp.presentacion.AcercaDeActivity;

/**
 * Created by dam201 on 23/10/2019.
 */

public class CasoUsoActividades {
    private Activity actividad;

    public CasoUsoActividades(Activity actividad){
        this.actividad = actividad;
    }

    public void lanzarAcercaDe(View view){

        Intent i = new Intent(actividad, AcercaDeActivity.class);
        actividad.startActivity(i);
    }
    public void lanzarPreferencias(View view){
        Toast mensa = Toast.makeText(actividad,
                "Nombre Apellido Alumno: Opción en construccion", Toast.LENGTH_SHORT);
        mensa.show();

        //Intent i = new Intent(actividad, ACercaDeActivity.class);
        //actividad.startActivity(i);
    }
    public void lanzarMapas(View view){

        Toast mensa = Toast.makeText(actividad,
                "Nombre Apellido Alumno: Opción en construccion", Toast.LENGTH_SHORT);
        mensa.show();
        //Intent i = new Intent(actividad, ACercaDeActivity.class);
        //actividad.startActivity(i);
    }
}
