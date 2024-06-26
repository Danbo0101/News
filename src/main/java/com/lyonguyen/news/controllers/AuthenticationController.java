package com.lyonguyen.news.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
    
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

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
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersService.save(user);
        redirectAttributes.addFlashAttribute("registration", registrationSuccess);
        return "redirect:/login";
    }
    
    @PostMapping("/submit_password_change")
    public String changePassword(@RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword,
            RedirectAttributes redirectAttributes, Principal principal) {

        User currentUser = usersService.findByUsername(principal.getName());

        
        if (!bCryptPasswordEncoder.matches(oldPassword, currentUser.getPassword())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Current password is incorrect.");
            return "redirect:/";
        }
        
//        if (!oldPassword.equals(currentUser.getPassword())) {
//            redirectAttributes.addFlashAttribute("errorMessage", "Current password is incorrect.");
//            return "redirect:/";
//        }

     
     if (bCryptPasswordEncoder.matches(currentUser.getPassword(), confirmPassword)) {
    	 redirectAttributes.addFlashAttribute("errorMessage", "New password and confirm password do not match.");
         return "redirect:/";
     }
      

        currentUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
        usersService.save(currentUser);
        return "redirect:/logout";
    }
    
   


}
