package com.tmm.maker.service;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import com.tmm.maker.security.Account;

@Component
public class MD5Util {
	
	public String createGravHash(Account viewedUser) {
		String email = viewedUser.getEmail().trim().toLowerCase(); 
		try {
			return DigestUtils.md5Hex(email.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			return ""; 
		}
	}
}