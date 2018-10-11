package main.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import main.webapp.model.User;
import main.webapp.service.UserService;
import main.webapp.validator.UserFormValidator;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserFormValidator userFormValidator;
	
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * This method is used to initialize validator
	 * @param binder
	 */
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userFormValidator);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		logger.debug("index()");
		return "redirect:/users";
	}

	// list page
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String showAllUsers(Model model) {

		logger.debug("showAllUsers()");
		model.addAttribute("users", userService.getAll());
		return "users/list";

	}
	
	@RequestMapping(value="/users", method = RequestMethod.POST)
	public String saveOrUpdateUser(@ModelAttribute("userForm") @Validated User user,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		
		logger.debug("saveOrUpdate() : {}", user);
		
		if(result.hasErrors()) {
			populateDefaultModel(model);
			return "users/userForm";
		} else {
			redirectAttributes.addFlashAttribute("css", "success");
			if( user.getId() <= 0 ) {
				//New user
				redirectAttributes.addFlashAttribute("msg", "User added successfully!");
			} else {
				//existed user
				redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
			}
			
			userService.saveOrUpdate(user);
			return "redirect:/users/" + user.getId();
		}
	}
	
	// show user
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public String showUser(@PathVariable("id") long id, Model model) {

		logger.debug("showUser() id: {}", id);

		User user = userService.findById(id);
		if (user == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "User not found");
		}
		model.addAttribute("user", user);

		return "users/show";

	}
	
	//Show update form
	@RequestMapping(value="/users/{id}/update", method=RequestMethod.GET)
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("userForm", user);
		populateDefaultModel(model);
		return "users/userForm";
	}
	
	private void populateDefaultModel(Model model) {
		
	}
}
