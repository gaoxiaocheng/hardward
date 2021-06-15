package com.security.fiter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("shiro.promiss")
public class ShiroPromissConfig {
    private List<String> anon;
    private List<String> authc;
    private List<String> user;
    private List<String> perms;
    private List<String> role;
    private String logurl;
    private String sucessurl;
    private String error;

    public List<String> getAnon() {
        return anon;
    }

    public void setAnon(List<String> none) {
        this.anon = none;
    }

    public List<String> getAuthc() {
        return authc;
    }

    public void setAuthc(List<String> authc) {
        this.authc = authc;
    }

    public List<String> getUser() {
        return user;
    }

    public void setUser(List<String> user) {
        this.user = user;
    }

    public List<String> getPerms() {
        return perms;
    }

    public void setPerms(List<String> perms) {
        this.perms = perms;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public String getLogurl() {
        return logurl;
    }

    public void setLogurl(String logurl) {
        this.logurl = logurl;
    }

    public String getSucessurl() {
        return sucessurl;
    }

    public void setSucessurl(String sucessurl) {
        this.sucessurl = sucessurl;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
