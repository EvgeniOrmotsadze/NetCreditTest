package com.evgo.controller;

import com.evgo.model.Users;
import com.evgo.repository.DAO.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by root_pc on 2/6/2016.
 */



@Controller
@RequestMapping(value="/netcredit")
public class MainController {


    @RequestMapping(value="/")
    @ResponseBody
    public String home() {
        return "home.html";
    }


    @Autowired
    UserDao userDao;

    @RequestMapping(value="/register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public boolean create(@RequestBody Users users) {
        userDao.userRegister(users);
        return true;
    }

    @RequestMapping(value="/sing", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Users singIn(@RequestBody Users users) {
        Users user = userDao.getUserByNamePass(users.getName(),users.getPassword());
        if(user == null){
            return null;
        }
        return user;
    }


    @RequestMapping(value="/update", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Users updateParam(@RequestBody Users users) {
        Users user = userDao.updateUser(users);
        if(user == null){
            return null;
        }
        return user;
    }





}
