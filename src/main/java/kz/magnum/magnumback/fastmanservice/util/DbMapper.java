package kz.magnum.magnumback.fastmanservice.util;

import org.springframework.util.LinkedCaseInsensitiveMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

public class DbMapper {

    private static void checkObjByKey(LinkedCaseInsensitiveMap<Object> row, String objKey) {
        if (!row.containsKey(objKey)) throw new NullPointerException("Object key not found, actual: " + objKey);
    }

    public static String objToStr(LinkedCaseInsensitiveMap<Object> row, String objKey) {
        checkObjByKey(row, objKey);
        return objToStr(row.get(objKey));
    }

    public static String objToStr(Object objStr) {
        if (objStr == null) return "";
        return objStr.toString();
    }

    public static Long objToLong(LinkedCaseInsensitiveMap<Object> row, String objKey) {
        checkObjByKey(row, objKey);
        return objToLong(row.get(objKey));
    }

    public static Long objToLong(Object longObj) {
        if (longObj == null) return null;
        return Long.parseLong(longObj.toString());
    }

    public static Integer objToInteger(LinkedCaseInsensitiveMap<Object> row, String objKey) {
        checkObjByKey(row, objKey);
        return objToInteger(row.get(objKey));
    }

    public static Integer objToInteger(Object intObj) {
        if (intObj == null) return null;
        return Integer.parseInt(intObj.toString());
    }

    public static Short objToShort(LinkedCaseInsensitiveMap<Object> row, String objKey) {
        checkObjByKey(row, objKey);
        return objToShort(row.get(objKey));
    }

    public static Short objToShort(Object intObj) {
        if (intObj == null) return null;
        return Short.parseShort(intObj.toString());
    }
    public static Byte objToByte(LinkedCaseInsensitiveMap<Object> row, String objKey) {
        checkObjByKey(row, objKey);
        return objToByte(row.get(objKey));
    }

    public static Byte objToByte(Object intObj) {
        if (intObj == null) return null;
        return Byte.parseByte(intObj.toString());
    }
    public static Date objToDate(LinkedCaseInsensitiveMap<Object> row, String objKey) {
        checkObjByKey(row, objKey);
        return objToDate(row.get(objKey));
    }

    public static Instant objToInstant(LinkedCaseInsensitiveMap<Object> row, String objKey) {
        Date date = objToDate(row, objKey);
        return date.toInstant();
    }

    public static Date objToDate(Object dateObj) {
        if (dateObj == null) return null;
        return (Date)dateObj;
        /*String dateInString = String.valueOf(dateObj);
        //System.out.println("dateObj = " + dateObj);

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return formatter.parse(dateInString);
        } catch (ParseException exp) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(dateInString);
            exp.printStackTrace();
            return date;
        }*/
    }

    public static Double objToDouble(LinkedCaseInsensitiveMap<Object> row, String objKey) {
        checkObjByKey(row, objKey);
        return objToDouble(row.get(objKey));
    }

    public static Double objToDouble(Object doubleObj) {
        if (doubleObj == null) return null;
        return Double.parseDouble(doubleObj.toString());
    }

    @SuppressWarnings("ConstantConditions")
    public static Boolean objToBoolean(LinkedCaseInsensitiveMap<Object> row, String objKey) {
        checkObjByKey(row, objKey);
        if (row.get(objKey) == null) return null;
        if (row.get(objKey).toString().equals("1")) return true;
        return row.get(objKey).toString().toLowerCase().equals("true");
    }

    public static Timestamp dateToSqlDate(Date iDate) {
        if (iDate == null) return null;
        return new Timestamp(iDate.getTime());
    }

    public static String clobToString(Clob clob) throws SQLException, IOException {
        if (clob == null) return null;
        final StringBuilder sb = new StringBuilder();
        final Reader reader = clob.getCharacterStream();
        final BufferedReader br = new BufferedReader(reader);
        int b;
        while (-1 != (b = br.read())) {
            sb.append((char) b);
        }
        br.close();
        return sb.toString();
    }
}