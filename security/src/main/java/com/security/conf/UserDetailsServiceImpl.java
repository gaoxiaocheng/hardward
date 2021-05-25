package com.security.conf;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.machine.collect.entity.SecurityUser;
import com.machine.collect.entity.UserRole;
import com.machine.collect.mapper.SecurityUserMapper;
import com.machine.collect.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private SecurityUserMapper securityUserMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s == null || "".equals(s)) {
            throw new RuntimeException("用户不能为空");
        }
        // 调用方法查询用户
        QueryWrapper<SecurityUser> wrapper = new QueryWrapper<>();
        wrapper.eq("code", s);
        SecurityUser user = securityUserMapper.selectOne(wrapper);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        wrapper.eq("user_code", s);
        List<UserRole> roles = userRoleMapper.selectList(userRoleQueryWrapper);
        for (UserRole role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleCode()));
        }

        return new org.springframework.security.core.userdetails.User(user.getCode(), "{noop}" + user.getPassword(), authorities);
    }
}