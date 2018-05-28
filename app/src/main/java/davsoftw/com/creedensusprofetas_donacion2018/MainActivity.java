package davsoftw.com.creedensusprofetas_donacion2018;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.controller.C_MAPEO;

public class MainActivity extends AppCompatActivity {

    public C_MAPEO oMapeo;
    public int language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                oMapeo= new C_MAPEO();

                SharedPreferences prefs =
                        getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                String lang= Locale.getDefault().getLanguage();
                language=2;
                if(lang.compareTo("es")==0)
                {
                    editor.putString("language", "1");
                    language=1;
                }
                if(lang.compareTo("en")==0)
                {
                    editor.putString("language", "2");
                    language=2;
                }
                editor.commit();
                System.out.println(lang+ "language:"+language +"xD");




            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_biblia:
                oMapeo.mapping(this, 1, language,"");
                break;
            case R.id.btn_egw:
                oMapeo.mapping(this, 3, language,"");
                break;
            case R.id.btn_calendar:
                oMapeo.mapping(this, 5, language,"");
                break;


        }

    }
}
