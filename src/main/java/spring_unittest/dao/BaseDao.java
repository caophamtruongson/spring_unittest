package spring_unittest.dao;

import java.util.List;

import lombok.Setter;

import org.springframework.jdbc.core.JdbcTemplate;

@Setter
public abstract class BaseDao<T> {

  protected JdbcTemplate jdbcTemplateObject;

  public void create(T object) {};

  public T findOne(long id) {
    return null;
  }

  public List<T> findAll() {
    return null;
  }

  public void delete(long id) {};

  public void update(long id, T ob) {};
}
