package davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.controller;



import java.util.ArrayList;
import java.util.List;

import com.davsoftw.creedasusprofetas.view.V_CALENDAR;
import com.davsoftw.creedasusprofetas.view.V_LECTURA_BIBLIA;
import com.davsoftw.creedasusprofetas.view.V_LECTURA_EGW;
import com.davsoftw.creedasusprofetas.view.V_PRINCIPAL;
import android.content.Context;
import android.content.Intent;

public class C_MAPEO {


    public void mapping(Context context, int opcion, int language, String date)
    {

        switch(opcion)
        {
            case 1:
                Intent intent = new Intent(context, V_LECTURA_BIBLIA.class);
                System.out.println("VALOR DE DATE EN MAPPING: " +date);
                intent.putExtra("date",String.valueOf(date));
                context.startActivity(intent);
                break;
            case 2:
                intent = new Intent(context, V_PRINCIPAL.class);
                context.startActivity(intent);
                break;
            case 3:
                intent = new Intent(context, V_LECTURA_EGW.class);
                intent.putExtra("date",String.valueOf(date));
                context.startActivity(intent);
                break;


            case 4:
                C_LOGICA oLogica = new C_LOGICA();
                oLogica.Conectar(context);

            case 5:
                intent = new Intent(context, V_CALENDAR.class);
                intent.putExtra("date",String.valueOf(date));
                context.startActivity(intent);
                break;



        }
    }

    public void mappingVerse (Context context, int opcion, String idbook, String chapter, String verse, String highlight)
    {
        C_LOGICA oLogica = new C_LOGICA();
        oLogica.updateVerse(context, idbook, chapter, verse, highlight);

    }

    public List<Object> mappingList (Context context, int opcion, int language, String date)
    {
        List<Object> Lista = new ArrayList<Object>();
        switch(opcion)
        {

            case 1:
                C_LOGICA oLogica = new C_LOGICA();
                Lista=oLogica.LecturaBiblia(context, String.valueOf(language),date);
                break;


            case 2:
                oLogica = new C_LOGICA();
                Lista = new ArrayList<Object>();
                Lista=oLogica.LecturaEGW(context, String.valueOf(language),date);
                break;
            case 3:
                oLogica = new C_LOGICA();
                Lista = new ArrayList<Object>();
                Lista=oLogica.PlanLectura(context, String.valueOf(language));
                break;

        }


        return Lista;

    }

}
