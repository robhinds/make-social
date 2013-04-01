/**
 * 
 */
package com.tmm.maker.service;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tmm.maker.security.Account;
import com.tmm.maker.security.ApplicationUser;

/**
 * @author robert.hinds
 * 
 */
@Service("applicationService")
public class ApplicationService implements ApplicationContextAware {
	private static ApplicationContext staticAppContext;
	private ApplicationContext applicationContext;

	public static final UUID saltID = UUID.randomUUID();

	@Autowired
	private AccountService accountService;

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public static ApplicationService getInstance() {
		return (ApplicationService) staticAppContext.getBean("applicationService");
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		this.applicationContext = ctx;
		staticAppContext = ctx;
	}

	public ApplicationService() {
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accServ) {
		this.accountService = accServ;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public Account getLoggedInUserAccount() {
		ApplicationUser user = getApplicationUser();
		Account account = getAccountService().loadAccount(user.getAccountId());
		return account;
	}

	public Long getLoggedInUserAccountId() {
		ApplicationUser user = getApplicationUser();
		return user.getAccountId();
	}

	public boolean isLoggedInUser() {
		return (getApplicationUser() != null);
	}

	private ApplicationUser getApplicationUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return null;
		}

		if (auth.getPrincipal() instanceof ApplicationUser) {
			return (ApplicationUser) auth.getPrincipal();
		}

		return null;
	}

}
