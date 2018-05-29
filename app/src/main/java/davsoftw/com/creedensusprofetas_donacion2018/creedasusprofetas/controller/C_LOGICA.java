
package davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.model.M_BIBLE_READ;
import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.model.M_BOOK;
import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.model.M_VERSE;

import static com.android.volley.VolleyLog.TAG;

public class C_LOGICA {



    public JSONArray obj_reading;
    public JSONObject obj_header;
    public JSONObject obj_language;
    public Dictionary retorno = new Hashtable();
    public RequestQueue requestQueue;
    public JSONObject result;

    public interface VolleyCallback {
        void onSuccess(JSONObject result);
    }

    public void getString(final Context context, final VolleyCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(c.getTime());
        final String url = "https://davrv93.pythonanywhere.com/api/believe/verse/reading/?date=" + strDate + "&language=ES";
        // Testing out blocking
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response);

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



    class ThreadA extends AsyncTask<Void, Void, JSONObject> {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(c.getTime());

        final String url = "https://davrv93.pythonanywhere.com/api/believe/verse/reading/?date=" + strDate + "&language=ES";

        public ThreadA() {
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            RequestFuture<JSONObject> future = RequestFuture.newFuture();
            final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                    url,
                    new JSONObject(),
                    future, future);

            requestQueue.add(request);

            try {
                JSONObject response=future.get(10, TimeUnit.SECONDS);
                System.out.println("retorno"+response);
                return  response;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public JSONObject LecturaBiblia(final Context context)
    {
        final List<M_VERSE> aVerse = new ArrayList<M_VERSE>();
        requestQueue = Volley.newRequestQueue(context);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("RT", "Thread t Begins");
                ThreadA threadA = new ThreadA();
                try{
                    try {

                        try{
                            result=threadA.execute().get(10, TimeUnit.SECONDS);
                            System.out.println("EL VALOR DE RESULT" + result);
                        }
                        catch (TimeoutException e){
                            Log.e(TAG,Log.getStackTraceString(e));
                        }
                    }
                    catch (InterruptedException e){
                        Log.e(TAG,Log.getStackTraceString(e));
                    }
                }
                catch (ExecutionException e) {
                    Log.e(TAG,Log.getStackTraceString(e));
                }

            }
        });
        t.start();
        return result;
    }

    public List<Object> PlanLectura(Context context, String language)
    {
        List<M_BIBLE_READ> aBibleRead= new  ArrayList<M_BIBLE_READ>();
        List<M_BOOK> aBook = new ArrayList<M_BOOK>();
        M_BIBLE_READ oBibleRead = new M_BIBLE_READ();
        M_BOOK oBook = new M_BOOK();
        List<Object> Lista = new ArrayList<Object>();

        return Lista;
    }



    public List<Object> LecturaEGW(Context context, String language, String date)
    {
        List<Object> Lista = new ArrayList<Object>();

        return Lista;
    }
}

