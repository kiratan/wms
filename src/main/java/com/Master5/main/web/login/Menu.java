
package com.Master5.main.web.login ;

import javax.servlet.http.HttpSession ;

import org.apache.shiro.SecurityUtils ;
import org.apache.shiro.authz.annotation.RequiresPermissions ;
import org.apache.shiro.subject.Subject ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.stereotype.Controller ;
import org.springframework.ui.Model ;
import org.springframework.web.bind.annotation.RequestMapping ;
import org.springframework.web.bind.annotation.RequestMethod ;

import com.Master5.main.annotation.CheckPermission;
import com.Master5.main.utils.constant.Key;
import com.Master5.main.utils.constant.SysKey;
import com.Master5.main.web.user.service.PermissionService;
import com.Master5.main.web.user.service.RoleService;
import com.Master5.main.web.user.service.UserService;

@Controller
@RequestMapping ( value = "menu" )
public class Menu {

	@Autowired
	UserService userService ;

	@Autowired
	PermissionService permissionService ;

	@Autowired
	RoleService roleService ;

	@RequiresPermissions ( value = "menu:list" )
	@CheckPermission ( name = "首页" , method = "menu:list" , state = SysKey.STATE_DEFAULT_DARK )
	@RequestMapping ( value = "list" )
	public String menu( Model model ) {

		model.addAttribute ( "list" , userService.findAll ( ) ) ;
		return "index" ;
	}

	@RequiresPermissions ( value = "menu:exit" )
	@CheckPermission ( name = "退出" , method = "menu:exit" , state = SysKey.STATE_DEFAULT_DARK )
	@RequestMapping ( value = "exit" , method = RequestMethod.GET )
	public String exit( HttpSession session , Model model ) {

		Subject subject = SecurityUtils.getSubject ( ) ;
		if ( subject.isAuthenticated ( ) ) {
			subject.logout ( ) ; // session 会销毁，在SessionListener监听session销毁，清理权限缓存
		}
		return "/login/regist" ;
	}

}
