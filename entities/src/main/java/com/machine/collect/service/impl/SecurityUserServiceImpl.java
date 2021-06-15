package com.machine.collect.service.impl;

import com.machine.collect.entity.SecurityUser;
import com.machine.collect.mapper.SecurityUserMapper;
import com.machine.collect.service.ISecurityUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gaoyapeng
 * @since 2021-05-25
 */
@Service
public class SecurityUserServiceImpl extends ServiceImpl<SecurityUserMapper, SecurityUser> implements ISecurityUserService {

    @Override
    public SecurityUser getUserByCode(String code) {
        return null;
    }
}
