package spring_unittest.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import spring_unittest.entity.Message;
import spring_unittest.mapper.MessageMapper;

@RunWith(MockitoJUnitRunner.class)
public class MessageJdbcDaoTest {

  @InjectMocks
  MessageJdbcDao messageJdbcDao;

  @Mock
  JdbcTemplate jdbcTemplateObject;

  @Mock
  MessageMapper messageMapper;

  Message message;

  long id;

  public MessageJdbcDaoTest() {
    MockitoAnnotations.initMocks(this.getClass());
    message = new Message("Message Name", "Message Message");
    id = 1;
    message.setId(id);
  }

  @Test
  public void testCreate() {

    String SQL =
        "insert into messages (name, message, views, created_at) values (?, ?, 0, UNIX_TIMESTAMP())";

    messageJdbcDao.create(message);

    verify(jdbcTemplateObject).update(SQL, message.getName(), message.getMessage());
  }

  @Test
  public void testfindOne() {
    List<Message> messages = new ArrayList<Message>();
    messages.add(message);
    String SQL = "select * from messages where id = ? and deleted_at IS NULL limit 1";

    when(jdbcTemplateObject.query(SQL, new Object[] {id}, messageMapper)).thenReturn(messages);

    assertEquals(messageJdbcDao.findOne(1), message);
    verify(jdbcTemplateObject).query(SQL, new Object[] {id}, messageMapper);
  }

  @Test
  public void testfindOneWithIdNotExists() {
    List<Message> messages = new ArrayList<Message>();
    String SQL = "select * from messages where id = ? and deleted_at IS NULL limit 1";

    when(jdbcTemplateObject.query(SQL, new Object[] {id}, messageMapper)).thenReturn(messages);

    assertEquals(messageJdbcDao.findOne(1).getId(), 0);
    verify(jdbcTemplateObject).query(SQL, new Object[] {id}, messageMapper);
  }

  @Test
  public void testFindAll() {
    String SQL = "select * from messages where deleted_at IS NULL order by id desc";
    List<Message> messages = new ArrayList<Message>();
    messages.add(new Message("Message Name 1", "Message Message 1"));
    messages.add(new Message("Message Name 2", "Message Message 2"));

    when(jdbcTemplateObject.query(SQL, messageMapper)).thenReturn(messages);

    assertEquals(messageJdbcDao.findAll(), messages);
    verify(jdbcTemplateObject).query(SQL, messageMapper);
  }

  @Test
  public void testDelete() {
    String SQL = "update messages set deleted_at = UNIX_TIMESTAMP() where id = ?";

    messageJdbcDao.delete(id);

    verify(jdbcTemplateObject).update(SQL, id);
  }

  @Test
  public void testUpdate() {
    String SQL =
        "update messages " + " set name = ? " + ", message = ? "
            + ", updated_at = UNIX_TIMESTAMP() " + " where id = ? and deleted_at IS NULL";

    messageJdbcDao.update(id, message);

    verify(jdbcTemplateObject).update(SQL, message.getName(), message.getMessage(), id);
  }

  @Test
  public void testUpdateViews() {
    message.setViews(1);
    String SQL =
        "update messages " + " set views = ? " + ", updated_at = UNIX_TIMESTAMP() "
            + " where id = ? and deleted_at IS NULL";

    messageJdbcDao.updateViews(message);

    verify(jdbcTemplateObject).update(SQL, message.getViews() + 1, message.getId());
  }

  @Test
  public void testGetHotMessage() {
    String SQL = "select * from messages where deleted_at IS NULL order by views desc limit 3";
    List<Message> messages = new ArrayList<Message>();
    messages.add(new Message("Message Name 1", "Message Message 1"));
    messages.add(new Message("Message Name 2", "Message Message 2"));

    when(jdbcTemplateObject.query(SQL, messageMapper)).thenReturn(messages);

    assertEquals(messageJdbcDao.getHotMessage(), messages);
    verify(jdbcTemplateObject).query(SQL, messageMapper);
  }
}
