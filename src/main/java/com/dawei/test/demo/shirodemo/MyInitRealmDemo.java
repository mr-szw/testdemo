package com.dawei.test.demo.shirodemo;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * @author by Dawei on 2018/7/20.
 * 自定义Realm
 */
public class MyInitRealmDemo extends AuthorizingRealm {


    /* 做授权 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        /* 从认证信息中获取用户信息*/
        String userName = principalCollection.getPrimaryPrincipal().toString();
        /* 通过用户名获取角色 从自定义的数据源中 */
        Set<String> roles = new HashSet<>();

        /* 通过角色获取权限 从自定义的数据源中 */
        Set<String> permissions = new HashSet<>();

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);

        return simpleAuthorizationInfo;
    }

    /* 做认证 */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //1、从主题中获取认证信息 获取用户名
        String userName = authenticationToken.getPrincipal().toString();

        //2、通过用户名获取认证凭证信息
        //自定义的凭证获取方式 自定义的数据源
        String token = "";


        return new SimpleAuthenticationInfo("Dawei", token, "myInitRealm");
    }
}
