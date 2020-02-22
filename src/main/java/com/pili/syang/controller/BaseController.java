package com.pili.syang.controller;

import com.pili.syang.entity.BaseInfo;
import com.pili.syang.entity.User;
import com.pili.syang.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected UserUtil userUtil;

}
