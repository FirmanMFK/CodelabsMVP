package id.gits.codelabsmvp.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Firman on 10/6/2017.
 */

public class Util {

    public static String formatDate(Date date) {
        final String DATE_FORMAT = "dd-MM-yyyy";

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String hireDate = dateFormat.format(date);
        return hireDate;
    }

}
