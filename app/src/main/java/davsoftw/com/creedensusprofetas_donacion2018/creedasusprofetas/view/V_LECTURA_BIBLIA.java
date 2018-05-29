package davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    public JSONArray jsonArray;
    public JSONObject headerObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Asignando vista
        setContentView(R.layout.lay_v_lectura);
        oMapeo = new C_MAPEO();
        SharedPreferences prefs = getSharedPreferences("MisPreferencias",
                Context.MODE_PRIVATE);
        String language = prefs.getString("language", "");
        System.out.print("language" + language);
        Intent myIntent = getIntent(); // gets the previously created intent
        date = myIntent.getStringExtra("date");

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(c.getTime());

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        final TextView Titulo = new TextView(this);

        final String url = "https://davrv93.pythonanywhere.com/api/believe/verse/reading/?date=" + strDate + "&language=ES";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            jsonArray = response.getJSONArray("obj_reading");

                            headerObject = response.getJSONObject("obj_header");

                            int N = jsonArray.length();
                            LinearLayout myLinearLayout = (LinearLayout) findViewById(R.id.r_fila6);

                            myTextViews = new TextView[N];

                            try {
                                Titulo.setText(headerObject.getString("book_name"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Titulo.setTypeface(null, Typeface.BOLD);
                            Titulo.setGravity(Gravity.CENTER);
                            Titulo.setTextSize(24);
                            Titulo.setTextColor(Color.WHITE);
                            Titulo.setBackgroundColor(Color.parseColor("#000000"));

                            myLinearLayout.addView(Titulo);


                            for (int i = 0; i < jsonArray.length(); i++) {
                                final TextView rowTextView = new TextView(getApplicationContext());
                                JSONObject versiculo = jsonArray.getJSONObject(i);
                                rowTextView.setTextSize(24);
                                rowTextView.setText(versiculo.getString("verse") + " "
                                        + versiculo.getString("data") + '\n');
                                rowTextView.setTextColor(Color.WHITE);
                                rowTextView.setTypeface(null, Typeface.ITALIC);
                                rowTextView.setId(i + 1);
                                myLinearLayout.addView(rowTextView);
                                rowTextView.setBackgroundColor(Color.parseColor("#000000"));

                                if (versiculo.getString("highlight").compareTo("1") == 0) {
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


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });


        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);

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
