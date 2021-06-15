package com.security.fiter;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.machine.collect.entity.SecurityUser;
import com.machine.collect.service.ISecurityUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

//继承AuthorizingRealm
public class UserRealm extends AuthorizingRealm {
    @Autowired
    ISecurityUserService securityUserService;

    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        return null;
    }

    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取token
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        QueryWrapper<SecurityUser> wrapper = new QueryWrapper<>();
        wrapper.eq("code",token.getUsername());

        SecurityUser user = securityUserService.getById(1);
        //1、判断用户名
        if (user == null) {
            //用户名不存在，shiro底层会抛出UnKnowAccountException
            return null;
        }
        if(!String.valueOf(token.getPassword()).equals(user.getPassword())){
            return null;
        }
        //2、自动判断密码,第1个为Principal，第二位正确的密码，第三为shiro名字
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }


}
