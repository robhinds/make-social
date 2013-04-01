package com.tmm.maker.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tmm.maker.core.exception.CustomException;
import com.tmm.maker.core.exception.CustomExceptionCode;
import com.tmm.maker.service.AccountService;

/**
 * Controller class that handles all requests for the site home page - it
 * determines whether or not the user is logged in and either displays the site
 * welcome/login page or directs the user to their user profile page
 * 
 * @author robert.hinds
 * 
 */
@Controller
@RequestMapping("/sign-up")
public class SignupController {

	@Autowired
	private AccountService accountService;

	@RequestMapping(method = RequestMethod.GET)
	public String signupPage(HttpServletRequest request) throws Exception {
		return "signup";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView registerUser(@RequestParam String username, @RequestParam String pword1, @RequestParam String pword2, @RequestParam String email1, @RequestParam String email2)
			throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		model.put("username", username);
		model.put("email1", email1);
		model.put("email2", email2);

		if (pword1 == null || pword1.equals("") || !pword1.equals(pword2)) {
			model.put("passworderror", "Error verifying passwords - please ensure password confirmation is correct");
		}
		if (email1 == null || email1.equals("") || !email1.equals(email2)) {
			model.put("emailerror", "Error verifying emails - please ensure email confirmation is correct");
		}

		if ((!model.containsKey("passworderror") && !model.containsKey("emailerror"))) {
			try {
				accountService.setCredentials();
				accountService.createNewUser(username, email1, pword1);
				accountService.clearCredentials();
				return new ModelAndView("redirect:/sign-in?success=true");
			} catch (CustomException cex) {
				if (cex.getExceptionCode().equals(CustomExceptionCode.USER002_DUPLICATEUSER)) {
					model.put("usernameerror", "Error registering - This username is already registered (is it already yours? if so login now)");
				}
				if (cex.getExceptionCode().equals(CustomExceptionCode.USER003_DUPLICATEEMAIL)) {
					model.put("emailerror", "Error registering - This email is already registered ");
				}
				if (cex.getExceptionCode().equals(CustomExceptionCode.USER004_INVALIDUSERNAME)) {
					model.put("usernameerror", "Error registering - This username is invalid, please ensure you are only using valid letters");
				}
			}
		}

		return new ModelAndView("homepage", model);
	}
}
