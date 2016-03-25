package spring_unittest.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class CommonService {
  public static String convertTimestamp2String(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return new SimpleDateFormat(CommonConstants.FORMAT_DATE).format(timestamp);
  }

  public static Timestamp convertString2Timestamp(String d) {
    return Timestamp.valueOf(d);
  }
}
