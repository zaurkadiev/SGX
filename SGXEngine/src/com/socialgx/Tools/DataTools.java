package com.socialgx.Tools;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zaurmac on 1/22/17.
 */
public class DataTools {
    public static int objectToInt(Object param){
        return Integer.parseInt((String) param);
    }

    public static String getCurrentDate(){

        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());

        return timeStamp.toString();
    }

    public static Date getCurrentDateDate(){
        Date date = new Date();
        return date;
    }

    public static Map<String, String> splitQuery(String string) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<>();
        String query = string;
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }
}
