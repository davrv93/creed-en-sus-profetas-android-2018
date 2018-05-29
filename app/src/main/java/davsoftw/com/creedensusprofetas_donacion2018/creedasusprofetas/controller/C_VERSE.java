package davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.controller;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.ArrayList;

import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.model.M_VERSE;

public class C_VERSE extends AsyncTask<String,String, List> {

    private Context ctx;
    private SV_VERSE svVerse = new SV_VERSE();
    private JSONObject verse= new JSONObject();
    private JSONArray obj_reading= new JSONArray();
    private JSONObject obj_header= new JSONObject();
    private JSONObject obj_language= new JSONObject();
    private List<M_VERSE> aVerse;

    public C_VERSE(Context hostContext)
    {
        ctx = hostContext;
    }

    @Override
    protected List<M_VERSE> doInBackground(String... params) {

        // Method runs on a separate thread, make all the network calls you need

        JSONObject resultado=svVerse.getVerse(ctx);
        try {
            obj_reading= resultado.getJSONArray("obj_reading");
            aVerse = new ArrayList<>();
            for (int i=0; i<obj_reading.length();i++){
                M_VERSE oVerse = new M_VERSE();
                JSONObject versiculo=obj_reading.getJSONObject(i);
                oVerse.setId(versiculo.getString("id"));
                oVerse.setHighlight(versiculo.getString("highlight"));
                oVerse.setData(versiculo.getString("data"));
                oVerse.setLanguage(versiculo.getString("language"));
                oVerse.setBook(versiculo.getString("book"));
                oVerse.setVerse(versiculo.getString("verse"));
                oVerse.setChapter(versiculo.getString("chapter"));
                aVerse.add(oVerse);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return aVerse;
    }

    @Override
    protected void onPostExecute(List result)
    {
        // runs on the UI thread
        // do something with the result
    }
}
