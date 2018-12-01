package ydj.project.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ydj.project.springboot.VO.User;
import ydj.project.springboot.repository.MainRepository;

import javax.servlet.http.HttpSession;

/**
 * Created by djyoon on 2018-11-20.
 */
@Controller
public class MainController {

    @Autowired
    private MainRepository mainRepository;

    @GetMapping("/")
    public String hello(){
        return "/index";
    }

    @PostMapping("/user/login")
    public String userLogin(User tempUser, HttpSession session){

        User user = mainRepository.findByUserId(tempUser.getUserId());

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
        mainRepository.save(user);
        session.setAttribute("loginUser", user);
        return "redirect:/";
    }


    @GetMapping("/user/list")
    public String userList(Model model) {

        model.addAttribute("members",mainRepository.findAll());

        return "/user/list";
    }
}
