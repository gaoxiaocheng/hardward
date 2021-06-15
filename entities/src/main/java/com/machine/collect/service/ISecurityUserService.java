package com.machine.collect.service;

import com.machine.collect.entity.SecurityUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gaoyapeng
 * @since 2021-05-25
 */
public interface ISecurityUserService extends IService<SecurityUser> {
    SecurityUser getUserByCode(String code);

}
