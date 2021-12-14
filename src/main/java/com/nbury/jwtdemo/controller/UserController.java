package com.nbury.jwtdemo.controller;

import com.nbury.jwtdemo.entity.User;
import com.nbury.jwtdemo.utils.JwtUtil;
import com.nbury.jwtdemo.utils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuhuacheng
 * @version 1.0
 * @description com.nbury.jwtdemo.controller
 * @date 2021/12/14
 */
@RestController
@RequestMapping("/admin")
public class UserController {


    @PostMapping("/login")
    public R login(@RequestBody User user) {
        if ("abc".equals(user.getUsername()) && "123".equals(user.getPassword())) {

            String jwt = JwtUtil.generalToken(user);

            return R.ok().data("token", jwt);
        }else {
            return R.error();
        }
    }
}
