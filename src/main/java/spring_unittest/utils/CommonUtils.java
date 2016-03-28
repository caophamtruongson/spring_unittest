package spring_unittest.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CommonUtils {

  public static String convertTimestamp2String(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return new SimpleDateFormat(Constants.FORMAT_DATE).format(timestamp);
  }
}
