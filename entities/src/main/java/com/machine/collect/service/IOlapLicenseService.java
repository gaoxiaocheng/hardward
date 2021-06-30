package com.machine.collect.service;

import com.machine.collect.entity.OlapLicense;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gaoyapeng
 * @since 2021-06-30
 */
public interface IOlapLicenseService extends IService<OlapLicense> {
    public byte[] generateLicense(String name, String code, String ip) throws Exception;
    public byte[] getLicenseFile(Integer id);
}
