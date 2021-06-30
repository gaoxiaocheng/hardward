package com.machine.collect.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.core.utils.ResultUtils;
import com.machine.collect.entity.OlapLicense;
import com.machine.collect.service.IOlapLicenseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gaoyapeng
 * @since 2021-06-30
 */
@RestController
@Api(value = "/olap-license", tags = "license控制器")
@RequestMapping("/olap-license")
public class OlapLicenseController {
    static HttpHeaders headers;
    {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "application.license");
    }
    @Resource
    private IOlapLicenseService olapLicenseService;

    @ApiOperation("列表")
    @RequestMapping(value="/listLicense")
    public Object listLicense(String name) {
        QueryWrapper<OlapLicense> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        List<OlapLicense> olapLicenses = olapLicenseService.list(queryWrapper);
        return ResultUtils.sucess("sucess",olapLicenses);
    }
    @ApiOperation("保存并下载")
    @RequestMapping(value="/generateLicense")
    public Object generateLicense( String name, String code, String ip) throws Exception {
        return new ResponseEntity<>(olapLicenseService.generateLicense(name, code, ip), headers, HttpStatus.OK);
    }
    @ApiOperation("下载")
    @RequestMapping(value="/getLicenseFile")
    public Object getLicenseFile(Integer id) {
        return new ResponseEntity<>(olapLicenseService.getLicenseFile(id), headers, HttpStatus.OK);
    }

}
