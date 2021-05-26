package br.com.personal.loginsecurity.securityMySql.controller;

import br.com.personal.loginsecurity.securityMySql.model.User;
import br.com.personal.loginsecurity.securityMySql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findByUserName(user.getUserName());
        if(userExists != null){
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided!");
        }
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("registration");
            return modelAndView;
        }
        userService.saveUser(user);
        modelAndView.addObject("successMessage", "User has been registered successfull");
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("registration");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.findByUserName(auth.getName());
        modelAndView.addObject("userName",
                "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + "(" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Content available only for users with ADMIN Role");
        return modelAndView;
    }


}
