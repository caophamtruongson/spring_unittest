package spring_unittest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

  @RequestMapping(value = {"/", "/hello"}, method = RequestMethod.GET)
  public String printHello(ModelMap model) {
    model.addAttribute("title", "Message List");

    return "hello";
  }

  @RequestMapping(value = "/hello/view", method = RequestMethod.GET)
  public String viewHello(ModelMap model) {
    model.addAttribute("title", "View Message");

    return "hello/view";
  }

  @RequestMapping(value = "/hello/edit", method = RequestMethod.GET)
  public String editHello(ModelMap model) {
    model.addAttribute("title", "Edit Message");

    return "hello/form";
  }

  @RequestMapping(value = "/hello/new", method = RequestMethod.GET)
  public String newHello(ModelMap model) {
    model.addAttribute("title", "New Message");

    return "hello/form";
  }
}
