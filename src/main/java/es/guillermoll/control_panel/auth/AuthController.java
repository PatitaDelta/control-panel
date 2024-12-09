package es.guillermoll.control_panel.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "/auth/login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "/auth/register";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "/auth/logout";
    }
}
