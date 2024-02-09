package ma.youcode.keycloak.controller;

import ma.youcode.keycloak.entity.AppUser;
import ma.youcode.keycloak.repository.AppUserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    private AppUserRepository appUserRepository;
    private ClientRegistrationRepository clientRegistrationRepository;

    public UserController(AppUserRepository appUserRepository, ClientRegistrationRepository clientRegistrationRepository) {
        this.appUserRepository = appUserRepository;
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @GetMapping("/notAuthorized")
    public String notAuthorized(){
        return "notAuthorized";
    }
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String users(Model model){
        List<AppUser> users = appUserRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/auth")
    @ResponseBody
    public Authentication authentication(Authentication authentication){
        return authentication;
    }

    @GetMapping("/oauth2Login")
    public String oauth2Login(Model model) {
        String authorizationRequestBaseUri = "oauth2/authorization";
        Map<String, String> oauth2AuthenticationUrls = new HashMap<>();
        Iterable<ClientRegistration> clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;;
        clientRegistrations.forEach(registration ->{
            oauth2AuthenticationUrls.put(registration.getClientName(),
                    authorizationRequestBaseUri + "/" + registration.getRegistrationId());
            System.out.println("registration id " + registration.getRegistrationId());
            System.out.println("client name " + registration.getClientName());
        });
        model.addAttribute("urls", oauth2AuthenticationUrls);
        return "oauth2Login";
    }
}
