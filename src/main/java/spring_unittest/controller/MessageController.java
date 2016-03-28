package spring_unittest.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring_unittest.dao.MessageJdbcDao;
import spring_unittest.entity.Message;

@Controller
public class MessageController {

  private static final Logger logger = Logger.getLogger(MessageController.class);

  @Autowired
  private MessageJdbcDao messageJdbcDao;

  @ModelAttribute("message")
  public Message getMessageObject() {
    return new Message();
  }

  /**
   * Show messages list
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = {"/", "/messages"}, method = RequestMethod.GET)
  public String printMessage(Model model) {

    model.addAttribute("title", "Message List");
    model.addAttribute("messages", messageJdbcDao.findAll());
    model.addAttribute("hotMessages", messageJdbcDao.getHotMessage());
    return "message";
  }

  /**
   * Show page view id message
   * 
   * @param id
   * @param model
   * @return
   */
  @RequestMapping(value = "/messages/view/{id}", method = RequestMethod.GET)
  public String viewMessage(@PathVariable("id") Long id, ModelMap model) {

    logger.warn("================== View Message id = " + id.toString() + " ===================");

    Message message = messageJdbcDao.findOne(id);
    messageJdbcDao.updateViews(message);

    model.addAttribute("message", message);
    model.addAttribute("title", "View Message");
    return "message/view";
  }

  /**
   * Show edit infomation of id message
   * 
   * @param id
   * @param model
   * @return
   */
  @RequestMapping(value = {"/messages/clone/{id}"}, method = RequestMethod.GET)
  public String cloneMessage(@PathVariable("id") Long id, ModelMap model) {

    logger.warn("================== Edit Message id = " + id.toString() + " ===================");

    Message message = messageJdbcDao.findOne(id);

    model.addAttribute("message", message);
    model.addAttribute("title", "Clone Message");
    model.addAttribute("url", "/messages/create");
    return "message/form";
  }

  /**
   * Show new message
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/messages/new", method = RequestMethod.GET)
  public String newMessage(@ModelAttribute("message") Message message, BindingResult bindingResult,
      ModelMap model) {

    model.addAttribute("title", "New Message");
    model.addAttribute("url", "/messages/create");
    return "message/form";
  }

  /**
   * Action create message
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/messages/create", method = RequestMethod.POST)
  public String createMessage(@ModelAttribute("message") @Valid Message message,
      BindingResult bindingResult, ModelMap model) {

    logger
        .warn("================== Create Message = " + message.getName() + " ===================");

    if (bindingResult.hasErrors()) {
      model.addAttribute("title", "New Message");
      model.addAttribute("url", "/messages/create");
      return "message/form";
    }

    messageJdbcDao.create(message);

    return "redirect:/messages";
  }

  /**
   * Show edit infomation of id message
   * 
   * @param id
   * @param model
   * @return
   */
  @RequestMapping(value = {"/messages/edit/{id}"}, method = RequestMethod.GET)
  public String editMessage(@PathVariable("id") Long id, ModelMap model) {

    logger.warn("================== Edit Message id = " + id.toString() + " ===================");

    Message message = messageJdbcDao.findOne(id);

    model.addAttribute("message", message);
    model.addAttribute("title", "Edit Message");
    model.addAttribute("url", "/messages/update/" + id.toString());
    return "message/form";
  }

  /**
   * Action update message
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/messages/update/{id}", method = RequestMethod.POST)
  public String updateMessage(@PathVariable("id") Long id,
      @ModelAttribute("message") @Valid Message message, BindingResult bindingResult, ModelMap model) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("title", "Edit Message");
      model.addAttribute("url", "/messages/update/" + id.toString());
      return "message/form";
    }

    messageJdbcDao.update(id, message);

    return "redirect:/messages";
  }

  /**
   * Action delete id messages
   * 
   * @param id
   * @param model
   * @return
   */
  @RequestMapping(value = "/messages/delete/{id}", method = RequestMethod.GET)
  public String deleteMessage(@PathVariable("id") Long id, ModelMap model) {

    logger.warn("================== Delete Message id = " + id.toString() + " ===================");

    messageJdbcDao.delete(id);
    return "redirect:/messages";
  }
}
