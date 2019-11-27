package com.example.dam201.mislugaresadrianapp.presentacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.dam201.mislugaresadrianapp.R;
import com.example.dam201.mislugaresadrianapp.casouso.CasoUsoLugares;
import com.example.dam201.mislugaresadrianapp.modelo.Lugar;


public class AdaptadorLugares extends BaseAdapter {
    private LayoutInflater inflador; // Crea Layouts a partir del XML

    TextView nombre, direccion;
    ImageView foto;
    RatingBar valoracion;
    CasoUsoLugares misLugares;

    public AdaptadorLugares(Context contexto, CasoUsoLugares usoLugares) {
        inflador = (LayoutInflater) contexto
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        misLugares=usoLugares;


    }

    public View getView(int posicion, View vistaReciclada,
                        ViewGroup padre) {
        Lugar lugar = misLugares.retornar(posicion);
        if (vistaReciclada == null) {
            vistaReciclada =
                    inflador.inflate(R.layout.elemento_lista, null);
        }

        nombre = (TextView) vistaReciclada.findViewById(R.id.nombre);
        direccion =
                (TextView) vistaReciclada.findViewById(R.id.direccion);
        foto = (ImageView) vistaReciclada.findViewById(R.id.foto);
        valoracion =
                (RatingBar) vistaReciclada.findViewById(R.id.valoracion);
        nombre.setText(lugar.getNombre());
        direccion.setText(lugar.getDireccion());
        int id = R.drawable.otros;
        switch(lugar.getTipo()) {
            case RESTAURANTE:id = R.drawable.restaurante; break;
            case BAR:    id = R.drawable.bar;     break;
            case COPAS:   id = R.drawable.copas;    break;
            case ESPECTACULO: id = R.drawable.espectaculos; break;
            case HOTEL:   id = R.drawable.hotel;    break;
            case COMPRAS:  id = R.drawable.compras;   break;
            case EDUCACION: id = R.drawable.educacion;  break;
            case DEPORTE:  id = R.drawable.deporte;   break;
            case NATURALEZA: id = R.drawable.naturaleza; break;
            case GASOLINERA: id = R.drawable.gasolinera; break;
        }

        foto.setImageResource(id);
        foto.setScaleType(ImageView.ScaleType.FIT_END);
        valoracion.setRating(lugar.getValoracion());
        return vistaReciclada;
    }

    public int getCount() {
        return misLugares.numeroLugares();
    }

    public Object getItem(int posicion) {
        return misLugares.retornar(posicion);
    }

    public long getItemId(int posicion) {
        return posicion;
    }

}
