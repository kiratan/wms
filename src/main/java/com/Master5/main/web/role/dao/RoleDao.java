package com.Master5.main.web.role.dao;

import java.util.List ;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository ;

import com.Master5.main.web.role.entry.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer>{

	Role findByState( int state ) ;
	
	List<Role> findByStateLessThanEqual( int state ) ;

}
