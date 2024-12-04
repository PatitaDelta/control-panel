package es.guillermoll.control_panel.api.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "/api/home";
    }
}
