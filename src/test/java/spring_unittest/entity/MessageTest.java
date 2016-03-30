package spring_unittest.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MessageTest {

  @InjectMocks
  Message message;

  @Test
  public void testToString() {
    message = new Message("Message Name", "Message Message");

    assertEquals(message.toString(),
        "{id=0, name=Message Name,message=Message Message,views=0created_at=null}");

  }
}
