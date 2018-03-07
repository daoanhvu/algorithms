package main.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import main.webapp.model.User;

/***
 * http://www.mkyong.com/spring-mvc/spring-mvc-form-handling-example/
 * @author davu
 *
 */

@Controller
public class IndexController {
	
	
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ModelAndView helloWorld() {
		String message = "Hello, how are you!";
		return new ModelAndView("hellopage", "message", message);
	}
}
