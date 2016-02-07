package com.evgo.controller;

import com.evgo.model.Users;
import com.evgo.repository.UserDao;
import org.apache.catalina.connector.Request;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionEventListenerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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
        String valType = validateUser(users);
        if(valType.equals("OK")) {
            userDao.userRegister(users);
            return true;
        }
        return false;
    }

    private String validateUser(Users users){
        if(users.getName().length() < 2){
            return "Not Enough name Length!";
        }

        if(users.getPassword().length() < 4){
            return "password must be greater 4";
        }

        return "OK";

    }


    @Autowired
    HttpSession session;

    @RequestMapping(value="/sing", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Users singIn(@RequestBody Users users) {
        Users user = userDao.getUserByNamePass(users.getName(),users.getPassword());
        if(user == null){
            return null;
        }
        System.out.println("user " + user.getName() + " id " + user.getId());
        session.setAttribute("userId",user.getId());
        return user;
    }


    @RequestMapping(value="/update", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Users updateParam(@RequestBody Users users) {
        System.out.println(session.getAttribute("userId"));
        Integer id = (Integer)session.getAttribute("userId");
        Users user = userDao.updateUser(users,id);
        System.out.println(id);
        if(user == null){
            return null;
        }
        return user;
    }

    @RequestMapping(value="/logout")
    @ResponseBody
    public boolean logout(){
        session.setAttribute("userId",null);
        return true;
    }





}
