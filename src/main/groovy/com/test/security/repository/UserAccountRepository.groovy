package com.ffss.datax.shiro.repository

import com.ffss.datax.shiro.domain.UserAccount

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface UserAccountRepository extends JpaRepository<UserAccount, Long>
{
	
	@Query("SELECT ua FROM UserAccount ua where ua.userName = :userName")
	def findUser(@Param("userName") String userName)
	
}
