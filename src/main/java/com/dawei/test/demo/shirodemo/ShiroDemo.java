package com.dawei.test.demo.shirodemo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @author by Dawei on 2018/6/5.
 */
public class ShiroDemo {


    //Realm 相当于数据源 用于获取获取或者存储认证数据信息
    private SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    /* 向realm 中设置预留参数 */
    @Before
    public void addUserInfo() {
        simpleAccountRealm.addAccount("Dawei", "aydl");
        // 参数3 添加授权参数
        simpleAccountRealm.addAccount("Dawei1", "aydl", "admin");
    }
    @Test
    public void testMethod() {


        /*##################################  认证过程 ########################################*/
        //1、构建SecurityManager 环境 创建SecurityManager对象
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        //向SecurityManager的认证环境中设置realm
        defaultSecurityManager.setRealm(simpleAccountRealm);


        //2、主体提交认证请求
        // 1)、设置SecurityUtils 的 SecurityManager环境
        // 2)、通过工具类获取主体
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        //3、提交认证 提交认证信息
       // UsernamePasswordToken token = new UsernamePasswordToken("Dawei", "aydl");

        //提交认证

        UsernamePasswordToken token1 = new UsernamePasswordToken("Dawei1", "aydl");
       // subject.login(token);
        subject.login(token1);
        //判断认证结果
        System.out.println("isAuthenticated : " + subject.isAuthenticated());

        /*##################################  授权部分 ########################################*/
        //校验授权信息
        subject.checkRole("admin");







        //4、退出认证
        subject.logout();
        //判断认证结果
        System.out.println("isAuthenticated : " + subject.isAuthenticated());


    }
}
