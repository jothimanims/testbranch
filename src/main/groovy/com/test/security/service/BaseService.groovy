package com.ffss.datax.shiro.service

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ffss.datax.shiro.config.MySqlDbRealarm;

@Service
class BaseService {
	
	def accessUserDetails(String ){
		if(SecurityUtils.getSubject().isPermitted("study:1:update")) 
		return "hasUpdateAccess"
		else if(SecurityUtils.getSubject().isPermitted("study:2:write"))
		  return "hasWriteAccess"
		else 
		  return "NoPermission"
	}
	
}
