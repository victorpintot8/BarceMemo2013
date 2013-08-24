package com.viterete.memobarce2013;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by viterete8 on 20/07/13.
 */
public class Jugador {

    public static final String ID_FILA="_id";
    public static final String ID_NOMBRE="nombre_jugador";
    public static final String ID_PUNTAJE="puntaje_jugador";

    private static final String N_BD="Puntajes Altos";
    public static final String N_TABLA="Tabla_Jugador";
    public static final int VERSION_BD=1;

    private Helper mHelper;
    private final Context mContexto;
    private SQLiteDatabase mBase;
    private ArrayList data=new ArrayList();

    public Jugador(Context c1){
        this.mContexto=c1;
    }

    public long crearRegistro(int puntajef, String username) {
        ContentValues cv=new ContentValues();
        cv.put(ID_NOMBRE,username);
        cv.put(ID_PUNTAJE,puntajef);
        return mBase.insert(N_TABLA,null,cv);
    }

    public ArrayList getDatos() {

        String [] columnas=new String[]{ID_FILA,ID_NOMBRE,ID_PUNTAJE};
        Cursor c=mBase.query(N_TABLA,columnas,null,null,null,null,ID_PUNTAJE + " DESC LIMIT 10");


        int iFila=c.getColumnIndex(ID_FILA);
        int iNombre=c.getColumnIndex(ID_NOMBRE);
        int iPuntaje=c.getColumnIndex(ID_PUNTAJE);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            data.add(c.getString(iFila));
            data.add(c.getString(iNombre));
            data.add(c.getInt(iPuntaje));
        }
        return data;
    }

    private static class Helper extends SQLiteOpenHelper{

        public Helper(Context context){
            super(context,N_BD,null,VERSION_BD);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE " + N_TABLA + "(" + ID_FILA + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ID_NOMBRE + " TEXT ," + ID_PUNTAJE + " INTEGER);");

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + N_TABLA);
            onCreate(sqLiteDatabase);

        }
    }

    public Jugador abrir() throws Exception{
        mHelper=new Helper(mContexto);
        mBase=mHelper.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        mHelper.close();
    }

}
