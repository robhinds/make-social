/**
 * 
 */
package com.tmm.maker.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Project> projects = new ArrayList<Project>();
	
	//forked projects
	
	//starred projects
	
	//following
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_FOLLOWING", joinColumns = { @JoinColumn(name = "FOLLOWER_ID") }, 
				inverseJoinColumns = { @JoinColumn(name = "FOLLOWEE_ID") })
	private Set<Profile> following = new HashSet<Profile>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_FOLLOWING", joinColumns = { @JoinColumn(name = "FOLLOWEE_ID") }, 
				inverseJoinColumns = { @JoinColumn(name = "FOLLOWER_ID") })
	private Set<Profile> followers = new HashSet<Profile>();
	
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

	public Set<Profile> getFollowing() {
		return following;
	}

	public void setFollowing(Set<Profile> following) {
		this.following = following;
	}
	
	public void addFollowing(Profile following) {
		getFollowing().add(following);
		following.addFollower(this);
	}

	public void removeFollowing(Profile following) {
		getFollowing().remove(following);
		following.removeFollower(this);
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	
	public void addProject(Project p) {
		getProjects().add(p);
	}

	public void removeProject(Project p) {
		getProjects().remove(p);
	}

	public Set<Profile> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<Profile> followers) {
		this.followers = followers;
	}
	
	public void addFollower(Profile f) {
		getFollowers().add(f);
	}

	public void removeFollower(Profile f) {
		getFollowers().remove(f);
	}

}
