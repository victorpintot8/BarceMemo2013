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
    private ViewGroup LYyo,LYnum,LYnombre,LYpuntaje;
    private TextView num,nombre,puntaje;
    private ArrayList values=new ArrayList();
    private Jugador jugador=new Jugador(PuntajesAltos.this);
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntajes_altos);
        LYyo=(ViewGroup)findViewById(R.id.LYyo);
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
                if((this.getResources().getDisplayMetrics().widthPixels/(int)this.getResources().getDisplayMetrics().density)>=720){
                    num.setTextSize(35);
                    nombre.setTextSize(35);
                    puntaje.setTextSize(35);
                }
                else if((this.getResources().getDisplayMetrics().widthPixels/(int)this.getResources().getDisplayMetrics().density)>=600){
                    num.setTextSize(45);
                    nombre.setTextSize(45);
                    puntaje.setTextSize(45);
                }
                else{
                    if(PuntajesAltos.this.getResources().getDisplayMetrics().densityDpi== DisplayMetrics.DENSITY_MEDIUM){
                        num.setTextSize(20);
                        nombre.setTextSize(20);
                        puntaje.setTextSize(20);
                    }
                    else if(PuntajesAltos.this.getResources().getDisplayMetrics().densityDpi== DisplayMetrics.DENSITY_HIGH){
                        num.setTextSize(27);
                        nombre.setTextSize(27);
                        puntaje.setTextSize(27);
                    }
                    else if(PuntajesAltos.this.getResources().getDisplayMetrics().densityDpi== DisplayMetrics.DENSITY_XHIGH){
                        num.setTextSize(30);
                        nombre.setTextSize(30);
                        puntaje.setTextSize(30);
                    }
                    else if(PuntajesAltos.this.getResources().getDisplayMetrics().densityDpi== DisplayMetrics.DENSITY_XXHIGH){
                        num.setTextSize(32);
                        nombre.setTextSize(32);
                        puntaje.setTextSize(32);
                    }
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

    protected void onDestroy(){
        super.onDestroy();
    }
}