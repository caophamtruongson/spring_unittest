package spring_unittest.dao;

import java.util.List;

import lombok.Setter;

import org.springframework.jdbc.core.JdbcTemplate;

@Setter
public abstract class BaseDao<T> {

  protected JdbcTemplate jdbcTemplateObject;

  public abstract void create(T object);

  public abstract T findOne(long id);

  public abstract List<T> findAll();

  public abstract void delete(long id);

  public abstract void update(long id, T ob);
}
