package com.medical.action;

import com.medical.dto.UserInfoDTO;
import com.medical.service.SystemManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 7872753307947389015L;
	private SystemManager systemManager;
	private UserInfoDTO userinfo;

	public String login() {
		userinfo = systemManager.validate(userinfo);
		if (null != userinfo) {
			ActionContext.getContext().getSession().put("user", userinfo);
			ActionContext.getContext().getSession().put("username",
					userinfo.getUsername());
			return SUCCESS;
		} else {
			this.addActionError("用户名密码错误");
			return ERROR;
		}
	}

	public void validateLogin() {
		clearErrorsAndMessages();
		String password = userinfo.getPassword();
		String username = userinfo.getUsername();
		if (null == username || "".equals(username)) {
			this.addActionError("用户名不能为空");
		}
		if (null == password || "".equals(password)) {
			this.addActionError("密码不能为空");
		}
	}

	public void validate() {

	}

	public void setSystemManager(SystemManager systemManager) {
		this.systemManager = systemManager;
	}

	public UserInfoDTO getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfoDTO userinfo) {
		this.userinfo = userinfo;
	}
}