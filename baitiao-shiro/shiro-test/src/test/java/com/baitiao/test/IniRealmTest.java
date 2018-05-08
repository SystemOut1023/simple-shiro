package com.baitiao.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class IniRealmTest {
	@Test
	public void testIniRealm(){
		IniRealm iniRealm = new IniRealm("classpath:user.ini");
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		defaultSecurityManager.setRealm(iniRealm);
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("admin","123456");
		subject.login(token);
		//验证用户是否认证
		System.out.println(subject.isAuthenticated());
		//验证用户是否授权
		subject.checkRole("admin");
		//该用户拥有删除用户的权限
		subject.checkPermission("user:update");
	}
}

