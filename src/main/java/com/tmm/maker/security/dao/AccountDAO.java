/**
 * 
 */
package com.tmm.maker.security.dao;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tmm.maker.core.dao.GenericHibernateDAO;
import com.tmm.maker.security.Account;

/**
 * @author robert.hinds
 * 
 */
@Repository(value = "accountDAO")
public class AccountDAO extends GenericHibernateDAO<Account, Long> {

	
	@Transactional
	public Account loadUserAccountByEmail(String email) {
		Query query = getEntityManager().createQuery("select u from Account u where u.email = ?1");
		query.setParameter(1, email);
		Account user = null;

		try {
			List<Account> users = (List<Account>) query.getResultList();
			if (users == null || users.isEmpty() || users.get(0).getUserName().equalsIgnoreCase("admin")) {
				return null;
			} else {
				return users.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@Transactional
	public Account loadAccountByUserName(String userName) {
		Query query = getEntityManager().createQuery("select u from Account u where u.userName = ?1");
		query.setParameter(1, userName);
		Account user = null;

		try {
			List<Account> users = (List<Account>) query.getResultList();
			if (users == null || users.isEmpty()) {
				return null;
			} else {
				return users.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}


}
