package com.ffss.datax.shiro.domain;


import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

import com.fasterxml.jackson.annotation.JsonBackReference

@Entity
@Table(name="ROLE")
public class Role {

    @Id
	@GeneratedValue
	@Column(name="ROLE_ID")
    Long id;
	
	@Column(name="ROLE_NAME")
    String roleName;
	
	/*@ManyToOne
	@JoinColumn(name="USER_ID")*/
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
	@JsonBackReference
	List<UserAccount> userId;
	
    //@ManyToMany(mappedBy="roleId", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonBackReference
	@JoinTable(name = "ROLE_PERMISSION",joinColumns =
		@JoinColumn(name = "role_id", referencedColumnName = "ROLE_ID")
	,
	inverseJoinColumns =
		@JoinColumn(name = "permission_id",referencedColumnName = "ID")
	)
    List<Permission> permissions;

}
