package davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import davsoftw.com.creedensusprofetas_donacion2018.R;
import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.controller.C_MAPEO;
import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.controller.C_VERSE;
import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.model.M_VERSE;

public class V_LECTURA_BIBLIA extends Activity {

    public C_MAPEO oMapeo;
    public M_VERSE oVerse;
    public TextView[] myTextViews;
    public LinearLayout Layout;
    public String idbook;
    public String chapter;
    public String verse;
    public String highlight;
    public String date;
    public JSONArray jsonArray;
    public JSONObject headerObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Asignando vista
        setContentView(R.layout.lay_v_lectura);
        SharedPreferences prefs = getSharedPreferences("MisPreferencias",
                Context.MODE_PRIVATE);
        String language = prefs.getString("language", "");
        Intent myIntent = getIntent(); // gets the previously created intent


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final List<M_VERSE> aVerse = new C_VERSE(getApplicationContext()).execute().get();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int N = aVerse.size();
                            LinearLayout myLinearLayout = (LinearLayout) findViewById(R.id.r_fila6);
                            myTextViews = new TextView[N];

                            final TextView Titulo = new TextView(getApplicationContext());
                            Titulo.setText("Capitulo: "+aVerse.get(0).getChapter().toString());

                            Titulo.setTypeface(null, Typeface.BOLD);
                            Titulo.setGravity(Gravity.CENTER);
                            Titulo.setTextSize(24);
                            Titulo.setTextColor(Color.WHITE);
                            Titulo.setBackgroundColor(Color.parseColor("#000000"));

                            myLinearLayout.addView(Titulo);

                            for (int i = 0; i < aVerse.size(); i++) {
                                final TextView rowTextView = new TextView(getApplicationContext());

                                rowTextView.setTextSize(24);
                                rowTextView.setText(aVerse.get(i).getVerse() + " "
                                        + aVerse.get(i).getData() + '\n');
                                rowTextView.setTextColor(Color.WHITE);
                                rowTextView.setTypeface(null, Typeface.ITALIC);
                                rowTextView.setId(i + 1);
                                myLinearLayout.addView(rowTextView);
                                rowTextView.setBackgroundColor(Color.parseColor("#000000"));

                                if (aVerse.get(i).getHighlight().compareTo("1") == 0) {
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

                                                    } else {
                                                        rowTextView.setBackgroundColor(Color.BLUE);

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
                        }
                    });


                    System.out.println("aVErse"+aVerse.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();




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
