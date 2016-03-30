package spring_unittest.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ContantsTest extends Constants {

  @Test
  public void testFormatDate() {
    assertEquals(Constants.FORMAT_DATE, "yyyy MM dd hh:mm:ss");
  }
}
