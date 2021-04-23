package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Zhang zq
 * @Date 2021/4/23 16:54
 * @Description
 */
public class TimeCastHandler {

    /**
     * 2020-04-23T03:39:51.294Z  to   2020-04-23 03:39:51
     * @param oldTimeStr
     * @return
     */
    public static String strCastdateTime(String oldTimeStr){
        Date date = null;
        String replace = oldTimeStr.replace("T", " ");
        String split = replace.substring(0,replace.indexOf("."));
        System.out.println("处理后的oldTimeStr = " + split);
  /*      try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(split);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("strCastdateTime转换异常...");
        }
        return date;
   */
        return split;


    }

}
