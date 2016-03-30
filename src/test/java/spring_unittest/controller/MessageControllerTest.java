package spring_unittest.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import spring_unittest.entity.Message;
import spring_unittest.service.MessageService;

@RunWith(MockitoJUnitRunner.class)
public class MessageControllerTest {

  @InjectMocks
  MessageController messageController;

  @Mock
  MessageService messageService;

  @Mock
  BindingResult bindingResult;

  Message message;

  ModelMap model;

  long id;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    model = new ModelMap();
    message = new Message();
    id = 10;
  }

  @Test
  public void testPrintMessage() {
    assertEquals(messageController.printMessage(model), "message");
    verify(messageService).prepareContentPrintMessage();
  }

  @Test
  public void testViewMessage() {
    assertEquals(messageController.viewMessage(id, model), "message/view");
    verify(messageService).prepareContentViewMessagee(id);
  }

  @Test
  public void testCloneMessage() {
    assertEquals(messageController.cloneMessage(id, model), "message/form");
    verify(messageService).prepareContentCloneMessage(id);
  }

  @Test
  public void testNewMessage() {
    assertEquals(messageController.newMessage(message, bindingResult, model), "message/form");
    verify(messageService).prepareContentNewMessage();
  }

  @Test
  public void testCreateMessage() {
    when(messageService.actionCreateMessage(message, bindingResult, model)).thenReturn(
        "redirect:/messages");

    assertEquals(messageController.createMessage(message, bindingResult, model),
        "redirect:/messages");
  }

  @Test
  public void testEditMessage() {
    assertEquals(messageController.editMessage(id, model), "message/form");
    verify(messageService).actionEditMessage(id);
  }

  @Test
  public void testUpdateMessage() {
    when(messageService.actionUpdateMessage(id, message, bindingResult, model)).thenReturn(
        "redirect:/messages");

    assertEquals(messageController.updateMessage(id, message, bindingResult, model),
        "redirect:/messages");
  }

  @Test
  public void testDeleteMessage() {
    when(messageService.actionDeleteMessage(id)).thenReturn("redirect:/messages");

    assertEquals(messageController.deleteMessage(id, model), "redirect:/messages");
  }

  @Test
  public void testGetMessageObject() {
    assertEquals(messageController.getMessageObject().getClass(), Message.class);
  }
}
