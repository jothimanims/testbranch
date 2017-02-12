package com.ffss.datax.shiro.domain;

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.Table

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="PERMISSION")
class Permission {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	Long id;
	
	@Column(name="permission_name")
	String permissionName;
	
	@Column(name="url")
	String url;
	/*
	@JoinColumn(name="role_id")
	@ManyToOne*/
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "permissions")
	@JsonBackReference
	List<Role> roleId;

}