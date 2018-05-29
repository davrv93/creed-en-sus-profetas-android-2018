package davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas;

import davsoftw.com.creedensusprofetas_donacion2018.MainActivity;
import davsoftw.com.creedensusprofetas_donacion2018.creedasusprofetas.view.*;
import android.content.Context;
import android.content.Intent;


public class ROUTER {


    public void mapping(Context context, int opcion, int language, String date)
    {

        switch(opcion)
        {
            case 1:
                Intent intent = new Intent(context, V_LECTURA_BIBLIA.class);
                context.startActivity(intent);
                break;
            case 2:
                intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                break;
            case 3:
                intent = new Intent(context, V_LECTURA_EGW.class);
                intent.putExtra("date",String.valueOf(date));
                context.startActivity(intent);
                break;

            case 5:
                intent = new Intent(context, V_CALENDAR.class);
                intent.putExtra("date",String.valueOf(date));
                context.startActivity(intent);
                break;

        }
    }
}
