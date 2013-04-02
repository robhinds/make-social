package com.tmm.maker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tmm.maker.service.ApplicationService;

/**
 * Controller class that handles all requests for the site home page - it
 * determines whether or not the user is logged in and either displays the site
 * welcome/login page or directs the user to their user profile page
 * 
 * @author robert.hinds
 * 
 */
@Controller
@RequestMapping("/")
public class HomeController {

	/**
	 * This method is called by the home page only - determines if anonymous
	 * request and then either displays welcome info or login page
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView homepage(HttpServletRequest request) throws Exception {
		if (ApplicationService.getInstance().isLoggedInUser()) {
			return new ModelAndView("redirect:/user/" + request.getRemoteUser());
		} else {
			return new ModelAndView("homepage");
		}
	}

}
