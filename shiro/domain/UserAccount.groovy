package com.ffss.datax.shiro.domain

import javax.persistence.*
import javax.validation.constraints.NotNull

import org.hibernate.annotations.Type
import org.joda.time.DateTime

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize

@Entity
@Table(name="USER_ACCOUNT")
class UserAccount 
{
	@Id 
 	@GeneratedValue
    @Column(name="USER_ACCOUNT_PK")	
 	Long id 
    
	@Column(name="ENABLED")
	Character enabled = '\1'
	
	@Column(name="FIRST_NAME")
	String firstName 
	
	@Column(name="LAST_NAME")
	String lastName
	
	@Column(name="PREFIX")
	String prefix
	
	@NotNull
	@Column(name="PASSWORD_HASH")
	String passwordHash
	
	@NotNull
	@Column(name="EMAIL_ADDRESS")
	String emailAddress
	
	@Column(name="CREATED_BY")
	Long createdBy
	
	@Column(name="CREATED_DATE_TIME")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	DateTime createdDateTime
	
	@Column(name="UPDATED_BY")
	Long updatedBy
	
	@Column(name="UPDATED_DATE_TIME")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	DateTime updatedDateTime

}
