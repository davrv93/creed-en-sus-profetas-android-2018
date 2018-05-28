
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


    public DataBaseHelper Conectar(Context context)
    {
        DataBaseHelper myDbHelper = new DataBaseHelper(context);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        }catch(SQLException sqle){

            throw sqle;
        }
        return myDbHelper;
    }


    public Cursor resultado(Context context, String Query)
    {
        DataBaseHelper DbHelper = Conectar(context);
        SQLiteDatabase db = DbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(Query, null);
        return cursor;
    }


    public void updateVerse(Context context, String idbook, String chapter, String verse, String highlight)
    {
        String Query= "UPDATE verse SET highlight='"+highlight+"' where idbook="+idbook+" and chapter="+chapter+" and verse="+verse;
        System.out.println(Query);
        DataBaseHelper DbHelper = Conectar(context);
        SQLiteDatabase db = DbHelper.getWritableDatabase();
        db.execSQL(Query);
    }

    public List<Object> LecturaBiblia(Context context, String language, String date)
    {
        List<M_BIBLE_READ> aBibleRead= new  ArrayList<M_BIBLE_READ>();
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

        return Lista;
    }

    public List<Object> PlanLectura(Context context, String language)
    {
        List<M_BIBLE_READ> aBibleRead= new  ArrayList<M_BIBLE_READ>();
        List<M_BOOK> aBook = new ArrayList<M_BOOK>();


        M_BIBLE_READ oBibleRead = new M_BIBLE_READ();
        M_BOOK oBook = new M_BOOK();


        List<Object> Lista = new ArrayList<Object>();

        Cursor cursor=resultado(context, "SELECT DISTINCT B.name, BR.START_CHAPTER, BR.START_DATE  FROM BOOK B, BIBLE_READ BR, READ_PLAN RP WHERE B.idbook=BR.IDBOOK AND RP.ID_BOOK is not null AND BR.START_DATE  BETWEEN RP.START_DATE AND RP.END_DATE AND B.language='"+language+"' order by BR.START_DATE");

        int filas= cursor.getCount();
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya m�s registros
            do {
                oBook.setName(cursor.getString(0));
                oBibleRead.setSTART_CHAPTER(cursor.getString(1));
                oBibleRead.setSTART_DATE(cursor.getString(2));

                aBibleRead.add(oBibleRead);
                aBook.add(oBook);

                oBibleRead = new M_BIBLE_READ();
                oBook = new M_BOOK();
            } while(cursor.moveToNext());
        }
        Lista.add(aBibleRead);
        Lista.add(aBook);

        return Lista;
    }



    public List<Object> LecturaEGW(Context context, String language, String date)
    {
        List<M_BOOK_READ_EGW> aBookReadEGW= new  ArrayList<M_BOOK_READ_EGW>();
        List<M_BOOK_EGW> aBookEgw = new ArrayList<M_BOOK_EGW>();
        List<M_CHAPTER_EGW> aChapter = new ArrayList<M_CHAPTER_EGW>();

        M_BOOK_READ_EGW oBookReadEGW = new M_BOOK_READ_EGW();
        M_BOOK_EGW oBookEgw = new M_BOOK_EGW();
        M_CHAPTER_EGW oChapter = new M_CHAPTER_EGW();


        List<Object> Lista = new ArrayList<Object>();
        Cursor cursor;
        if(date.compareTo("")==0)
        {
            date="now";
            cursor=resultado(context, "SELECT R.ID_READ, B.BOOK_NAME , B.ID_BOOK, BR.CHAPTER , C.CHAPTER_TITLE , BR.CONTENT, BR.LANGUAGE, C.CHAPTER_TITLE, B.BOOK_NAME FROM READ_PLAN R INNER JOIN book_read_egw BR ON R.ID_BOOK = BR.ID_BOOK INNER JOIN BOOK_EGW B ON BR.ID_BOOK = B.ID_BOOK INNER JOIN CHAPTER_EGW C ON B.ID_BOOK = C.ID_BOOK WHERE (date('"+date+"', 'localtime') BETWEEN DATE(R.START_DATE) AND DATE(R.END_DATE)  ) AND BR.CHAPTER = C.CHAPTER AND BR.CHAPTER BETWEEN R.START_CHAPTER AND R.END_CHAPTER and BR.LANGUAGE=B.ID_LANGUAGE AND B.ID_LANGUAGE=C.IDLANGUAGE and BR.language="+language);
        }
        else
        {
            cursor=resultado(context, "SELECT R.ID_READ, B.BOOK_NAME , B.ID_BOOK, BR.CHAPTER , C.CHAPTER_TITLE , BR.CONTENT, BR.LANGUAGE, C.CHAPTER_TITLE, B.BOOK_NAME FROM READ_PLAN R INNER JOIN book_read_egw BR ON R.ID_BOOK = BR.ID_BOOK INNER JOIN BOOK_EGW B ON BR.ID_BOOK = B.ID_BOOK INNER JOIN CHAPTER_EGW C ON B.ID_BOOK = C.ID_BOOK WHERE ('"+date+"' BETWEEN DATE(R.START_DATE) AND DATE(R.END_DATE)  ) AND BR.CHAPTER = C.CHAPTER AND BR.CHAPTER BETWEEN R.START_CHAPTER AND R.END_CHAPTER and BR.LANGUAGE=B.ID_LANGUAGE AND B.ID_LANGUAGE=C.IDLANGUAGE and BR.language="+language);
        }
        int filas= cursor.getCount();
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya m�s registros
            do {

                oBookEgw.setBOOK_NAME(cursor.getString(1));
                oChapter.setCHAPTER(cursor.getString(3));
                oChapter.setCHAPTER_TITLE(cursor.getString(4));
                oBookReadEGW.setCONTENT(cursor.getString(5));

                aBookEgw.add(oBookEgw);
                aChapter.add(oChapter);
                aBookReadEGW.add(oBookReadEGW);

                oBookEgw = new M_BOOK_EGW();
                oChapter = new M_CHAPTER_EGW();
                oBookReadEGW = new M_BOOK_READ_EGW();

            } while(cursor.moveToNext());
        }

        Lista.add(aBookEgw);
//		Lista.add(oMapeo);
        Lista.add(aChapter);
        Lista.add(aBookReadEGW);

        return Lista;
    }
}
