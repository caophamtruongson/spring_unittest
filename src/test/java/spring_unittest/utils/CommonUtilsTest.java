package spring_unittest.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommonUtilsTest extends CommonUtils {

  @Test
  public void testConvertTimestamp2StringNotNull() {

    Date today = new Date();
    Timestamp ts1 = new java.sql.Timestamp(today.getTime());
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.FORMAT_DATE);

    assertEquals(simpleDateFormat.format(today), CommonUtils.convertTimestamp2String(ts1));
  }

  @Test
  public void testConvertTimestamp2StringISNULL() {
    assertNull(CommonUtils.convertTimestamp2String(null));
  }
}
