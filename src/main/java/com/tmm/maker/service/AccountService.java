package com.tmm.maker.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tmm.maker.core.exception.CustomException;
import com.tmm.maker.core.exception.CustomExceptionCode;
import com.tmm.maker.domain.Profile;
import com.tmm.maker.security.Account;
import com.tmm.maker.security.ApplicationUser;
import com.tmm.maker.security.Role;
import com.tmm.maker.security.dao.AccountDAO;
import com.tmm.maker.security.dao.RoleDAO;

@Service("accountService")
public class AccountService {
	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private Validator validator;

	// 24 hour timeout - push to config at some point
	private static long RESET_TIMEOUT_MS = 172800000;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Account> findAllAccounts() {
		return accountDAO.find();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Account loadAccount(long id) {
		return accountDAO.read(Account.class, new Long(id));
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Account loadAccountByUserName(String userName) {
		return accountDAO.loadAccountByUserName(userName);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Account loadUserAccountByEmail(String email) {
		return accountDAO.loadUserAccountByEmail(email);
	}

	@Transactional
	public void removeAccount(long id) {
		accountDAO.delete(accountDAO.read(Account.class, new Long(id)));
	}

	@Transactional
	public Account storeAccount(Account account) {
		return accountDAO.merge(account);
	}

	public Account createNewBatchUser(String name, String email, String password) {
		return createNewBatchUser(name, email, password, null, null);
	}

	@Transactional
	public Account createNewUser(String username, String email, String password) throws CustomException {
		if (loadUserAccountByEmail(email) == null) {
			if (loadAccountByUserName(username) == null) {
				Account account = new Account();
				account.setUserName(username);
				account.setEmail(email);
				Set<ConstraintViolation<Account>> result = validator.validate(account);
				if ((result.size() == 0)) {
					Role r = loadOrCreateRole(Role.ROLE_USER);
					account.addRole(r);
					Profile userProf = new Profile();
					userProf.setLinkedAccount(account);
					account.setUserProfile(userProf);

					UUID confirmationId = UUID.randomUUID();
					account.setConfirmationId(confirmationId.toString());

					accountDAO.persist(account);

					// now update password once we have the account created (we
					// need the ID to have been generated)
					account.setAndEncodePassword(password);

					return account;
				}
				throw new CustomException(CustomExceptionCode.USER004_INVALIDUSERNAME, "Attempting to register user with invalid username");
			}
			throw new CustomException(CustomExceptionCode.USER002_DUPLICATEUSER, "Attempting to register user with existing username");
		}
		throw new CustomException(CustomExceptionCode.USER003_DUPLICATEEMAIL, "Attempting to register user with existing email");

	}

	@Transactional
	public Role loadOrCreateRole(String authority) {
		Role r = roleDAO.loadRoleByAuthority(authority);
		if (r == null) {
			r = new Role();
			r.setRole(authority);
			roleDAO.persist(r);
		}
		return r;
	}

	@Transactional
	public Account createNewBatchUser(String name, String email, String password, String firstName, String lastName) {
		if (loadAccountByUserName(name) == null) {
			Account account = new Account();
			account.setUserName(name);
			account.setEmail(email);
			Role r = loadOrCreateRole(Role.ROLE_USER);
			account.addRole(r);
			account.setConfirmed(true);

			accountDAO.persist(account);

			// now update password once we have the account created (we need the
			// ID to have been generated)
			account.setAndEncodePassword(password);
			// accountDAO.merge(account);

			return account;
		}

		return null;
	}

	@Transactional
	public void startResetPassword(String input) throws UsernameNotFoundException {
		Account account = loadAccountByUserName(input);
		if (account == null) {
			account = loadUserAccountByEmail(input);
		}

		if (account != null) {
			account.setResetPasswordId(UUID.randomUUID().toString());
			Date date = new Date();
			account.setResetPasswordExpiryTime(new Timestamp(date.getTime() + RESET_TIMEOUT_MS));
			account.setResetComplete(false);
		} else {
			throw new UsernameNotFoundException("The password reset has not been activated for the input:  " + input + "-  Aborting reset password");
		}
	}

	@Transactional
	public void completeResetPassword(String username, String newPassword) throws UsernameNotFoundException {
		Account account = loadAccountByUserName(username);
		if (account == null) {
			account = loadUserAccountByEmail(username);
		}

		if (account != null) {
			account.setAndEncodePassword(newPassword);
			account.setResetComplete(true);
		} else {
			throw new UsernameNotFoundException("The password reset failed for the user:  " + username + "-  Aborting reset password");
		}
	}

	@Transactional
	public void setCredentials() {
		Account account = loadAccountByUserName("admin");
		if (account == null) {
			account = createNewBatchUser("admin", "", "admin");
		}
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		auths.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		ApplicationUser user = new ApplicationUser(new Long(account.getId()), account.getUserName(), account.getPassword(), true, true, true, true, auths);
		Authentication auth = new TestingAuthenticationToken(user, "ignored", auths);
		auth.setAuthenticated(true);
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	public void clearCredentials() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	@Transactional
	public void confirmAccount(long accId) {
		Account acc = loadAccount(accId);
		acc.setConfirmed(true);
	}

}