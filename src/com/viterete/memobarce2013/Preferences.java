package com.viterete.memobarce2013;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Preferences extends Activity {
    private Switch sw;
    private ToggleButton toggle;
    private Spinner sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        if (android.os.Build.VERSION.SDK_INT >= 14) {
            setContentView(R.layout.activity_preferences);
            sw=(Switch)findViewById(R.id.switch1);
            sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {
                    SharedPreferences preferences =getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                    if(isChecked){
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("sCancion", true);
                        editor.commit();
                    }
                    else{
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("sCancion", false);
                        editor.commit();
                    }

                }
            });
        } else {
            setContentView(R.layout.activity_preferences_toogle);
            toggle=(ToggleButton)findViewById(R.id.TG1);
            toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {
                    SharedPreferences preferences =getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                    if(isChecked){
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("sCancion", true);
                        editor.commit();
                    }
                    else{
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("sCancion", false);
                        editor.commit();
                    }

                }
            });
        }
        sp=(Spinner)findViewById(R.id.SPcancion);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.custom_spinner_cancion,R.id.TVsp,getResources().getStringArray(R.array.canciones));
        adapter.setDropDownViewResource(R.layout.custom_spinner_cancion);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,int position, long id){
                SharedPreferences preferences =getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("nCancion",position);
                editor.commit();
            }
            public void onNothingSelected(AdapterView<?> parentView){

            }
        });


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.preferences, menu);
		return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.acerca_de:
                LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View vi = inflater.inflate(R.layout.custom_acercade, null);
                AlertDialog.Builder dialog = new AlertDialog.Builder(Preferences.this);
                dialog.setView(vi);
                final AlertDialog alert = dialog.create();
                alert.show();
               break;
        }
        return true;
    }

    protected void onPause(){
        super.onPause();
    }

    protected void onStop(){
        super.onStop();

    }

    protected void onResume(){
        super.onResume();
        SharedPreferences preferences =getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        if (android.os.Build.VERSION.SDK_INT >= 14) {
            sw.setChecked(preferences.getBoolean("sCancion",false));
        } else {
            toggle.setChecked(preferences.getBoolean("sCancion",false));
        }
        sp.setSelection(preferences.getInt("nCancion",0));
    }

}
