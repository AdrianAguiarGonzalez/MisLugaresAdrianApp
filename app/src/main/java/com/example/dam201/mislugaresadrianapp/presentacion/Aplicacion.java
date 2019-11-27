package com.example.dam201.mislugaresadrianapp.presentacion;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.dam201.mislugaresadrianapp.modelo.LugaresVector;

/**
 * Created by dam201 on 23/10/2019.
 */

public class Aplicacion extends Application {
    private int saldo;
    private LugaresVector mislugares;

    @Override
    public void onCreate(){
        super.onCreate();
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        saldo = pref.getInt("saldo_inicial", -1);
        mislugares = new LugaresVector();
    }

    public int getSaldo(){return saldo; }

    public void  setSaldo(int saldo) { this.saldo = saldo; }

    public LugaresVector getLugares() { return mislugares; }
}
