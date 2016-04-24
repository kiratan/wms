
package com.Master5.main.web.login;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Master5.main.annotation.annotationtools.GetValueFromAnnotation;
import com.Master5.main.utils.GetClassByPackage;
import com.Master5.main.utils.IPTools;
import com.Master5.main.utils.constant.Key;
import com.Master5.main.utils.constant.MsgKey;
import com.Master5.main.utils.encrypt.MD5;
import com.Master5.main.utils.encrypt.encryptTools;
import com.Master5.main.web.permission.entry.Permission;
import com.Master5.main.web.permission.service.PermissionService;
import com.Master5.main.web.role.entry.Role;
import com.Master5.main.web.role.service.RoleService;
import com.Master5.main.web.user.entry.User;
import com.Master5.main.web.user.service.UserService;

@Controller
@RequestMapping(value = "login")
public class Login {

	private static final Logger logger = LoggerFactory.getLogger(Login.class);

	@Autowired
	UserService userService;

	@Autowired
	PermissionService permissionService;

	@Autowired
	RoleService roleService;

	/**
	 * 登录主页面
	 */
	@RequiresGuest
	@RequestMapping(value = "list")
	public String login() {

		return "/login/regist";
	}

	/**
	 * 登录处理方法
	 */
	@RequestMapping(value = "loging", method = RequestMethod.POST)
	public String login(@ModelAttribute User loginUser, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		
		List<String> msgList = new ArrayList<String>();
		
		loginUser.setPass( MD5.getMD5Pass( loginUser.getPass() ));

		// subject理解成权限对象。类似user
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		// 创建用户名和密码的令牌
		UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getName(), loginUser.getPass() );
		// 记录该令牌，如果不记录则类似购物车功能不能使用。
		token.setRememberMe(true);
		subject.getPrincipal();
		try {
			subject.login(token);
			loginUser = userService.findByName(loginUser.getName());
			// 验证是否成功登录的方法
			if (subject.isAuthenticated()) {
				msgList.add("欢迎回来" + loginUser.getNickName());
				loginUser.setIp(IPTools.getClientIp(request));
				session.setAttribute(Key.LOGINED, loginUser);
				redirectAttributes.addFlashAttribute(MsgKey.msg, msgList);
				logger.info("登陆成功："+loginUser.getNickName());
				return "redirect:/user/info";
			}
		} catch (UnknownAccountException ex) {
			msgList.add("用户不存在");
		} catch (IncorrectCredentialsException ex) {
			msgList.add("密码错误");
		} catch (AuthenticationException ex) {
			msgList.add("服务器繁忙");
		} catch (Exception e) {
			msgList.add("未知错误");
		}

		redirectAttributes.addFlashAttribute(MsgKey.msg, msgList);
		return "redirect:/login/list";

	}

	@RequestMapping(value = "init")
	public String initSystem(RedirectAttributes redirectAttributes) {

		List<String> msgList = new ArrayList<String>();

		String pack = "com.Master5.main.web";

		if (null != roleService.findByState(Key.STATE_DEFAULT_ADMIN)) {
			msgList.add(Key.SYSTEM_INIT_FAILY);
			redirectAttributes.addFlashAttribute(Key.msg, msgList);
			return "redirect:/login/list";
		}
		Set<Class<?>> classes = GetClassByPackage.getInstance().getClasses(pack);
		Set<Permission> permissions = GetValueFromAnnotation.getInstance().getPermissions(classes);
		permissionService.save(permissions);
		Role role = new Role(Key.ROLE_DEFAULT_ADMIN,
				new HashSet<Permission>(permissionService.findByStateLessThanEqual(Key.STATE_DEFAULT_ADMIN)));
		role.setState(Key.STATE_DEFAULT_ADMIN);
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleService.save(role));
		User user = new User(Key.ROLE_DEFAULT_ADMIN, Key.ADMIN_DEFAULT_NAME, MD5.getMD5Pass( Key.ADMIN_DEFAULT_PASS ),
				Key.ADMIN_DEFAULT_EMAIL, Key.SEX_MAN);
		user.setRoles(roles);
		user.setState(Key.STATE_DEFAULT_ADMIN);
		userService.save(user);
		role = new Role(Key.ROLE_DEFAULT_USER,
				new HashSet<Permission>(permissionService.findByStateLessThanEqual(Key.STATE_DEFAULT_USER)));
		role.setState(Key.STATE_DEFAULT_USER);
		roleService.save(role);
		role = new Role(Key.ROLE_DEFAULT_DARK,
				new HashSet<Permission>(permissionService.findByStateLessThanEqual(Key.STATE_DEFAULT_DARK)));
		role.setState(Key.STATE_DEFAULT_DARK);
		roleService.save(role);

		msgList.add(Key.SYSTEM_INIT_SUCCESS);
		redirectAttributes.addFlashAttribute(Key.msg, msgList);
		return "redirect:/login/list";
	}

}
