package spring_unittest.dao;

import java.util.List;

import javax.sql.DataSource;

public interface BaseDao<T> {

  public void setDataSource(DataSource ds);

  public void create(T object);

  public T findOne(long id);

  public List<T> findAll();

  public void delete(long id);

  public void update(long id, T ob);
}
