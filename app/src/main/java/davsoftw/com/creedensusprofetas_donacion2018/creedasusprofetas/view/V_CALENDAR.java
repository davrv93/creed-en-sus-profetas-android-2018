package davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import davsoftw.com.creedensusprofetas_donacion2018.R;
import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.controller.C_MAPEO;
import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.model.M_BIBLE_READ;
import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.model.M_BOOK;
import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.model.M_VERSE;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

public class V_CALENDAR extends Activity {

	public C_MAPEO oMapeo;
	public int language;
	public Long date;
	public CalendarView calendar;
	public Intent myIntent;
	public TextView Titulo;
	public String selectedDate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		oMapeo = new C_MAPEO();
		
		setContentView(R.layout.lay_v_calendar);
		// Calendario
		calendar = (CalendarView) findViewById(R.id.calendarView);
		
		//Capturando TextView del Titulo
		Titulo = (TextView) findViewById(R.id.date_title);
		
		// Guardando la fecha

		date = calendar.getDate();
		// Ocultando la semana
		calendar.setShowWeekNumber(false);
		// Mostrando un mensaje al seleccionar d�a

		calendar.setOnDateChangeListener(new OnDateChangeListener() {
			@Override
			public void onSelectedDayChange(CalendarView view, int year,
					int month, int dayOfMonth) {

				if (calendar.getDate() != date) {
					date = calendar.getDate();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					selectedDate = sdf.format(new Date(calendar.getDate()));
					setTitle(selectedDate);
				}
			}
		});
		
		//Agregando evento onclick al Layout
		LinearLayout layout = (LinearLayout) findViewById(R.id.LinearLayout01);
		layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(selectedDate==null)
            	{
            		selectedDate="";
            	}            	
            	oMapeo.mapping(V_CALENDAR.this, 1, language,selectedDate);
            }
        });		

		SharedPreferences prefs = getSharedPreferences("MisPreferencias",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		String lang = Locale.getDefault().getLanguage();
		language = 2;
		if (lang.compareTo("es") == 0) {
			editor.putString("language", "1");
			language = 1;
		}
		if (lang.compareTo("en") == 0) {
			editor.putString("language", "2");
			language = 2;
		}
		editor.commit();				
		setTitle("");
		
		
	}
	
	@SuppressWarnings("unchecked")
	private void setTitle(String today)
	{
		//String today = myIntent.getStringExtra("date");		


	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
