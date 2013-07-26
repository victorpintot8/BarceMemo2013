package com.viterete.memobarce2013;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        View l=findViewById(R.id.RelativeLayout1);
        TextView header=(TextView)findViewById(R.id.TVmain);
    }


	public void start(View v){
        LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View vi = inflater.inflate(R.layout.custom_selector_nivel, null);

        Button BTfacil = (Button) vi.findViewById(R.id.BTnfacil);
        Button BTmedio = (Button) vi.findViewById(R.id.BTnmedio);
        Button BTdificil = (Button) vi.findViewById(R.id.BTndificil);
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setView(vi);
        final AlertDialog alert = dialog.create();
        BTfacil.setClickable(true);
        BTfacil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f = new Intent(getApplicationContext(), ChallengeModeFacil.class);
                startActivity(f);
                alert.dismiss();
            }
        });
        BTmedio.setClickable(true);
        BTmedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(getApplicationContext(), ChallengeModeMedio.class);
                startActivity(m);
                alert.dismiss();
            }
        });
        BTdificil.setClickable(true);
        BTdificil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d = new Intent(getApplicationContext(), ChallengeModeDificil.class);
                startActivity(d);
                alert.dismiss();
            }
        });

        alert.show();
		}

    public void Epreferencias(View v){
        Intent p=new Intent(getApplicationContext(),Preferences.class);
        startActivity(p);
    }

    public void puntaje(View v){
        Intent p=new Intent(getApplicationContext(),PuntajesAltos.class);
        startActivity(p);
    }


    public void onResume() {
        super.onResume();

    }

    public void onPause() {
        super.onPause();

    }

	}
	


