package com.pili.syang.controller;

import com.pili.syang.BaseInfo;
import com.pili.syang.entity.User;
import com.pili.syang.enums.StatusCode;
import com.pili.syang.repository.UserRepository;
import com.pili.syang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/findAll")
    public List<User> findAll() {
        return userService.ADallUser();
    }

    @PostMapping("/login")
    public BaseInfo login(@RequestBody User user) {
        User login = userService.login(user);
        if (login != null) {
            login.setPassword("secret");
//            login.setVideos(null);
            return BaseInfo.BackInfo(StatusCode.SUCCESS, login);
        } else {
            return BaseInfo.BackInfo(StatusCode.WONG_PASSWORD, null);
        }
    }

    @PostMapping("/register")
    public BaseInfo register(@RequestBody User user) {
        user.setSex("男");
        user.setImage("http://localhost:8181/uploads/images/default.png");
        int regsiter = userService.regsiter(user);
        if (regsiter == 1) {
            User login = userService.login(user);
            if (login != null) {
                response.addCookie(new Cookie("token", login.getToken()));
                login.setPassword("secret");
                return BaseInfo.BackInfo(StatusCode.SUCCESS, login);
            } else {
                return BaseInfo.BackInfo(StatusCode.WONG_PASSWORD, null);
            }
        }
        if (regsiter == 0) {
            return BaseInfo.BackInfo(StatusCode.EMAIL_USED, null);
        }
        return BaseInfo.BackInfo(StatusCode.UNKNOWN_ERROR, null);
    }

    @GetMapping("/logout")
    public BaseInfo logout() {
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return BaseInfo.BackInfo(StatusCode.SUCCESS, null);
    }

    @PostMapping("/token")
    public BaseInfo chaToken(@RequestBody User token){
        User user = userService.queryByToken(token.getToken());
        if (user!=null){
            user.setPassword("secret");
//            user.setVideos(null);
            return BaseInfo.BackInfo(StatusCode.SUCCESS, user);
        }
        return BaseInfo.BackInfo(StatusCode.NEED_LOGIN, null);
    }


    @PostMapping("/updata")
    public BaseInfo updataUser(@RequestBody User user){
        User user1 = userService.queryByToken(user.getToken());
        if (user1==null){
            return BaseInfo.BackInfo(StatusCode.NEED_LOGIN, null);
        }
        user1.setSex(user.getSex());
        user1.setName(user.getName());
        User user2 = userService.updataUser(user1);
        if (user2!=null){
//            user2.setVideos(null);
            return BaseInfo.BackInfo(StatusCode.SUCCESS,user2);
        }
        return BaseInfo.BackInfo(StatusCode.UNKNOWN_ERROR,null);
    }

    @PostMapping("/import")
    public BaseInfo importData(MultipartFile file,Integer uid) throws IOException {
        String realPath = "D:\\test\\images\\" ;
        if (uid==null){
            return BaseInfo.BackInfo(StatusCode.NEED_LOGIN, null);
        }
        User one = userService.findOne(uid);
        if (one==null){
            return BaseInfo.BackInfo(StatusCode.UNKNOWN_ERROR,null);
        }
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        file.transferTo(new File(folder,newName));
        String url = "http://localhost:8181/upload/images/" + newName;
        one.setImage(url);
        userService.updataUser(one);
        return BaseInfo.BackInfo(StatusCode.SUCCESS,url);
    }
}
