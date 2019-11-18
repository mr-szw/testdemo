package com.dawei.test.demo.shirodemo;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author by Dawei on 2018/6/5.
 */
public class IniRealmDemo {


    @Test
    public void iniRealmTest() {
        IniRealm iniRealm = new IniRealm("classpath:shiro/iniShiro.ini");

        /*##################################  校验 ########################################*/
        //1、构建SecurityManager 环境 创建SecurityManager对象
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //向SecurityManager的认证环境中设置realm
        defaultSecurityManager.setRealm(iniRealm);
        //2、主体提交认证请求
        // 1)、设置SecurityUtils 的 SecurityManager环境
        // 2)、通过工具类获取主体
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        //3、提交认证 提交认证信息
        UsernamePasswordToken token = new UsernamePasswordToken("Dawei", "aydl");
        subject.login(token);
        //判断认证结果
        System.out.println("isAuthenticated : " + subject.isAuthenticated());

        /*##################################  授权部分 ########################################*/
        //校验授权信息
        subject.checkRole("guest");
        //授予权限是否包含 某种权限
        subject.checkPermission("user:see");


    }

}
