package spring_unittest.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import spring_unittest.dao.MessageJdbcDao;
import spring_unittest.entity.Message;
import spring_unittest.test_data.MessageData;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTest {

  @InjectMocks
  MessageService messageService;

  @Mock
  MessageJdbcDao messageJdbcDao;

  @Mock
  BindingResult bindingResult;

  @Mock
  ModelMap model;

  List<Message> messages;

  long id;

  Message message;


  public MessageServiceTest() {
    MockitoAnnotations.initMocks(this.getClass());
    MessageData messageData = new MessageData();
    messageData.generateData("data/messages.csv");
    messages = messageData.getObjects();
    id = 10;
    message = messages.get(0);
  }

  @Test
  public void testPrepareContentPrintMessage() {
    List<Message> hot_message = messages.subList(0, 3);
    List<Message> normal_message = messages.subList(3, 8);

    when(messageJdbcDao.findAll()).thenReturn(normal_message);
    when(messageJdbcDao.getHotMessage()).thenReturn(hot_message);

    Map<String, Object> content = messageService.prepareContentPrintMessage();

    assertEquals(content.get("title"), "Message List");
    assertEquals(content.get("messages"), normal_message);
    assertEquals(content.get("hotMessages"), hot_message);
  }

  @Test
  public void testPrepareContentViewMessage() {
    message.setId(id);
    message.setViews(10);

    when(messageJdbcDao.findOne(id)).thenReturn(message);

    Map<String, Object> content = messageService.prepareContentViewMessagee(id);

    verify(messageJdbcDao).updateViews(message);
    assertEquals(content.get("title"), "View Message");
    assertEquals(content.get("message"), message);
  }

  @Test
  public void testPrepareContentViewMessageWithIdNoExists() {
    when(messageJdbcDao.findOne(id)).thenReturn(message);

    Map<String, Object> content = messageService.prepareContentViewMessagee(id);

    verify(messageJdbcDao, never()).updateViews(message);
    assertEquals(content.get("title"), "View Message");
    assertEquals(content.get("message"), message);
  }

  @Test
  public void testPrepareContentCloneMessage() {
    when(messageJdbcDao.findOne(id)).thenReturn(message);

    Map<String, Object> content = messageService.prepareContentCloneMessage(id);

    verify(messageJdbcDao, never()).updateViews(message);
    assertEquals(content.get("title"), "Clone Message");
    assertEquals(content.get("url"), "/messages/create");
    assertEquals(content.get("message"), message);
  }

  @Test
  public void testPrepareContentNewMessage() {
    Map<String, Object> content = messageService.prepareContentNewMessage();

    assertEquals(content.get("title"), "New Message");
    assertEquals(content.get("url"), "/messages/create");
  }

  @Test
  public void testPrepareContentEditMessage() {
    Map<String, Object> content = messageService.prepareContentEditMessage(id);

    assertEquals(content.get("title"), "Edit Message");
    assertEquals(content.get("url"), "/messages/update/" + id);
  }

  @Test
  public void testActionCreateMessageInvalid() {
    when(bindingResult.hasErrors()).thenReturn(true);

    assertEquals(messageService.actionCreateMessage(message, bindingResult, model), "message/form");
    verify(model).addAllAttributes(messageService.prepareContentNewMessage());
    verify(messageJdbcDao, never()).create(message);
  }

  @Test
  public void testActionCreateMessageValid() {
    when(bindingResult.hasErrors()).thenReturn(false);

    assertEquals(messageService.actionCreateMessage(message, bindingResult, model),
        "redirect:/messages");
    verify(model, never()).addAllAttributes(messageService.prepareContentEditMessage(id));
    verify(messageJdbcDao).create(message);
  }

  @Test
  public void testActionEditMessage() {
    when(messageJdbcDao.findOne(id)).thenReturn(message);

    Map<String, Object> content = messageService.actionEditMessage(id);

    assertEquals(content.get("message"), message);
    assertEquals(content.get("title"), "Edit Message");
    assertEquals(content.get("url"), "/messages/update/" + id);
  }

  @Test
  public void testActionUpdateMessageInvalid() {
    when(bindingResult.hasErrors()).thenReturn(true);

    assertEquals(messageService.actionUpdateMessage(id, message, bindingResult, model),
        "message/form");
    verify(model).addAllAttributes(messageService.prepareContentEditMessage(id));
    verify(messageJdbcDao, never()).update(id, message);
  }

  @Test
  public void testActionUpdateMessageValid() {
    when(bindingResult.hasErrors()).thenReturn(false);

    assertEquals(messageService.actionUpdateMessage(id, message, bindingResult, model),
        "redirect:/messages");
    verify(model, never()).addAllAttributes(messageService.prepareContentEditMessage(id));
    verify(messageJdbcDao).update(id, message);
  }

  @Test
  public void testActionDeleteMessage() {
    assertEquals(messageService.actionDeleteMessage(id), "redirect:/messages");
    verify(messageJdbcDao).delete(id);
  }
}
