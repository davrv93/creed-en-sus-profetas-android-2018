package davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.view;

//import android.support.v7.app.ActionBarActivity;
import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

import davsoftw.com.creedensusprofetas_donacion2018.R;
import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.controller.C_MAPEO;
import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.model.M_BOOK;
import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.model.M_VERSE;

public class V_LECTURA_BIBLIA extends Activity {

	public C_MAPEO oMapeo;
	public TextView[] myTextViews;
	public LinearLayout Layout;
	public String idbook;
	public String chapter;
	public String verse;
	public String highlight;
	public String date;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Asignando vista
		setContentView(R.layout.lay_v_lectura);
		oMapeo = new C_MAPEO();
		SharedPreferences prefs = getSharedPreferences("MisPreferencias",
				Context.MODE_PRIVATE);
		String language = prefs.getString("language", "");
		System.out.pritln("language"+language);
		Intent myIntent = getIntent(); // gets the previously created intent
		date = myIntent.getStringExtra("date");

		// Instantiate the RequestQueue.
		RequestQueue queue = Volley.newRequestQueue(this);
		String url ="https://davrv93.pythonanywhere.com/api/believe/verse/reading/";

		// Request a string response from the provided URL.
		StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						// Display the first 500 characters of the response string.
						mTextView.setText("Response is: "+ response.substring(0,500));
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				mTextView.setText("That didn't work!");
			}
		});

		// Add the request to the RequestQueue.
		queue.add(stringRequest);



		System.out.println("DATE: " + date);

		LinearLayout myLinearLayout = (LinearLayout) findViewById(R.id.r_fila6);
		/*
		List<Object> Lista = oMapeo.mappingList(this, 1,
				Integer.valueOf(language), date);
		List<M_VERSE> aVerse = new ArrayList<M_VERSE>();
		List<M_BOOK> aBook = new ArrayList<M_BOOK>();

		aVerse = (List<M_VERSE>) Lista.get(2);
		aBook = (List<M_BOOK>) Lista.get(1);

		int N = aVerse.size();
		myTextViews = new TextView[N];
		final TextView Titulo = new TextView(this);
		Titulo.setText(aBook.get(0).getName() + " "
				+ aVerse.get(0).getChapter());
		Titulo.setTypeface(null, Typeface.BOLD);
		Titulo.setGravity(Gravity.CENTER);
		Titulo.setTextSize(24);
		Titulo.setTextColor(Color.WHITE);
		Titulo.setBackgroundColor(Color.parseColor("#000000"));

		myLinearLayout.addView(Titulo);

		for (int i = 0; i < N; i++) {
			final TextView rowTextView = new TextView(this);
			rowTextView.setTextSize(24);
			rowTextView.setText(aVerse.get(i).getVerse() + " "
					+ aVerse.get(i).getText() + '\n');
			rowTextView.setTextColor(Color.WHITE);
			rowTextView.setTypeface(null, Typeface.ITALIC);
			rowTextView.setId(i + 1);
			myLinearLayout.addView(rowTextView);
			rowTextView.setBackgroundColor(Color.parseColor("#000000"));

			final String verse = aVerse.get(i).getVerse();
			final String idbook = aBook.get(0).getIdbook();
			final String chapter = aVerse.get(0).getChapter();
			final String highlight = aVerse.get(i).getHighlight();

			if (highlight.compareTo("1") == 0) {
				rowTextView.setBackgroundColor(Color.BLUE);
				System.out.println("blue");
			}

			rowTextView.setOnTouchListener(new OnTouchListener() {
				private GestureDetector gestureDetector = new GestureDetector(
						V_LECTURA_BIBLIA.this,
						new GestureDetector.SimpleOnGestureListener() {

							public boolean onDoubleTap(MotionEvent e) {

								int colorId = ((ColorDrawable) rowTextView
										.getBackground()).getColor();

								if (colorId == Color.BLUE) {
									rowTextView.setBackgroundColor(Color.BLACK);
									oMapeo.mappingVerse(V_LECTURA_BIBLIA.this,
											1, idbook, chapter, verse, "2");
								} else {
									rowTextView.setBackgroundColor(Color.BLUE);
									oMapeo.mappingVerse(V_LECTURA_BIBLIA.this,
											1, idbook, chapter, verse, "1");
								}

								return super.onDoubleTap(e);
							}

							@Override
							public boolean onSingleTapConfirmed(MotionEvent e) {
								// TODO Auto-generated method stub
								// rowTextView.setBackgroundColor(Color.parseColor("8258FA"));

								return super.onSingleTapConfirmed(e);
							}
						});

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					gestureDetector.onTouchEvent(event);

					return true;
				}
			});
			myTextViews[i] = rowTextView;
		}

		// ImageView im = (ImageView) findViewById(R.id.btnCheck);
		// im.setBackgroundColor(Color.parseColor("#000000"));
		*/

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnVolver1:
			finish();
			break;

		}
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
