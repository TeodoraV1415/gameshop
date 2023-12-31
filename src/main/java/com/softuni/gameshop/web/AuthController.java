package com.softuni.gameshop.web;

import com.softuni.gameshop.model.DTO.UserRegisterDTO;
import com.softuni.gameshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userRegisterDTO")
    public UserRegisterDTO initRegisterDTO() {
        return new UserRegisterDTO();
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @PostMapping("/login-error")
    public String onFailure(
            @ModelAttribute("username") String username,
            Model model) {

        model.addAttribute("username", username);
        model.addAttribute("badCredentials", "true");

        return "login";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO userRegisterDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
        redirectAttributes.addFlashAttribute(
                "org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);

        if (userService.getByUsername(userRegisterDTO.getUsername()).isPresent()){
            redirectAttributes.addFlashAttribute("usernameExists", true);
            return "redirect:/register";
        }

        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("passwordsDontMatch", true);
            return "redirect:/register";
        }

        if (bindingResult.hasErrors() || !this.userService.register(userRegisterDTO)) {
            return "redirect:/register";
        }

        return "redirect:/login";
    }
}
