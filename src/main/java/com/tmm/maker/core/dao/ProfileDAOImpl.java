/**
 * 
 */
package com.tmm.maker.core.dao;

import static org.hibernate.search.jpa.Search.getFullTextEntityManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tmm.maker.domain.Profile;

/**
 * @author robert.hinds
 * 
 */
@Repository(value = "profileDAO")
public class ProfileDAOImpl extends GenericHibernateDAO<Profile, Long>
		implements ProfileDAO {

	@SuppressWarnings("unchecked")
	public List<Profile> loadAllProfiles() {
		Query query = getEntityManager().createQuery("select s from Profile s");
		List<Profile> s = (List<Profile>) query.getResultList();
		return s;
	}

	@Override
	public List<Profile> loadProfiles(Long[] ids) {
		if (ids.length == 0) {
			return new ArrayList<Profile>();
		}
		Query query = getEntityManager().createQuery(
				"select c from Profile c where c.id in ("
						+ createSqlArgs(ids.length) + ")");
		int i = 0;
		for (Long id : ids) {
			query.setParameter(i++, id);
		}
		return query.getResultList();
	}

	
	private String createSqlArgs(int length) {
		String[] builder = new String[length];
		for (int i = 0; i < length; i++) {
			builder[i] = "?" + i;
		}
		return StringUtils.join(builder, ",");
	}
}
