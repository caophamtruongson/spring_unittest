package spring_unittest.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import spring_unittest.entity.Message;
import spring_unittest.utils.CommonUtils;
import spring_unittest.utils.Constants;

@RunWith(MockitoJUnitRunner.class)
public class MessageMapperTest {

  @InjectMocks
  MessageMapper messageMapper;

  public MessageMapperTest() {
    MockitoAnnotations.initMocks(this.getClass());
  }

  @Test
  public void testMapRow() throws SQLException {

    java.util.Date today = new java.util.Date();
    java.sql.Timestamp ts1 = new java.sql.Timestamp(today.getTime());
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.FORMAT_DATE);

    ResultSet rs = Mockito.mock(ResultSet.class);

    when(rs.getInt("id")).thenReturn(1);
    when(rs.getString("name")).thenReturn("Messsage Name");
    when(rs.getString("message")).thenReturn("Messsage Message");
    when(rs.getInt("views")).thenReturn(1);
    when(rs.getTimestamp("created_at")).thenReturn(ts1);
    when(rs.getTimestamp("updated_at")).thenReturn(ts1);
    when(rs.getTimestamp("deleted_at")).thenReturn(ts1);

    Message message = messageMapper.mapRow(rs, 0);

    assertEquals(message.getId(), 1);
    assertEquals(message.getName(), "Messsage Name");
    assertEquals(message.getMessage(), "Messsage Message");
    assertEquals(message.getViews(), 1);
    assertEquals(message.getCreatedAt(), simpleDateFormat.format(ts1));
    assertEquals(message.getUpdatedAt(), simpleDateFormat.format(ts1));
    assertEquals(message.getDeletedAt(), simpleDateFormat.format(ts1));
  }
}
