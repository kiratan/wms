
package com.Master5.main.web.login;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Master5.main.annotation.annotationtools.GetValueFromAnnotation;
import com.Master5.main.utils.GetClassByPackage;
import com.Master5.main.utils.IPTools;
import com.Master5.main.utils.ValidateTools;
import com.Master5.main.utils.constant.Key;
import com.Master5.main.utils.constant.MsgKey;
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

		// subject理解成权限对象。类似user
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		// 创建用户名和密码的令牌
		UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getName(), loginUser.getPass());
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
		return "/login/regist";

	}

	/**
	 * 异步验证注册数据的正确性
	 */
	@RequestMapping(value = "checkRegAjax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> checkRegAjax(String nickName, String name, String pass, String pass2, String email,
			String sex) {

		return checkReg(nickName, name, pass, pass2, email, sex);
	}

	/**
	 * 检查注册的各个字段的合法性
	 */
	private Map<String, String> checkReg(String nickName, String name, String pass, String pass2, String email,
			String sex) {

		Map<String, String> errMap = new HashMap<String, String>();

		if (null == nickName || nickName.length() > 8 || nickName.length() < 2)
			errMap.put("nickNameErr", "昵称长度必须2-10");
		if (null == name || name.length() > 18 || name.length() < 5)
			errMap.put("nameErr", "用户名的长度必须在6到18之间");
		if (null == pass || pass.length() > 18 || pass.length() < 5)
			errMap.put("passErr", "密码的长度必须在6到18之间");

		if (!ValidateTools.userName(name))
			errMap.put("nameErr", "用户名只能为英文和数字");
		if (!ValidateTools.nickName(nickName))
			errMap.put("nickNameErr", "昵称只能为英文数字和汉字");
		if (!ValidateTools.password_reg(pass))
			errMap.put("passErr", "密码只能为英文字母和数字");
		if (null == email || !ValidateTools.Email(email))
			errMap.put("emailErr", "邮箱格式不正确");

		if (null != userService.findByNickName(nickName))
			errMap.put("nickNameErr", "昵称已经存在");
		if (null != userService.findByName(name))
			errMap.put("nameErr", "用户名已经存在");
		if (null != userService.findByEmail(email))
			errMap.put("emailErr", "邮箱已经存在");

		if (null == pass2 || !pass.equals(pass2))
			errMap.put("pass2Err", "两次输入的密码不一样");

		if (errMap.size() == 0) {
			User user = userService.save(new User(nickName, name, pass, email, sex));

			int year = Calendar.getInstance().get(Calendar.YEAR);
			int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
			int day = Calendar.getInstance().get(Calendar.DATE);

			// EmailTools.sendTxtMail( encryptTools.getEncodeStr( new int[ ] {
			// (int) user.getId() ,
			// year * 10000 + month * 100 + day } ) , email );
			errMap.put("regForm", "可爱的<strong>" + nickName + "</strong>恭喜你注册成功。随后我们将验证爱卿的邮箱~");
		}

		return errMap;

	}

	@RequestMapping(value = "/checkEmail/{hash}", method = RequestMethod.GET)
	public String checkEmail(@PathVariable String hash, RedirectAttributes attr) {

		try {

			int[] code = encryptTools.getDecodeStr(hash);

			int time = Calendar.getInstance().get(Calendar.YEAR) * 1000
					+ (Calendar.getInstance().get(Calendar.MONTH) + 1) * 100
					+ Calendar.getInstance().get(Calendar.DATE);

			if (time - code[1] > 3) {
				attr.addFlashAttribute("msg", "验证邮箱已过期~请重新验证");
			} else {

				Role role = roleService.findById(2);

				Set<Role> roles = new HashSet<Role>();
				roles.add(role);

				User user = userService.findById((long) code[0]);
				user.setState(1);
				userService.save(user);
				user.setRoles(roles);

				attr.addFlashAttribute("msg", "验证邮箱成功~已经为你开放部分有趣功能 请登录后使用");
			}
		} catch (Exception e) {
			logger.info("error：邮件验证", e);
		}
		return "redirect:/menu/list";
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
		User user = new User(Key.ROLE_DEFAULT_ADMIN, Key.ADMIN_DEFAULT_NAME, Key.ADMIN_DEFAULT_PASS,
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
