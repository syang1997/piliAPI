package com.pili.syang.util;

import com.pili.syang.entity.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserUtil {
    public User getUser(HttpServletRequest request){
        return (User) request.getSession().getAttribute("user");
    }
}
