package com.machine.collect.controller;


import com.machine.anno.PassToken;
import com.machine.anno.UserLoginToken;
import com.machine.collect.service.ISecurityUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gaoyapeng
 * @since 2021-05-25
 */
@RestController
@Api(value = "security-user", tags = "用户操作")
@RequestMapping("/security-user")
public class SecurityUserController {

    @Autowired
    ISecurityUserService securityUserService;

    //登录
    @PostMapping("/login")
    @ApiOperation("用户登录")
    @PassToken
    public String login(String name, String password, Model model) {

        //1、获取subject
        Subject subject = SecurityUtils.getSubject();
        //2、封装用户数据
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        //3、执行登录方法
        try {
            //交给config中的Realm处理--->执行它的认证方法
            subject.login(token);
            //登录成功
            return "redirect:/testThymeleaf";
        } catch (UnknownAccountException e) {
            //登录失败:用户名不存在
            model.addAttribute("msg", "用户名不存在");
            return "user/login";
        } catch (IncorrectCredentialsException e) {
            //登录失败：密码错误
            model.addAttribute("msg", "密码错误");
            return "user/login";
        }


    }

    @UserLoginToken
    @ApiOperation("测试")
    @GetMapping("/getMessage")
    public String getMessage() {
        return "sdfsafafsf";
    }

}
