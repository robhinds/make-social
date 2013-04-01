package com.tmm.maker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userProfileService")
public class UserProfileService {

	@Autowired
	AccountService accountService;

}