package medicalimagebrowser.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import medicalimagebrowser.model.LoginModel;
import medicalimagebrowser.repository.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {
    final private UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String login() {

        if(SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping
    public String login(@Valid LoginModel loginModel, Model model) {
        model.addAttribute("username", loginModel.getUsername());
        model.addAttribute("message", "用户名与密码不匹配");
        return "login";
    }
}
