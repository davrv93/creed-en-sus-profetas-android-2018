package davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.controller;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SV_VERSE {



    private String TAG = "SO_TEST";
    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String strDate = sdf.format(c.getTime());
    private String url = "https://davrv93.pythonanywhere.com/api/believe/verse/reading/?date=" + strDate + "&language=ES";


    public JSONObject getVerse(Context ctx){
        JSONObject response = null;
        RequestQueue requestQueue = Volley.newRequestQueue(ctx);


        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(url,null,future,future);
        requestQueue.add(request);


        try {
            response = future.get(3, TimeUnit.SECONDS); // Blocks for at most 10 seconds.
        } catch (InterruptedException e) {
            Log.d(TAG,"interrupted");
        } catch (ExecutionException e) {
            Log.d(TAG,"execution");
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        Log.d(TAG,response.toString());

        return response;
    }

}
