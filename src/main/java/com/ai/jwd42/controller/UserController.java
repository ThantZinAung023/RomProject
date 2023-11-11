package com.ai.jwd42.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ai.jwd42.dto.User;
import com.ai.jwd42.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView user(ModelMap model, HttpServletRequest request) throws IOException {

		if (request.getSession().getAttribute("username") == null) {
			return new ModelAndView("redirect:/login");
		} else {
			User user = new User();
			model.addAttribute("users", userService.findAllUser());
			return new ModelAndView("user.html", "user", user);
		}
	}

	@RequestMapping(value = { "/updateUser" }, method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute("SpringWeb") User user, HttpServletRequest request,ModelMap model) throws IOException {
		model.addAttribute("users", userService.findAllUser());
		if (userService.checkEmailAlreadyExistAtUpdate(user.getEmail(), user.getId())) {
			model.addAttribute("message", "Email is Already exists!");
			return new ModelAndView("user.html", "user", new User());
//			return "redirect:/user";
		} else if (userService.checkPhoneNumberAlreadyExistAtUpdate(user.getPhoneNumber(), user.getId())) {
			model.addAttribute("message", "Phone Number is Already Exist!");
			return new ModelAndView("user.html", "user",new User());
//			return "redirect:/user";
		} else {
			userService.updateUser(user);
			model.addAttribute("users", userService.findAllUser());
			model.addAttribute("message", "Success User Update !");
			return new ModelAndView("user.html", "user", new User());
//			return "redirect:/user";
		}
	}

	@RequestMapping(value = { "/deleteUser" }, method = RequestMethod.POST)
	public ModelAndView deleteUser(@ModelAttribute("SpringWeb") User user,ModelMap model) throws IOException {

		userService.deleteUser(user.getId());
		model.addAttribute("message", "Delete User Success!");
		model.addAttribute("users", userService.findAllUser());
		return new ModelAndView("user.html", "user",new User());
//		return "redirect:/user";
	}

	@RequestMapping(value = { "/changeUser" }, method = RequestMethod.POST)
	public ModelAndView changeUser(@ModelAttribute("SpringWeb") User user, ModelMap model) throws IOException {
		model.addAttribute("users", userService.findAllUser());
		if (!user.getNewPassword().equals(user.getComfirmPassword())) {
			model.addAttribute("message", "New Password and Comfirm Password are not match!");
			return new ModelAndView("user.html", "user", new User());
//			return "redirect:/user";
		} else {
			if (userService.checkPassword(user.getPassword(), user.getId())) {

				userService.changePassword(user.getNewPassword(), user.getId());
				model.addAttribute("message", "Success Change Password!");
				model.addAttribute("users", userService.findAllUser());
				return new ModelAndView("user.html", "user", new User());
//				return "redirect:/user";
			} else {
				model.addAttribute("message", "Old Password is not correct!");
				return new ModelAndView("user.html", "user",new User());
//				return "redirect:/user";
			}
		}
	}

	@RequestMapping(value = { "/addUser" }, method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("SpringWeb") User user, ModelMap model) throws IOException {
		user.setLoginDateTime(new java.util.Date().toString());
		if (!user.getPassword().equals(user.getComfirmPassword())) {
			model.addAttribute("registerMessage", "Password and Comfirm Password are not match!");
			return new ModelAndView("login.html", "user", new User());
//			return "redirect:/login";
		} else if (userService.checkEmailAlreadyExist(user.getEmail())) {
			model.addAttribute("registerMessage", "Register Email is Already Exist!");
			return new ModelAndView("login.html", "user", new User());
//			return "redirect:/login";
		} else if (userService.checkPhoneNumberAlreadyExist(user.getPhoneNumber())) {
			model.addAttribute("registerMessage", "Register Phone Number is Already Exist!");
			return new ModelAndView("login.html", "user",new User());
//			return "redirect:/login";
		} else {
			userService.insertUser(user);
			model.addAttribute("registerMessage", "Registration Success!");
			return new ModelAndView("login.html", "user", new User());
//			return "redirect:/login";
		}
	}
}
