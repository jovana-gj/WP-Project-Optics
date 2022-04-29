package com.ukim.finki.optika.web;


import com.ukim.finki.optika.model.Role;
import com.ukim.finki.optika.model.exception.InvalidArgumentsException;
import com.ukim.finki.optika.model.exception.PasswordsDoNotMatchException;
import com.ukim.finki.optika.model.exception.UsernameAlreadyExistsException;
import com.ukim.finki.optika.service.AuthService;
import com.ukim.finki.optika.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final AuthService authService;
    private final UserService userService;


    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent","register");
        return "main_template";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role role){
        try{
            this.userService.register(username, password, repeatPassword, name, surname, role);
            return "redirect:/glasses";
        }
        catch (InvalidArgumentsException | PasswordsDoNotMatchException | UsernameAlreadyExistsException exception){
            return "redirect:/register?error="+exception.getMessage();
        }
    }


}
