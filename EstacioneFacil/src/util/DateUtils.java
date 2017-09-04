package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Douglas
 */
public class DateUtils {
    
    public static String getDataHoraString(Date data) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(data);
    }
}
