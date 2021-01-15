package com.udemy.controller;

import com.udemy.model.UserCredential;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private static final Log LOG = LogFactory.getLog(LoginController.class);

    @GetMapping("/")
    public String redirectToLogin(){
        LOG.info("METHOD: redirectToLogin()");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String ShowLoginForm(Model model,
                                @RequestParam(name="error", required=false) String error,
                                @RequestParam(name="logout", required=false) String logout){
        LOG.info("METHOD: ShowLoginForm() -- PARAMS: error=" + error + ", logout:" + logout);
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        model.addAttribute("userCredentials", new UserCredential());
        LOG.info("Returning to login view");
        return "login";
    }

    @PostMapping("/logincheck")
    public String loginCheck(@ModelAttribute(name="userCredentials") UserCredential userCredential){
        LOG.info("METHOD: loginCheck() -- PARAMS: " + userCredential.toString());
        if(userCredential.getUsername().equals("user") && userCredential.getPassword().equals("user")) {
            LOG.info("Returning to contacts view");
            return "contacts";
        }
        LOG.info("Redirect to login?error");
        return "redirect:/login?error";
    }
}
