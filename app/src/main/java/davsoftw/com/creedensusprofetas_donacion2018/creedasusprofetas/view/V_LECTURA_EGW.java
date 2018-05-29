package davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.view;
//import android.support.v7.app.ActionBarActivity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.ROUTER;

import davsoftw.com.creedensusprofetas_donacion2018.R;

public class V_LECTURA_EGW extends Activity {

	public ROUTER oMapeo;
	public String date;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.lay_v_lectura);
      oMapeo= new ROUTER();
      SharedPreferences prefs = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
      String language=prefs.getString("language","");
      //
      Intent myIntent = getIntent(); // gets the previously created intent
      date = myIntent.getStringExtra("date");
      
      System.out.println("DATE: "+date);
      
      LinearLayout myLinearLayout = (LinearLayout) findViewById(R.id.r_fila6);
      /*List<Object> Lista =oMapeo.mappingList(this, 2, Integer.valueOf(language),date);
	  List<M_BOOK_EGW> aBookEgw = new ArrayList<M_BOOK_EGW>();
	  List<M_BOOK_READ_EGW> aBookReadEgw = new ArrayList<M_BOOK_READ_EGW>();
	  List<M_CHAPTER_EGW> aChapter = new ArrayList<M_CHAPTER_EGW>();

	  aBookEgw= (List<M_BOOK_EGW>) Lista.get(0);
	  aChapter= (List<M_CHAPTER_EGW>) Lista.get(1);
	  aBookReadEgw= (List<M_BOOK_READ_EGW>) Lista.get(2);
		
      int N = aBookReadEgw.size(); 
      TextView[] myTextViews = new TextView[N];
      final TextView Titulo = new TextView(this);
      Titulo.setText(aBookEgw.get(0).getBOOK_NAME()+" " +aChapter.get(0).getCHAPTER());
      Titulo.setTypeface(null, Typeface.BOLD);
      Titulo.setGravity(Gravity.CENTER);
      Titulo.setTextSize(24);
      Titulo.setTextColor(Color.WHITE);

      myLinearLayout.addView(Titulo);      
      
      for (int i = 0; i < N; i++) {	          
      final TextView rowTextView = new TextView(this);
      rowTextView.setTextSize(24);
      rowTextView.setText(aBookReadEgw.get(i).getCONTENT()+'\n');    
      rowTextView.setTextColor(Color.WHITE);
      rowTextView.setTypeface(null, Typeface.ITALIC);
      myLinearLayout.addView(rowTextView);      
      myTextViews[i] = rowTextView;
      }
      */
      
      
      
  }


  public void onClick(View v) {
  	  switch(v.getId()){
  	    case R.id.btnVolver1:
  	    	
  	    	finish();
  	    	break;
  	  
  	}}
  
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
