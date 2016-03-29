package spring_unittest.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import spring_unittest.dao.MessageJdbcDao;
import spring_unittest.entity.Message;

public class MessageService {

  @Autowired
  private MessageJdbcDao messageJdbcDao;

  public Map<String, Object> prepareContentPrintMessage() {
    Map<String, Object> content = new HashMap<String, Object>();
    content.put("title", "Message List");
    content.put("messages", messageJdbcDao.findAll());
    content.put("hotMessages", messageJdbcDao.getHotMessage());
    return content;
  }

  public Map<String, Object> prepareContentViewMessagee(long id) {
    Map<String, Object> content = new HashMap<String, Object>();
    Message message = messageJdbcDao.findOne(id);

    if (message.getId() > 0) {
      messageJdbcDao.updateViews(message);
    }

    content.put("message", message);
    content.put("title", "View Message");
    return content;
  }

  public Map<String, Object> prepareContentCloneMessage(long id) {
    Map<String, Object> content = new HashMap<String, Object>();

    content.put("message", messageJdbcDao.findOne(id));
    content.put("title", "Clone Message");
    content.put("url", "/messages/create");
    return content;
  }

  public Map<String, Object> prepareContentNewMessage() {
    Map<String, Object> content = new HashMap<String, Object>();

    content.put("title", "New Message");
    content.put("url", "/messages/create");
    return content;
  }

  public Map<String, Object> prepareContentEditMessage(long id) {
    Map<String, Object> content = new HashMap<String, Object>();

    content.put("title", "Edit Message");
    content.put("url", "/messages/update/" + id);
    return content;
  }

  public String actionCreateMessage(Message message, BindingResult bindingResult, ModelMap model) {

    if (bindingResult.hasErrors()) {
      model.addAllAttributes(prepareContentNewMessage());
      return "message/form";
    }

    messageJdbcDao.create(message);
    return "redirect:/messages";
  }

  public Map<String, Object> actionEditMessage(long id) {
    Map<String, Object> content = prepareContentEditMessage(id);

    Message message = messageJdbcDao.findOne(id);
    content.put("message", message);
    return content;
  }

  public String actionUpdateMessage(long id, Message message, BindingResult bindingResult,
      ModelMap model) {
    if (bindingResult.hasErrors()) {
      model.addAllAttributes(prepareContentEditMessage(id));
      return "message/form";
    }

    messageJdbcDao.update(id, message);
    return "redirect:/messages";
  }

  public String actionDeleteMessage(long id) {
    messageJdbcDao.delete(id);
    return "redirect:/messages";
  }
}
