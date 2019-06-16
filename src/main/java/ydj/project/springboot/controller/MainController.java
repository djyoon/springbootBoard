package ydj.project.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ydj.project.springboot.VO.Question;
import ydj.project.springboot.VO.User;
import ydj.project.springboot.repository.BoardRepository;
import ydj.project.springboot.repository.MainRepository;
import ydj.project.springboot.service.MainService;

import javax.servlet.http.HttpSession;

/**
 * Created by djyoon on 2018-11-20.
 */
@Controller
public class MainController {


    @Autowired
    private MainService mainService;

    @GetMapping("/")
    public String hello(HttpSession session){

        if( session.getAttribute("isTest") == null ) {
            mainService.saveMain(new User("test","test","테스트용계정"));
            for(int i=0; i<30 ; i++){
                mainService.saveBoard(new Question("djyoon",i+"번 게시글",i+"번 게시글"));
            }
            session.setAttribute("isTest",true);
        }

        return "/index";
    }

    @PostMapping("/user/login")
    public String userLogin(User tempUser, HttpSession session){

        User user = mainService.findByUserId(tempUser.getUserId());

        if( user == null){
            return "redirect:/";
        } else if (!user.getPassWd().equals(tempUser.getPassWd())) {
            return "redirect:/";
        } else {
            System.out.println("Login success");
            session.setAttribute("loginUser",user);
            return "redirect:/";
        }
    }

    @GetMapping("/user/logout")
    public String userLogOut(User tempUser, HttpSession session){
        session.removeAttribute("loginUser");
        return "redirect:/";
    }

    @PostMapping("/user/join")
    public String userJoin(User user, HttpSession session){
        mainService.saveMain(user);
        session.setAttribute("loginUser", user);
        return "redirect:/";
    }

    @GetMapping("/user/valid/{userId}")
    @ResponseBody
    public ResponseEntity userIdCheck(@PathVariable String userId){

        User user = null;

        try{
            user = mainService.findByUserId(userId);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(user != null){
                return new ResponseEntity("exist", HttpStatus.OK);
            } else {
                return new ResponseEntity("ok",HttpStatus.OK);
            }
        }
    }
}
