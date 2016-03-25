package spring_unittest.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring_unittest.entity.Message;
import spring_unittest.service.CommonService;

public class MessageMapper implements RowMapper<Message> {
  public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
    Message message = new Message();
    message.setId(rs.getInt("id"));
    message.setName(rs.getString("name"));
    message.setMessage(rs.getString("message"));
    message.setViews(rs.getInt("views"));
    message.setCreatedAt(CommonService.convertTimestamp2String(rs.getTimestamp("created_at")));
    message.setUpdatedAt(CommonService.convertTimestamp2String(rs.getTimestamp("updated_at")));
    message.setDeletedAt(CommonService.convertTimestamp2String(rs.getTimestamp("deleted_at")));
    return message;
  }
}
