package spring_unittest.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring_unittest.entity.Message;
import spring_unittest.service.MessageService;

@Controller
public class MessageController {

  private static final Logger logger = Logger.getLogger(MessageController.class);

  @Autowired
  private MessageService messageService;

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
  public String printMessage(ModelMap model) {

    model.addAllAttributes(messageService.prepareContentPrintMessage());
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
  public String viewMessage(@PathVariable("id") long id, ModelMap model) {

    logger.warn("================== View Message id = " + id + " ===================");

    model.addAllAttributes(messageService.prepareContentViewMessagee(id));
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
  public String cloneMessage(@PathVariable("id") long id, ModelMap model) {

    logger.warn("================== Edit Message id = " + id + " ===================");

    model.addAllAttributes(messageService.prepareContentCloneMessage(id));
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

    model.addAllAttributes(messageService.prepareContentNewMessage());
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

    return messageService.actionCreateMessage(message, bindingResult, model);
  }

  /**
   * Show edit infomation of id message
   * 
   * @param id
   * @param model
   * @return
   */
  @RequestMapping(value = {"/messages/edit/{id}"}, method = RequestMethod.GET)
  public String editMessage(@PathVariable("id") long id, ModelMap model) {

    logger.warn("================== Edit Message id = " + id + " ===================");

    model.addAllAttributes(messageService.actionEditMessage(id));
    return "message/form";
  }

  /**
   * Action update message
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/messages/update/{id}", method = RequestMethod.POST)
  public String updateMessage(@PathVariable("id") long id,
      @ModelAttribute("message") @Valid Message message, BindingResult bindingResult, ModelMap model) {

    return messageService.actionUpdateMessage(id, message, bindingResult, model);
  }

  /**
   * Action delete id messages
   * 
   * @param id
   * @param model
   * @return
   */
  @RequestMapping(value = "/messages/delete/{id}", method = RequestMethod.GET)
  public String deleteMessage(@PathVariable("id") long id, ModelMap model) {

    logger.warn("================== Delete Message id = " + id + " ===================");

    return messageService.actionDeleteMessage(id);
  }
}
