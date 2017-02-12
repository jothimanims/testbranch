package com.test.security.repository

import com.test.security.domain.UserAccount

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
