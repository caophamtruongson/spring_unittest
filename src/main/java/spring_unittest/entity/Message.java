package spring_unittest.entity;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.NotEmpty;

@Setter
@Getter
public class Message {

  private long id;

  @NotEmpty(message = "Name is not empty.")
  private String name;

  @NotEmpty(message = "Message is not empty.")
  private String message;

  private long views;

  private String createdAt;

  private String updatedAt;

  private String deletedAt;

  public Message() {}

  public Message(String name, String message) {
    this.setName(name);
    this.setMessage(message);
  }

  @Override
  public String toString() {
    return "{" + "id=" + id + ", name=" + name + ",message=" + message + ",views=" + views
        + "created_at=" + createdAt + '}';
  }
}
