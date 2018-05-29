
package davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.controller;
import  davsoftw.com.creedensusprofetas_donacion2018.tools.DataBaseHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.model.M_BIBLE_READ;
import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.model.M_BOOK;
import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.model.M_BOOK_EGW;
import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.model.M_BOOK_READ_EGW;
import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.model.M_CHAPTER_EGW;
import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.model.M_READING_PLAN;
import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.model.M_VERSE;

public class C_LOGICA {





    public void updateVerse(Context context, String idbook, String chapter, String verse, String highlight)
    {
        /*
        String Query= "UPDATE verse SET highlight='"+highlight+"' where idbook="+idbook+" and chapter="+chapter+" and verse="+verse;
        System.out.println(Query);
        DataBaseHelper DbHelper = Conectar(context);
        SQLiteDatabase db = DbHelper.getWritableDatabase();
        db.execSQL(Query);*/
    }

    public List<Object> LecturaBiblia(Context context, String language, String date)
    {
        List<Object> Lista = new ArrayList<Object>();
        /*List<M_BIBLE_READ> aBibleRead= new  ArrayList<M_BIBLE_READ>();
        //List<C_MAPEO> oMapeo = new ArrayList<C_MAPEO>();
        List<M_BOOK> aBook = new ArrayList<M_BOOK>();
        List<M_VERSE> aVerse = new ArrayList<M_VERSE>();
        date=date.trim();
        M_BIBLE_READ oBibleRead = new M_BIBLE_READ();
        M_BOOK oBook = new M_BOOK();
        M_VERSE oVerse = new M_VERSE();

        List<Object> Lista = new ArrayList<Object>();
        Cursor cursor;
        if(date.compareTo("")==0)
        {
            date="now";
            cursor=resultado(context, "SELECT l.idbook , l.name , l.abrev , v.idbook, v.chapter, v.verse, v.text, v.language, v.highlight FROM book l , verse v , bible_read br WHERE date('"+date+"', 'localtime') = br.START_DATE AND l.idbook = v.idbook AND l.idbook = br.idbook AND v.chapter = br.start_chapter and v.language=l.language and l.language="+language);
        }
        else
        {
            cursor=resultado(context, "SELECT l.idbook , l.name , l.abrev , v.idbook, v.chapter, v.verse, v.text, v.language, v.highlight FROM book l , verse v , bible_read br WHERE '"+date+"'" +
                    " = br.START_DATE AND l.idbook = v.idbook AND l.idbook = br.idbook AND v.chapter = br.start_chapter and v.language=l.language and l.language="+language);
        }
        System.out.println("VALOR DE DATE EN LOGICA: " +date);


        //Cursor cursor=resultado(context, "SELECT l.idbook , l.name , l.abrev , v.idbook, v.chapter, v.verse, v.text, v.language, v.highlight FROM book l , verse v , bible_read br WHERE date('"+date+"', 'localtime') = br.START_DATE AND l.idbook = v.idbook AND l.idbook = br.idbook AND v.chapter = br.start_chapter and v.language=l.language and l.language="+language);

        int filas= cursor.getCount();
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya m�s registros
            do {

                oBibleRead.setIDLIBRO(cursor.getString(0));
                oBook.setIdbook(cursor.getString(0));
                oBook.setName(cursor.getString(1));
                oBook.setAbrev(cursor.getString(2));
                oVerse.setChapter(cursor.getString(4));
                oVerse.setVerse(cursor.getString(5));
                oVerse.setText(cursor.getString(6));
                oVerse.setLanguage(cursor.getString(7));
                oVerse.setHighlight(cursor.getString(8));
                aBibleRead.add(oBibleRead);
                aBook.add(oBook);
                aVerse.add(oVerse);

                oBibleRead = new M_BIBLE_READ();
                oBook = new M_BOOK();
                oVerse = new M_VERSE();

            } while(cursor.moveToNext());
        }

        Lista.add(aBibleRead);
//		Lista.add(oMapeo);
        Lista.add(aBook);
        Lista.add(aVerse);
*/
        return Lista;
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
