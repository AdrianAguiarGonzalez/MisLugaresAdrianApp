package com.example.dam201.mislugaresadrianapp.presentacion;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dam201.mislugaresadrianapp.R;
import com.example.dam201.mislugaresadrianapp.casouso.CasoUsoLugares;
import com.example.dam201.mislugaresadrianapp.modelo.Lugar;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

public class VistaLugarActivity extends AppCompatActivity {
    final static  int RESULTADO_EDITAR = 1;
    final static int RESULTADO_GALERIA = 2;
    final static int RESULTADO_FOTO = 3;

    private CasoUsoLugares usoLugar;
    private int pos;
    private Lugar lugar;

    //Elementos de la interfaz
    private ImageView imageView;
    private Uri uriFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //para permitir acceso a camara
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        setContentView(R.layout.activity_vista_lugar);
        Bundle extras = getIntent().getExtras();
        pos = extras.getInt("pos", 0);//Recuperar la posicion a visualizar
        usoLugar = new CasoUsoLugares(this, ((Aplicacion) getApplication()).getLugares());//Crear y acceder a la lista de lugares
        lugar = usoLugar.retornar(pos);//lugar sobre el que se está trabajando
        actualizaVistas();
    }


    public void actualizaVistas(){
        imageView= (ImageView) findViewById(R.id.foto);
        //imageView esta inicializada a la foto de res
        TextView nombre = findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());
        ImageView logo_tipo = findViewById(R.id.logo_tipo);
        logo_tipo.setImageResource(lugar.getTipo().getRecurso());
        TextView tipo = findViewById(R.id.tipo);
        tipo.setText(lugar.getTipo().getTexto());
        TextView direccion = findViewById(R.id.direccion);
        direccion.setText(lugar.getDireccion());
        TextView telefono = findViewById(R.id.telefono);
        telefono.setText(Integer.toString(lugar.getTelefono()));
        TextView url = findViewById(R.id.url);
        url.setText(lugar.getUrl());
        TextView comentario = findViewById(R.id.comentario);
        comentario.setText(lugar.getComentario());
        TextView fecha = findViewById(R.id.fecha);
        fecha.setText(DateFormat.getDateInstance().format(
                new Date(lugar.getFecha())));
        TextView hora = findViewById(R.id.hora);
        hora.setText(DateFormat.getTimeInstance().format(
                new Date(lugar.getFecha())));
        RatingBar valoracion = findViewById(R.id.valoracion);
        valoracion.setRating(lugar.getValoracion());
        valoracion.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override public void onRatingChanged(RatingBar ratingBar,
                                                          float valor, boolean fromUser) {
                        lugar.setValoracion(valor);
                    }
                });
        if (lugar.getFoto()!= null && !lugar.getFoto().isEmpty() && !lugar.getFoto().equals("null")) {
            ponerFoto(lugar.getFoto());//Actualizar a vista coa foto que eu selecciono
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vistalugar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean opExito=true;
        switch (item.getItemId()) {
            case R.id.accion_compartir:
                compartirLugar();
                break;
            case R.id.accion_llegar:
                llegarLugar();
                break;
            case R.id.accion_editar:
                editarLugar(pos, RESULTADO_EDITAR);
                //  usoLugar.editar(pos,RESULTADO_EDITAR);
                break;
            case R.id.accion_borrar:
                new AlertDialog.Builder(this)
                        .setTitle("Borrado de lugar")
                        .setMessage("¿Estás seguro de que quieres modificar este lugar?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                eliminarLugar(pos);
                            }})
                        .setNegativeButton("Cancelar", null)
                        .show();

                break;
            default:
                opExito=super.onOptionsItemSelected(item);
        }
        return opExito;
    }

    public void compartirLugar(){}

    public void llegarLugar(){}

    public void editarLugar(int pos,int codigoSolicitud){
        usoLugar.modificar( pos,  codigoSolicitud);

    }

    public void eliminarLugar(int pos){
        usoLugar.eliminar(pos);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case RESULTADO_EDITAR:
                actualizaVistas();
                findViewById(R.id.scrollView1).invalidate();
                break;
            case RESULTADO_GALERIA:
                    if (resultCode == Activity.RESULT_OK) {//Si se executou correctamente
                        lugar.setFoto(data.getDataString());
                        ponerFoto( lugar.getFoto());
                    } else {
                        Toast.makeText(this, "Foto no cargada", Toast.LENGTH_LONG).show();
                    }

                break;
            case RESULTADO_FOTO:
                if (resultCode == Activity.RESULT_OK
                        && lugar!=null && uriFoto!=null) {
                    lugar.setFoto(uriFoto.toString());
                    ponerFoto( lugar.getFoto());
                } else {
                    Toast.makeText(this, "Error en captura", Toast.LENGTH_LONG).show();
                }
                break;
            default:  Toast.makeText(this, "Error fatal del menu", Toast.LENGTH_LONG).show();
        }
    }
    public void galeria(View view) {//Este método o que fai é lanzar a Galería do dispositivo

        //Intent intent = new Intent(Intent.ACTION_PICK, /*Acceso denegado*/
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULTADO_GALERIA);

    }

    protected void ponerFoto(String uriFoto) {
        imageView= (ImageView) findViewById(R.id.foto);
        if (uriFoto != null && !uriFoto.isEmpty() && !uriFoto.equals("null")) {
            imageView.setImageURI(Uri.parse(uriFoto));
        } else{
            imageView.setImageBitmap(null);
        }
    }

    public void tomarFoto(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        uriFoto = Uri.fromFile(new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+File.separator
                        +"img_" + (System.currentTimeMillis() / 1000) + ".jpg"));

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriFoto);
        startActivityForResult(intent, RESULTADO_FOTO);

    }

    public void eliminarFoto(View view) {
        lugar.setFoto(null);
        ponerFoto(null);
        actualizaVistas();
    }
}//fin de clase
