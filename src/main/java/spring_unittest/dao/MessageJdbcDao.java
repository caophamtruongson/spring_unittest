package spring_unittest.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import spring_unittest.entity.Message;
import spring_unittest.mapper.MessageMapper;


public class MessageJdbcDao extends BaseDao<Message> {

  @Autowired
  private MessageMapper messageMapper;

  @Override
  public void create(Message object) {
    String SQL =
        "insert into messages (name, message, views, created_at) values (?, ?, 0, UNIX_TIMESTAMP())";

    jdbcTemplateObject.update(SQL, object.getName(), object.getMessage());
  }

  @Override
  public Message findOne(long id) {
    String SQL = "select * from messages where id = ? and deleted_at IS NULL limit 1";

    List<Message> messages = jdbcTemplateObject.query(SQL, new Object[] {id}, messageMapper);

    return (messages.size() > 0) ? messages.get(0) : new Message();
  }

  @Override
  public List<Message> findAll() {
    String SQL = "select * from messages where deleted_at IS NULL order by id desc";

    List<Message> messages = jdbcTemplateObject.query(SQL, messageMapper);
    return messages;
  }

  @Override
  public void delete(long id) {
    String SQL = "update messages set deleted_at = UNIX_TIMESTAMP() where id = ?";
    jdbcTemplateObject.update(SQL, id);
  }

  @Override
  public void update(long id, Message message) {
    String SQL =
        "update messages " + " set name = ? " + ", message = ? "
            + ", updated_at = UNIX_TIMESTAMP() " + " where id = ? and deleted_at IS NULL";
    jdbcTemplateObject.update(SQL, message.getName(), message.getMessage(), id);
  }

  public void updateViews(Message message) {
    long count = message.getViews() + 1;
    String SQL =
        "update messages " + " set views = ? " + ", updated_at = UNIX_TIMESTAMP() "
            + " where id = ? and deleted_at IS NULL";
    jdbcTemplateObject.update(SQL, count, message.getId());
  }

  public List<Message> getHotMessage() {
    String SQL = "select * from messages where deleted_at IS NULL order by views desc limit 3";

    List<Message> messages = jdbcTemplateObject.query(SQL, messageMapper);
    return messages;
  }
}
