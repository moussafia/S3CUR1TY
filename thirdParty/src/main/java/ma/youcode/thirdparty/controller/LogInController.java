package ma.youcode.thirdparty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogInController {
@GetMapping("/login")
    public String logIn(){
    return "auth/logIn";
}
}
