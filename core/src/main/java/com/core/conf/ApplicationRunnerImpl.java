package com.core.conf;

import com.core.utils.crypto.B64Utils;
import com.core.utils.crypto.RSAUtils;
import com.core.utils.license.LicenseSequences;
import com.core.utils.license.LicenseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;

@Configuration
public class ApplicationRunnerImpl implements ApplicationRunner {
    protected static Logger logger = LoggerFactory.getLogger(ApplicationRunnerImpl.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            File file = ResourceUtils.getFile("classpath:application.license");
            String[] txt = LicenseUtils.s_read_license(file);
            byte[] bytes0 = B64Utils.s_decode(txt[0]);
            byte[] bytes1 = B64Utils.s_decode(txt[1]);
            String d_txt = LicenseUtils.s_decode_string(RSAUtils.s_decrypt_public(bytes0, bytes1))[1];
            String s = LicenseSequences.s_sequence();

            if (!d_txt.equals(s)) {
                logger.error("****" + d_txt + "****");
                logger.error("****" + s + "****");

                logger.error("License文件经验证结果：失败");
                System.exit(-1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
