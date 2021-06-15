package com.security.fiter;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Autowired
    ShiroPromissConfig shiroPromissConfig;

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }



    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

         List<String> anon = shiroPromissConfig.getAnon();
         List<String> authc = shiroPromissConfig.getAuthc();
         List<String> user = shiroPromissConfig.getUser();
         List<String> perms = shiroPromissConfig.getPerms();
         List<String> role = shiroPromissConfig.getRole();
         String logurl = shiroPromissConfig.getLogurl();
         String sucessurl = shiroPromissConfig.getSucessurl();
         String error = shiroPromissConfig.getError();
       /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *  常用的过滤器：
         *      anon: 无需认证（登录）可以访问
         *      authc: 必须认证才可以访问
         *      user: 如果使用rememberMe功能可以直接访问
         *      perms: 该资源必须得到资源权限才可以访问
         *      role: 该资源必须得到角色权限才可以访问
         */
        Map<String, String> filerMap = new LinkedHashMap<>();
        if(anon != null){
            for(String url : anon){
                filerMap.put(url,"anon");
            }
        }
        if(user != null){
            for(String url : user){
                filerMap.put(url,"user");
            }
        }
        if(perms != null){
            for(String url : perms){
                filerMap.put(url,"perms");
            }
        }
        if(role != null){
            for(String url : role){
                filerMap.put(url,"role");
            }
        }
        if(authc != null){
            for(String url : authc){
                filerMap.put(url,"authc");
            }
        }
        //注意直接通过登录请求！！！
//        filerMap.put("/security-user/login","anon");
//        filerMap.put("/static/**", "anon");
//        //被shiro拦截的swagger资源放行
//        filerMap.put("/doc.html", "anon");
//        filerMap.put("/doc.html/**", "anon");
//        filerMap.put("/swagger-resources/**", "anon");
//        filerMap.put("/v2/api-docs/**", "anon");
//        filerMap.put("/webjars/**", "anon");
//        filerMap.put("/configuration/security", "anon");
//        filerMap.put("/configuration/ui", "anon");
//        filerMap.put("/swagger-resources/configuration/ui/**", "anon");
//        filerMap.put("/swagger-resources/configuration/security/**", "anon");
        
        //认证过滤器
//        filerMap.put("/testThymeleaf","authc");
//        filerMap.put("/**","authc"); //全部但不包含前面已经指定的页面，第一次匹配优先
		//设置登录的页面！！！
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filerMap);
        if(StringUtils.isNotBlank(logurl)){
            shiroFilterFactoryBean.setLoginUrl(logurl);
        }
        if(StringUtils.isNotBlank(sucessurl)){
            shiroFilterFactoryBean.setSuccessUrl(sucessurl);
        }
        if(StringUtils.isNotBlank(error)){
            shiroFilterFactoryBean.setUnauthorizedUrl(error);
        }

        
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        return shiroFilterFactoryBean;
    }
    
    /**
     * 创建DefaultWebSecurityManager安全管理器
     */
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联Reaml
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }
    
    /**
     * 创建Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getReaml(){
        return new UserRealm();
    }
}
