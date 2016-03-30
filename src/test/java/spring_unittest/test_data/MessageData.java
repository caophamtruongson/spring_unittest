package spring_unittest.test_data;

import spring_unittest.entity.Message;

public class MessageData extends AbstractData<Message> {

  public void addObject(String[] object) {
    objects.add(new Message(object[0], object[1]));
  }
}
