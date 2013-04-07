package com.tmm.maker.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.tmm.maker.security.Account;
import com.tmm.maker.service.AccountService;
import com.tmm.maker.service.MD5Util;
import com.tmm.maker.service.UserProfileService;

/**
 * Controller class that handles all requests for the site home page - it
 * determines whether or not the user is logged in and either displays the site
 * welcome/login page or directs the user to their user profile page
 * 
 * @author robert.hinds
 * 
 */
@Controller
@RequestMapping("/user")
public class UserProfileController {

	@Autowired
	UserProfileService userProfileService;
	@Autowired
	AccountService accountService;
	@Autowired
	MD5Util md5Util;


	@RequestMapping("/{userName}")
	public ModelAndView viewUserProfile(@PathVariable("userName") String userName, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Account viewedUser = accountService.loadAccountByUserName(userName);
		if ( viewedUser == null ) {
			return new ModelAndView("resourceNotFound");
		}

		Map<String, Object> model = Maps.newHashMap();
		model.put("userName", WordUtils.capitalize(viewedUser.getUserName()));
		model.put("gUrl", md5Util.createGravHash(viewedUser));
		
		if (userName.equals(request.getRemoteUser())){
			return new ModelAndView("userprofile", model);			
		}else{
			return new ModelAndView("otheruserprofile", model);
		}
	}
}
