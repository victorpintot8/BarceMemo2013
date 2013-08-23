package com.viterete.memobarce2013;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ChallengeModeDificil extends Activity {
    private static boolean isswitch;
    private boolean pisdestroyed=false;
    private BackgroundSound mBackgroundSound=new BackgroundSound();
    private long seconds=-1,minutes=0;
    private View v1;
    private Card firstCard,secondCard;
    private static Timer myTimer,myTimer2;
    private int turns,cont=0,contwinner=0,v1pos,puntaje=0,puntajef=0,cuentaregresiva=0,tiempoplayer=0,cancion=0;
    private Handler mHandler = new Handler();
    private TextView TVturns,TVpuntaje,timer;
    private List<Integer> images=new ArrayList<Integer>();
    private GridView gv;
    private GridAdapter adapter=new GridAdapter(this,20);
    private Jugador jugador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_mode_dificil);
        jugador=new Jugador(ChallengeModeDificil.this);
        timer=(TextView)findViewById(R.id.TVCMtimer);
        TVpuntaje=(TextView)findViewById(R.id.TVCMpoints);
        TVturns=(TextView)findViewById(R.id.TVCMturns);
        gv=(GridView) findViewById(R.id.GV1);
        gv.setNumColumns(4);
        if (savedInstanceState != null) {
            minutes = savedInstanceState.getLong("minutos");
            seconds = savedInstanceState.getLong("segundos");
            turns=savedInstanceState.getInt("turnos");
            puntaje=savedInstanceState.getInt("puntaje");
            images.add(savedInstanceState.getInt("imagen0"));
            images.add(savedInstanceState.getInt("imagen1"));
            images.add(savedInstanceState.getInt("imagen2"));
            images.add(savedInstanceState.getInt("imagen3"));
            images.add(savedInstanceState.getInt("imagen4"));
            images.add(savedInstanceState.getInt("imagen5"));
            images.add(savedInstanceState.getInt("imagen6"));
            images.add(savedInstanceState.getInt("imagen7"));
            images.add(savedInstanceState.getInt("imagen8"));
            images.add(savedInstanceState.getInt("imagen9"));
            images.add(savedInstanceState.getInt("imagen10"));
            images.add(savedInstanceState.getInt("imagen11"));
            images.add(savedInstanceState.getInt("imagen12"));
            images.add(savedInstanceState.getInt("imagen13"));
            images.add(savedInstanceState.getInt("imagen14"));
            images.add(savedInstanceState.getInt("imagen15"));
            images.add(savedInstanceState.getInt("imagen16"));
            images.add(savedInstanceState.getInt("imagen17"));
            images.add(savedInstanceState.getInt("imagen18"));
            images.add(savedInstanceState.getInt("imagen19"));
        } else {
            Preferences p=new Preferences();
            isswitch=p.estadoSwitch();
            cancion=p.Cancion();
            loadImages();
            GridAdapterTemp adaptert=new GridAdapterTemp(this,20,images.get(0),images.get(1),images.get(2),images.get(3),images.get(4),images.get(5),images.get(6),images.get(7),images.get(8),images.get(9),images.get(10),images.get(11),images.get(12),images.get(13),images.get(14),images.get(15),images.get(16),images.get(17),images.get(18),images.get(19));
            gv.setAdapter(adaptert);
            gv.setEnabled(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    gv.setAdapter(adapter);
                    gv.setEnabled(true);
                }
            }, 750);
        }
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(final AdapterView<?> parent, final View v, int position,
                                    long id) {
                final ImageView ImageV=(ImageView) v;
                if(cont==0){
                    v1=v;
                    v1pos=position;

                    firstCard=new Card(images.get(position));
                    ImageV.setImageResource(images.get(position));
                    cont++;
                }
                else{
                    if(v1pos==position){
                        Toast.makeText(getApplicationContext(), "La misma carta fué seleccionada", Toast.LENGTH_SHORT).show();
                        turns++;
                        TVturns.setText(String.valueOf(turns));
                    }
                    else
                    {
                        ImageV.setImageResource(images.get(position));
                        secondCard=new Card(images.get(position));
                        if(compare(firstCard.getId(),secondCard.getId())){
                            turns++;
                            TVturns.setText(String.valueOf(turns));
                            parent.setEnabled(false);
                            mHandler.postDelayed(new Runnable(){
                                public void run() {
                                    parent.setEnabled(true);
                                    v.setVisibility(View.GONE);
                                    v1.setVisibility(View.GONE);
                                }}, 300);
                            contwinner++;
                            TVpuntaje.setText(String.valueOf(puntaje=puntaje+500));
                            CheckWin(contwinner);
                        }
                        else{
                            turns++;
                            TVturns.setText(String.valueOf(turns));
                            final ImageView ImageV1=(ImageView) v1;
                            parent.setEnabled(false);
                            mHandler.postDelayed(new Runnable(){
                                public void run() {
                                    parent.setEnabled(true);
                                    ImageV.setImageResource(R.drawable.bsc14);
                                    ImageV1.setImageResource(R.drawable.bsc14);
                                }}, 300);
                        }
                        cont=0;
                    }

                }
            }

            private void CheckWin(int contwinner) {
                if(contwinner==10){
                    CalcularPuntaje();
                }
            }
        }
        );
        ResetTiempo();
    }

    private void CalcularPuntaje() {
        LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View vi = inflater.inflate(R.layout.custom_alert_dialog, null);
        TextView TVTturnos=(TextView)vi.findViewById(R.id.TVFturnos);
        TextView TVTtiempo=(TextView)vi.findViewById(R.id.TVFtiempo);
        TextView TVTpuntaje = (TextView)vi.findViewById(R.id.TVFpuntaje);
        TextView TVTbonoturnos=(TextView)vi.findViewById(R.id.TVFbonoturnos);
        TextView TVTbonotiempo=(TextView)vi.findViewById(R.id.TVFbonotiempo);
        TextView TVTpuntajetotal=(TextView)vi.findViewById(R.id.TVFpuntajetotal);
        TVTturnos.setText(String.valueOf(turns));
        TVTpuntaje.setText(String.valueOf(puntaje));
        int bonotiempo = 0,bonoturnos=0;

        switch(turns){
            case 10:bonoturnos=turns*3000;
                TVTbonoturnos.setText(String.valueOf(turns)+" x 3000");
                break;
            case 11:bonoturnos=turns*2900;
                TVTbonoturnos.setText(String.valueOf(turns)+" x 2900");
                break;
            case 12:bonoturnos=turns*2800;
                TVTbonoturnos.setText(String.valueOf(turns)+" x 2800");
                break;
            case 13:bonoturnos=turns*2700;
                TVTbonoturnos.setText(String.valueOf(turns)+" x 2700");
                break;
            case 14:bonoturnos=turns*2600;
                TVTbonoturnos.setText(String.valueOf(turns)+" x 2600");
                break;
            case 15:bonoturnos=turns*2500;
                TVTbonoturnos.setText(String.valueOf(turns)+" x 2500");
                break;
            case 16:bonoturnos=turns*2400;
                TVTbonoturnos.setText(String.valueOf(turns)+" x 2400");
                break;
            case 17:bonoturnos=turns*2300;
                TVTbonoturnos.setText(String.valueOf(turns)+" x 2300");
                break;
            case 18:bonoturnos=turns*2200;
                TVTbonoturnos.setText(String.valueOf(turns)+" x 2200");
                break;
            case 19:bonoturnos=turns*2100;
                TVTbonoturnos.setText(String.valueOf(turns)+" x 2100");
                break;
            case 20:bonoturnos=turns*2000;
                TVTbonoturnos.setText(String.valueOf(turns)+" x 2000");
                break;
            case 21:bonoturnos=turns*1900;
                TVTbonoturnos.setText(String.valueOf(turns)+" x 1900");
                break;
            case 22:bonoturnos=turns*1800;
                TVTbonoturnos.setText(String.valueOf(turns)+" x 1800");
                break;
            case 23:bonoturnos=turns*1700;
                TVTbonoturnos.setText(String.valueOf(turns)+" x 1700");
                break;
            case 24:bonoturnos=turns*1600;
                TVTbonoturnos.setText(String.valueOf(turns)+" x 1600");
                break;
            case 25:bonoturnos=turns*1500;
                TVTbonoturnos.setText(String.valueOf(turns)+" x 1500");
                break;
            default:bonoturnos=0;
                TVTbonoturnos.setText(String.valueOf(turns)+" + 0");
                break;
        }
        if(minutes==0){
            if(seconds<14 && minutes==0){
                TVTtiempo.setText("0"+String.valueOf(seconds)+" s");
                bonotiempo=8000;
                TVTbonotiempo.setText(String.valueOf(bonotiempo));
            }
            else if(seconds<16 && minutes==0){
                TVTtiempo.setText(""+String.valueOf(seconds)+" s");
                bonotiempo=7000;
                TVTbonotiempo.setText(String.valueOf(bonotiempo));
            }
            else if(seconds<18 && minutes==0){
                TVTtiempo.setText(""+String.valueOf(seconds)+" s");
                bonotiempo=6000;
                TVTbonotiempo.setText(String.valueOf(bonotiempo));
            }
            else if(seconds<20 && minutes==0){
                TVTtiempo.setText(""+String.valueOf(seconds)+" s");
                bonotiempo=5000;
                TVTbonotiempo.setText(String.valueOf(bonotiempo));
            }
            else if(seconds<22 && minutes==0){
                TVTtiempo.setText(""+String.valueOf(seconds)+" s");
                bonotiempo=4000;
                TVTbonotiempo.setText(String.valueOf(bonotiempo));
            }
            else if(seconds<24 && minutes==0){
                TVTtiempo.setText(""+String.valueOf(seconds)+" s");
                bonotiempo=3000;
                TVTbonotiempo.setText(String.valueOf(bonotiempo));
            }
            else if(seconds<26 && minutes==0){
                TVTtiempo.setText(""+String.valueOf(seconds)+" s");
                bonotiempo=2000;
                TVTbonotiempo.setText(String.valueOf(bonotiempo));
            }
            else if(seconds<28 && minutes==0){
                TVTtiempo.setText(""+String.valueOf(seconds)+" s");
                bonotiempo=1000;
                TVTbonotiempo.setText(String.valueOf(bonotiempo));
            }
            else if(seconds<30 && minutes==0){
                TVTtiempo.setText(""+String.valueOf(seconds)+" s");
                bonotiempo=900;
                TVTbonotiempo.setText(String.valueOf(bonotiempo));
            }
            else if(seconds<32 && minutes==0){
                TVTtiempo.setText(""+String.valueOf(seconds)+" s");
                bonotiempo=800;
                TVTbonotiempo.setText(String.valueOf(bonotiempo));
            }
            else if(seconds<34 && minutes==0){
                TVTtiempo.setText(""+String.valueOf(seconds)+" s");
                bonotiempo=700;
                TVTbonotiempo.setText(String.valueOf(bonotiempo));
            }
            else if(seconds<38 && minutes==0){
                TVTtiempo.setText(""+String.valueOf(seconds)+" s");
                bonotiempo=600;
                TVTbonotiempo.setText(String.valueOf(bonotiempo));
            }
            else if(seconds<40 && minutes==0){
                TVTtiempo.setText(""+String.valueOf(seconds)+" s");
                bonotiempo=550;
                TVTbonotiempo.setText(String.valueOf(bonotiempo));
            }
            else if(seconds<50 && minutes==0){
                TVTtiempo.setText(""+String.valueOf(seconds)+" s");
                bonotiempo=500;
                TVTbonotiempo.setText(String.valueOf(bonotiempo));
            }
        }
        else{
            if(seconds<10 && minutes>0){
                TVTtiempo.setText(String.valueOf(minutes)+"0"+String.valueOf(seconds)+" s");
                bonotiempo=250;
                TVTbonotiempo.setText(String.valueOf(bonotiempo));
            }
            else{
                TVTtiempo.setText(String.valueOf(minutes)+String.valueOf(seconds)+" s");
                bonotiempo=100;
                TVTbonotiempo.setText(String.valueOf(bonotiempo));
            }
        }

        puntajef=bonotiempo+bonoturnos+puntaje;
        TVTpuntajetotal.setText(String.valueOf(puntajef));
        insertarpuntaje(puntajef);
        AlertDialog.Builder dialog = new AlertDialog.Builder(ChallengeModeDificil.this);
        dialog.setView(vi);
        dialog.setNegativeButton("Comparte", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Mi puntuación en Barce Memo 2013 fue: "+String.valueOf(puntajef)+" , Quieres superarme? Bajate la aplicación aquí....");
                startActivity(Intent.createChooser(intent, "Comparte"));

            }

        });

        dialog.setNeutralButton("Replay", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ResetGame(vi);
            }

        });

        dialog.setPositiveButton("Salir", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        dialog.show();
        myTimer.cancel();
    }

    protected void TimerMethod() {
        this.runOnUiThread(Timer_Tick);

    }

    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            if(seconds<10){
                timer.setText(String.valueOf(minutes)+":0"+String.valueOf(seconds));
            }
            else{
                timer.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));
            }
        }
    };

    public void ResetGame(View v){
        myTimer.cancel();
        seconds=-1;
        minutes=0;
        turns=0;
        TVturns.setText("0");
        gv.setAdapter(adapter);
        cont=0;
        contwinner=0;
        puntaje=0;
        TVpuntaje.setText("0");
        loadImages();
        ResetTiempo();
        GridAdapterTemp adaptert=new GridAdapterTemp(this,20,images.get(0),images.get(1),images.get(2),images.get(3),images.get(4),images.get(5),images.get(6),images.get(7),images.get(8),images.get(9),images.get(10),images.get(11),images.get(12),images.get(13),images.get(14),images.get(15),images.get(16),images.get(17),images.get(18),images.get(19));
        gv.setAdapter(adaptert);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gv.setAdapter(adapter);
            }
        }, 750);
    }

    private boolean compare(int a,int b) {
        boolean check = false;
        if(a==b){
            check=true;
        }
        else{
            check=false;
        }
        return check;
    }

    private void loadImages() {
        images.clear();
        images.add(R.drawable.card1);
        images.add(R.drawable.card2);
        images.add(R.drawable.card3);
        images.add(R.drawable.card4);
        images.add(R.drawable.card5);
        images.add(R.drawable.card6);
        images.add(R.drawable.card7);
        images.add(R.drawable.card8);
        images.add(R.drawable.card9);
        images.add(R.drawable.card10);
        images.add(R.drawable.card1);
        images.add(R.drawable.card2);
        images.add(R.drawable.card3);
        images.add(R.drawable.card4);
        images.add(R.drawable.card5);
        images.add(R.drawable.card6);
        images.add(R.drawable.card7);
        images.add(R.drawable.card8);
        images.add(R.drawable.card9);
        images.add(R.drawable.card10);
        shuffle(images);
    }

    private static void swap(List<Integer> l, int i, int change) {
        int helper = l.get(i);
        l.set(i, l.get(change));
        l.set(change, helper);
    }

    public void shuffle(List<Integer> l){
        int n = l.size();
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(l, i, change);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBackgroundSound.cancel(true);
    }

    protected void onStop() {
        super.onStop();
        mBackgroundSound.cancel(true);
        myTimer.cancel();
    }

    protected void onResume() {
        super.onResume();

        if(isswitch){
            mBackgroundSound=new BackgroundSound();
            mBackgroundSound.execute(pisdestroyed,null,null);
        }
        TVpuntaje.setText(String.valueOf(puntaje));
        TVturns.setText(String.valueOf(turns));
    }
    protected void onRestart(){
        super.onRestart();
        gv.setEnabled(false);
        CuentaRegresivaToast();
    }

    protected void onStart(){
        super.onStart();
    }

    protected void onDestroy(){
        super.onDestroy();
        mBackgroundSound.cancel(true);
        images.clear();
        pisdestroyed=true;
    }

    public void ResetTiempo(){
        myTimer=new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
                seconds++;
                if(seconds>=60){
                    seconds=0;
                    minutes++;
                }
            }
        }, 0, 1000);
    }

    public void CuentaRegresivaToast(){
        myTimer2=new Timer();
        myTimer2.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                CuentaRegresiva();
                cuentaregresiva++;
            }
        },0,900);
    }
    private void CuentaRegresiva() {
        this.runOnUiThread(Timer_Regresiva);
    }

    private Runnable Timer_Regresiva = new Runnable() {
        public void run() {
            if(cuentaregresiva==1){
                final Toast toast= Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 450);
            }
            else if(cuentaregresiva==2){
                final Toast toast= Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 450);
            }
            else if(cuentaregresiva==3){
                final Toast toast= Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 450);
                cuentaregresiva=0;
                myTimer2.cancel();
                ResetTiempo();
                gv.setEnabled(true);
            }

        }
    };

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("tiempoplayer", tiempoplayer);
        savedInstanceState.putLong("minutos", minutes);
        savedInstanceState.putLong("segundos", seconds);
        savedInstanceState.putInt("turnos", turns);
        savedInstanceState.putInt("puntaje", puntaje);
        savedInstanceState.putInt("imagen0",images.get(0));
        savedInstanceState.putInt("imagen1",images.get(1));
        savedInstanceState.putInt("imagen2",images.get(2));
        savedInstanceState.putInt("imagen3",images.get(3));
        savedInstanceState.putInt("imagen4",images.get(4));
        savedInstanceState.putInt("imagen5",images.get(5));
        savedInstanceState.putInt("imagen6",images.get(6));
        savedInstanceState.putInt("imagen7",images.get(7));
        savedInstanceState.putInt("imagen8",images.get(8));
        savedInstanceState.putInt("imagen9",images.get(9));
        savedInstanceState.putInt("imagen10",images.get(10));
        savedInstanceState.putInt("imagen11",images.get(11));
        savedInstanceState.putInt("imagen12",images.get(12));
        savedInstanceState.putInt("imagen13",images.get(13));
        savedInstanceState.putInt("imagen14",images.get(14));
        savedInstanceState.putInt("imagen15",images.get(15));
        savedInstanceState.putInt("imagen16",images.get(16));
        savedInstanceState.putInt("imagen17",images.get(17));
        savedInstanceState.putInt("imagen18",images.get(18));
        savedInstanceState.putInt("imagen19",images.get(19));
        super.onSaveInstanceState(savedInstanceState);
    }

    public void insertarpuntaje(int puntajef1){
        try {
            jugador.abrir();
            jugador.crearRegistro(puntajef1,getUsername());
            jugador.cerrar();
        } catch (Exception e) {

        }
    }

    public String getUsername(){
        AccountManager manager = AccountManager.get(this);
        Account[] accounts = manager.getAccountsByType("com.google");
        List<String> possibleEmails = new LinkedList<String>();

        for (Account account : accounts) {
            possibleEmails.add(account.name);
        }

        if(!possibleEmails.isEmpty() && possibleEmails.get(0) != null){
            String email = possibleEmails.get(0);
            String[] parts = email.split("@");
            if(parts.length > 0 && parts[0] != null)
                return parts[0];
            else
                return "user";
        }else
            return "user";
    }

    public class BackgroundSound extends AsyncTask<Boolean, Void, Void> {
        MediaPlayer player;
        @Override
        protected Void doInBackground(Boolean... booleans) {
            if(cancion==0){
                player= MediaPlayer.create(ChallengeModeDificil.this, R.raw.unsoloidolo);
            }
            else if(cancion==1){
                player= MediaPlayer.create(ChallengeModeDificil.this, R.raw.sisi);
            }
            else if(cancion==2){
                player= MediaPlayer.create(ChallengeModeDificil.this, R.raw.amarilloesmicolor);
            }
            player.setLooping(true); // Set looping
            player.setVolume(100, 100);
            if(Boolean.TRUE){
                player.seekTo(tiempoplayer);
                player.start();
            }
            else{
                player.start();
            }

            while (!isCancelled()) {

            }
            tiempoplayer=player.getCurrentPosition();
            player.stop();
            player.release();
            return null;
        }
        protected void onPostExecute(Void... params) {

        }

        protected void seek(int i){
            player.seekTo(i);
        }
    }
}
