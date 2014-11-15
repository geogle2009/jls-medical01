package com.medical.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.medical.dto.UserInfoDTO;
import com.medical.service.SystemManager;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -7775299532450817539L;
	private SystemManager systemManager;

	@SuppressWarnings("rawtypes")
	public String intercept(ActionInvocation invocation) throws Exception {

		ActionContext ctx = invocation.getInvocationContext();

		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		System.out.println(request.getServletPath());

		Map session = ctx.getSession();

		UserInfoDTO userInfoDTO = (UserInfoDTO) session.get("user");

		if (null == userInfoDTO) {
			return Action.LOGIN;
		} else {

			boolean flag = systemManager.findUserRoleUnuse(
					request.getServletPath(), userInfoDTO.getEmpId(),
					userInfoDTO.getOrganizationId());

			if (flag) {

				return invocation.invoke();
			} else {
				return "norole";
			}
		}
	}

	public SystemManager getSystemManager() {
		return systemManager;
	}

	public void setSystemManager(SystemManager systemManager) {
		this.systemManager = systemManager;
	}

}
