package com.machine.collect.service.impl;

import com.core.utils.license.LicenseUtils;
import com.machine.collect.entity.OlapLicense;
import com.machine.collect.mapper.OlapLicenseMapper;
import com.machine.collect.service.IOlapLicenseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gaoyapeng
 * @since 2021-06-30
 */
@Service
public class OlapLicenseServiceImpl extends ServiceImpl<OlapLicenseMapper, OlapLicense> implements IOlapLicenseService {

    private static final String seed = "T*P%NT^X%&IN9-AREXU#3*&KJ$M~QKIF=+^+S^DMTAKVRJRHOI";
    @Override
    public byte[] generateLicense(String name, String code, String ip) throws Exception {
        OlapLicense olapLicense = new OlapLicense();
        olapLicense.setCode(code);
        olapLicense.setName(name);
        olapLicense.setIp(ip);
        File file = LicenseUtils.s_generate(seed, olapLicense.getCode());
        try(FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream()){
            olapLicense.setFile(new byte[(int)file.length()]);

            int n;
            while ((n = fis.read(olapLicense.getFile())) != -1)
            {
                bos.write(olapLicense.getFile(), 0, n);
            }
            getBaseMapper().save(olapLicense);
        }catch (Exception e){
            log.error("",e);
            olapLicense.setFile(null);
        }finally {
            file.delete();
        }

        return olapLicense.getFile();
    }

    @Override
    public byte[] getLicenseFile(Integer id) {
        OlapLicense olapLicense = getBaseMapper().selectById(id);
        return olapLicense.getFile();
    }
}
