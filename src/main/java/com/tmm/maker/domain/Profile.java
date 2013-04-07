/**
 * 
 */
package com.tmm.maker.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tmm.maker.domain.social.SocialConnection;
import com.tmm.maker.security.Account;

/**
 * Class responsible for persisting user profile details
 * 
 * @author robert.hinds
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "USERPROFILE")
public class Profile extends PersistableObject {

	//badges
	
	//owned projects
	
	//forked projects
	
	//starred projects
	
	//following
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<SocialConnection> connections = new ArrayList<SocialConnection>();

	private static final long serialVersionUID = 3458607287170514439L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account")
	private Account linkedAccount;

	public Account getLinkedAccount() {
		return linkedAccount;
	}

	public void setLinkedAccount(Account linkedAccount) {
		this.linkedAccount = linkedAccount;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	public List<SocialConnection> getConnections() {
		return connections;
	}

	public void setConnections(List<SocialConnection> connections) {
		this.connections = connections;
	}

	public void addConnection(SocialConnection connection) {
		this.connections.add(connection);
	}

	public void removeConnection(SocialConnection connection) {
		this.connections.remove(connection);
	}

}
