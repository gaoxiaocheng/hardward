package com.machine.collect.controller;


import com.machine.collect.entity.HardwardDefinition;
import com.machine.collect.service.IHardwardDefinitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gaoyapeng
 * @since 2021-05-24
 */
@RestController
@Api(value = "hardward", tags = "硬件注册地址")
@RequestMapping("/hardward-definition")
public class HardwardDefinitionController {
    @Autowired
    IHardwardDefinitionService hardwareDefinitionService;
    @ApiOperation("硬件查询入口")
    @RequestMapping(value="find/{id}",method = RequestMethod.POST)
    public void find(String id){}

    @ApiOperation("硬件定义入口")
    @RequestMapping(value="save",method = RequestMethod.POST)
    public void save(HardwardDefinition hardwardDefinition){
        hardwareDefinitionService.save(hardwardDefinition);
    }


}
