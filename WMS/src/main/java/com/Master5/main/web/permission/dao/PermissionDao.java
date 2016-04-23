package com.Master5.main.web.permission.dao;

import java.util.List ;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository ;

import com.Master5.main.web.permission.entry.Permission;

@Repository
public interface PermissionDao extends JpaRepository<Permission, Integer>{

	List<Permission> findByStateLessThanEqual( int state ) ;
	
	List<Permission> findByStateGreaterThanEqual( int state ) ;

}
