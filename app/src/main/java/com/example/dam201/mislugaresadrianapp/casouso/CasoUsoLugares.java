package com.example.dam201.mislugaresadrianapp.casouso;

import android.app.Activity;
import android.content.Intent;

import com.example.dam201.mislugaresadrianapp.modelo.Lugar;
import com.example.dam201.mislugaresadrianapp.modelo.LugaresVector;
import com.example.dam201.mislugaresadrianapp.presentacion.EdicionLugarActivity;
import com.example.dam201.mislugaresadrianapp.presentacion.VistaLugarActivity;

/**
 * Created by dam201 on 23/10/2019.
 */

public class CasoUsoLugares {
    private Activity actividad;
    private LugaresVector misLugares;
    public CasoUsoLugares(Activity actividad,LugaresVector misLugares) {

        this.actividad = actividad;
        this.misLugares = misLugares;
    }
    public void mostrar(int pos) {
        Intent i = new Intent(actividad, VistaLugarActivity.class);
    i.putExtra("pos", pos);
        actividad.startActivity(i);
    }

    public Lugar retornar(int pos){
        return misLugares.elemento(pos);
    }

    public int numeroLugares(){
        return misLugares.tamanyo();
    }

    public void eliminar(int pos){
        misLugares.borrar(pos);
    }

    public void modificar(int pos, int codigoSolicitud)
    {
        Intent i = new Intent(actividad, EdicionLugarActivity.class);
        i.putExtra("pos", pos);
        actividad.startActivityForResult(i, codigoSolicitud);
    }

    public void guardar(int pos, Lugar lugar){
        misLugares.actualiza(pos, lugar);
    }

    public void cancelar()
    {
        actividad.finish();
    }
}
