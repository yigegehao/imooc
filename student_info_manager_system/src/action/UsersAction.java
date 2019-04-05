package action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ModelDriven;

import entity.Users;
import service.UserDao;
import service.impl.UserDaoImpl;

public class UsersAction extends SuperAction implements ModelDriven<Users>{
	
	private Users user = new Users();
	
	@Override
	public Users getModel() {
		return user;
	}
	
	//用户登录动作
	public String login() {
		System.out.println("------------login-------------------");
		UserDao userDao = new UserDaoImpl();
		if(userDao.userLogin(user)) {
			session.setAttribute("loginUserName", user.getUsername());
			return "login_success";
		}
		return "login_failure";
	}
	//用户注销
	@SkipValidation
	public String logout() {
		if(session.getAttribute("loginUserName") != null) {
			session.removeAttribute("loginUserName");
		}
		return "logout_success";
	}
	//表单验证
	/*
	 * @Override public void validate() { if("".equals(user.getUsername().trim())) {
	 * this.addFieldError("usernameError", "用户名不能为空"); }
	 * if(user.getPassword().length() < 6) { this.addFieldError("passwordError",
	 * "密码不能为空"); } }
	 */
	
}
