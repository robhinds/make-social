package com.tmm.maker.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmm.maker.core.dao.ProfileDAOImpl;
import com.tmm.maker.core.exception.CustomException;
import com.tmm.maker.core.exception.CustomExceptionCode;
import com.tmm.maker.domain.Profile;
import com.tmm.maker.domain.social.SocialConnection;
import com.tmm.maker.security.Account;

@Service("profileService")
@Transactional
public class ProfileService {

	private EntityManager entityManager;

	@Autowired
	private AccountService accountService;

	@Autowired
	private ProfileDAOImpl profileDao;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setProfileDao(ProfileDAOImpl profileDao) {
		this.profileDao = profileDao;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@Transactional
	public void createProfile(Profile p) {
		profileDao.persist(p);
	}

	@Transactional
	public void mergeProfile(Profile p) {
		profileDao.merge(p);
	}

	@Transactional
	public Profile loadProfile(long id) {
		return getEntityManager().find(Profile.class, id);
	}

	@Transactional
	public List<Profile> loadAllProfiles() {
		return profileDao.loadAllProfiles();
	}

	

	@Transactional
	public Profile loadProfileByName(String contactName)
			throws CustomException {
		Account acc = accountService.loadAccountByUserName(contactName);
		if (acc != null) {
			Profile user = acc.getUserProfile();
			if (user != null) {
				return user;
			}
		}

		// if nothing returned now then lets throw an exception
		throw new CustomException(CustomExceptionCode.USER001_INVALIDUSER,
				"No User/Team found matching name: " + contactName);
	}

	@Transactional
	public Profile loadProfileFromAccount(long accountId) {
		Account acc = entityManager.find(Account.class, accountId);
		Profile p = acc.getUserProfile();
		return p;
	}

	@Transactional
	public void addConnectionToAccount(long accountId, SocialConnection sc) {
		Profile p = loadProfileFromAccount(accountId);
		p.addConnection(sc);
		sc.setUser(p);
		entityManager.persist(sc);
	}

	@Transactional
	public void updateSocialConnection(long accountId, String providerId,
			String providerUserId, String accessToken, String displayName,
			Long expireTime, String imageUrl, String profileUrl,
			String refreshToken, String secret) {
		Profile p = loadProfileFromAccount(accountId);
		for (SocialConnection c : p.getConnections()) {
			if (c.getProviderId().equalsIgnoreCase(providerId)
					&& c.getProviderUserId().equalsIgnoreCase(providerUserId)) {
				c.setAccessToken(accessToken);
				c.setDisplayName(displayName);
				c.setExpireTime(expireTime);
				c.setImageUrl(imageUrl);
				c.setProfileUrl(profileUrl);
				c.setRefreshToken(refreshToken);
				c.setSecret(secret);
				break;
			}
		}
	}

}