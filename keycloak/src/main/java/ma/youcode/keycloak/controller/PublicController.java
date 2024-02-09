package ma.youcode.keycloak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class PublicController {
    @GetMapping("/public/api")
    public String publicPage(){
        return "public";
    }
}
