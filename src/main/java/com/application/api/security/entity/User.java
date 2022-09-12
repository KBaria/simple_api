package com.application.api.security.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "app_user")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	
	private String username;
	
	private String password;
	
	@Column(name = "non_expired")
	private boolean userNonExpired;
	
	@Column(name = "non_locked")
	private boolean userNonLocked;
	
	@Column(name = "credentials_non_expired")
	private boolean userCredentialsNonExpired;
	
	@Column(name = "enabled")
	private boolean userEnabled;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<RoleData> roleData;
	
	@Transient
	private List<Role> roles;
	
	public User processRoles() {
		this.roles = this.roleData.stream().map(data -> data.getRole()).toList();
		return this;
	}
	
	public User processRoleData() {
		this.roleData = this.roles.stream().map(role -> {
			RoleData roleDataSet = new RoleData();
			roleDataSet.setUser(this);
			roleDataSet.setRole(role);
			return roleDataSet;
		}).toList();
		
		return this;
	}
}
