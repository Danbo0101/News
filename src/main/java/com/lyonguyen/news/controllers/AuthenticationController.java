package com.lyonguyen.news.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.lyonguyen.news.models.User;
import com.lyonguyen.news.services.SecurityService;
import com.lyonguyen.news.services.UsersService;

@Controller
public class AuthenticationController {

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private UsersService usersService;

    @Value("${webapp.messages.loginfail}")
    private String loginFailedMessage;

    @Value("${webapp.messages.logout}")
    private String logoutMessage;
    
    @Value("${webapp.messages.registerpasswordfail}")
    private String registerPasswordFailedMessage;
    
    @Value("${webapp.messages.registerfail}")
    private String registerFailedMessage;
    
    @Value("${webapp.messages.registersuccess}")
    private String registrationSuccess;
    

    @GetMapping("/login")
    public String login(Model model, String error, String logout,RedirectAttributes redirectAttributes) {
    	
    	String registration = (String) redirectAttributes.getFlashAttributes().get("registration");
    	
        if (securityService.isLoggedIn()) {   
            return "redirect:/";
        }
        if (error != null) {
            model.addAttribute("error", loginFailedMessage);
        }
        if (logout != null) {
            model.addAttribute("logout", logoutMessage);
        }
        if (registration != null) {
            model.addAttribute("registration", registration);
        }

        return "login";
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
    	model.addAttribute("users",new User());    	
    	return "register";
    }
    
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user,@RequestParam("confirm-password") String confirmPassword,
    		Model model,RedirectAttributes redirectAttributes) {
    	
        if (usersService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", registerFailedMessage);
            return "register";
        }else if(!usersService.isPasswordMatch(user.getPassword(), confirmPassword)){
        	model.addAttribute("passwordError", registerPasswordFailedMessage);
            return "register";
        }
        usersService.save(user);
        redirectAttributes.addFlashAttribute("registration", registrationSuccess);
        return "redirect:/login";
    }
}
