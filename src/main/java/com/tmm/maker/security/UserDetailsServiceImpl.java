package com.tmm.maker.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tmm.maker.service.AccountService;

/**
 * Custom implementation to support Spring Security logon based on custom
 * authentication of persisted databases
 * 
 * @author robert.hinds
 * 
 */
@Service("userService")
public class UserDetailsServiceImpl implements UserDetailsService, InitializingBean {

	@Autowired
	private AccountService accountService;

	public void afterPropertiesSet() throws Exception {
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
		userName = userName.toLowerCase();
		try {
			Account account = accountService.loadAccountByUserName(userName);
			if (account == null) {
				throw new UsernameNotFoundException("Could not find username: " + userName + "in the DB.");
			}
			// if (!account.isConfirmed()) {
			// throw new IllegalStateException("User not yet confirmed email" +
			// userName + " login failed");
			// }

			List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
			for (Role r : account.getRoles()) {
				auths.add(new GrantedAuthorityImpl(r.getRole()));
			}
			ApplicationUser user = null;
			try {
				user = new ApplicationUser(new Long(account.getId()), userName, account.getPassword(), true, true, true, true, auths);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(userName + "not found", e);
		}
	}

}
