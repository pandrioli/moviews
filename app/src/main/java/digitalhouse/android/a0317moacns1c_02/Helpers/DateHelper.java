package digitalhouse.android.a0317moacns1c_02.Helpers;
import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {

    //TODO: implementar locale

    public static final String FORMAT_ARG = "dd-MM-yyyy";
    public static final String FORMAT_API = "yyyy-MM-dd";

    //parsear string
    public static Date parse(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            Log.d("Error", e.getMessage());
            return null;
        }
    }

    //form
    public static String format(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    // obtener edad a partir de fecha de nacimiento
    public static Integer age(Date birthday) {
        long startTime = birthday.getTime();
        long endTime = now().getTime();
        long diffTime = endTime - startTime;
        long diffDays = diffTime / (1000 * 60 * 60 * 24);
        return (int)diffDays/360;
    }

    public static Date now() {
        return Calendar.getInstance().getTime();
    }

}