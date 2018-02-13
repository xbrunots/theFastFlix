package com.devbruno.fastshop.infraestruture;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by bsilvabr on 11/02/2018.
 */

public class DateUtils {
    /**
     * @param source {string} data a converter no formato EN (1995-12-31)
     */
    public static String convertDateENtoBR(String source) {
        String[] sourceSplit = source.split("-");
        int ano = Integer.parseInt(sourceSplit[0]);
        int mes = Integer.parseInt(sourceSplit[1]);
        int dia = Integer.parseInt(sourceSplit[2]);

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(ano, mes - 1, dia);
        Date data1 = calendar.getTime();
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/20yy");
        String dayFormatted = myFormat.format(data1);
        return dayFormatted;
    }

}
