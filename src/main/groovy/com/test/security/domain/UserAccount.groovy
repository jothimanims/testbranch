package com.ffss.datax.shiro.domain

import javax.persistence.*
import javax.validation.constraints.NotNull

import org.hibernate.annotations.Type
import org.joda.time.DateTime

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize

@Entity
@Table(name="USER_ACCOUNT")
class UserAccount implements java.io.Serializable
{
	@Id 
 	@GeneratedValue
    @Column(name="USER_ACCOUNT_PK")	
 	Long id 
    
	/*@Column(name="ENABLED")
	Character enabled = '\1'
	*/
	@Column(name="FIRST_NAME")
	String userName 
	
	@Column(name="LAST_NAME")
	String lastName
	/*
	@Column(name="PREFIX")
	String prefix*/
	
	@NotNull
	@Column(name="PASSWORD_HASH")
	String password
	
	@NotNull
	@Column(name="EMAIL_ADDRESS")
	String emailAddress
	
	@Column(name="CREATED_BY")
	Long createdBy
	
	@Column(name="UPDATED_BY")
	Long updatedBy
	
	//@OneToMany(mappedBy = "userId",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonBackReference
	@JoinTable(name = "USER_ACCOUNT_ROLE",joinColumns =
		@JoinColumn(name = "USER_ACCOUNT_ID", referencedColumnName = "USER_ACCOUNT_PK")
	,
	inverseJoinColumns =
		@JoinColumn(name = "USER_ROLE_ID",referencedColumnName = "ROLE_ID")
	)
	List<Role> roles;
	

}
