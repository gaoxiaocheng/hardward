package com.machine.collect.mapper;

import com.machine.collect.entity.OlapLicense;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gaoyapeng
 * @since 2021-06-30
 */
public interface OlapLicenseMapper extends BaseMapper<OlapLicense> {

    void save(OlapLicense olapLicense);
}
