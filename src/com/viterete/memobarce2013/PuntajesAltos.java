package com.viterete.memobarce2013;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Victor on 7/06/13.
 */
public class PuntajesAltos extends Activity {
    private ViewGroup LYyo,LYtodos,LYnum,LYnombre,LYpuntaje;
    private TextView num,nombre,puntaje;
    private ArrayList values=new ArrayList();
    private Jugador jugador=new Jugador(PuntajesAltos.this);
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntajes_altos);

        LYyo=(ViewGroup)findViewById(R.id.LYyo);
        LYtodos=(ViewGroup)findViewById(R.id.LYtodos);
        LYnum=(ViewGroup)findViewById(R.id.LYnum);
        LYnombre=(ViewGroup)findViewById(R.id.LYnombre);
        LYpuntaje=(ViewGroup)findViewById(R.id.LYpuntaje);

        try{

            jugador.abrir();
            values=jugador.getDatos();
            Iterator it = values.iterator();
            int i=0;
            while(it.hasNext())
            {
                i++;
                num=new TextView(this);
                nombre=new TextView(this);
                puntaje=new TextView(this);
                if(PuntajesAltos.this.getResources().getDisplayMetrics().densityDpi== DisplayMetrics.DENSITY_MEDIUM){
                    num.setTextSize(25);
                    nombre.setTextSize(25);
                    puntaje.setTextSize(25);
                }
                else if(PuntajesAltos.this.getResources().getDisplayMetrics().densityDpi== DisplayMetrics.DENSITY_HIGH){
                    num.setTextSize(27);
                    nombre.setTextSize(27);
                    puntaje.setTextSize(27);
                }
                else{
                    num.setTextSize(30);
                    nombre.setTextSize(30);
                    puntaje.setTextSize(30);
                }
                String temp=(String)it.next();
                num.setText(String.valueOf(i));
                nombre.setText((String)(it.next()));
                puntaje.setText(String.valueOf(it.next()));
                LYnum.addView(num);
                LYnombre.addView(nombre);
                LYpuntaje.addView(puntaje);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void mostrarPuntajeYo(View v){
        LYyo.setVisibility(View.VISIBLE);
        LYtodos.setVisibility(View.GONE);
    }

    public void mostrarPuntajeTodos(View v){
        TextView title = new TextView(PuntajesAltos.this);
        title.setText("Advertencia");
        title.setTextSize(20);
        title.setGravity(Gravity.CENTER);
        LYtodos.setVisibility(View.VISIBLE);
        LYyo.setVisibility(View.GONE);
        AlertDialog.Builder builder = new AlertDialog.Builder(PuntajesAltos.this);
        builder.setCustomTitle(title);
        builder.setMessage("Esta función no está disponible por el momento...");
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();

            }

        });
        AlertDialog alert = builder.show();
    }

    protected void onDestroy(){
        super.onDestroy();
    }
}