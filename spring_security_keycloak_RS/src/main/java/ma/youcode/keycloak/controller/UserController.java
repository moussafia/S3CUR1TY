package ma.youcode.keycloak.controller;

import ma.youcode.keycloak.entity.AppUser;
import ma.youcode.keycloak.repository.AppUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private AppUserRepository appUserRepository;

    public UserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/notAuthorized")
    public String notAuthorized(){
        return "notAuthorized";
    }
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AppUser>> users(){
        List<AppUser> users = appUserRepository.findAll();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/auth")
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public Authentication authentication(Authentication authentication){

        return authentication;
    }
//
//    @GetMapping("/oauth2Login")
//    public String oauth2Login(Model model) {
//        String authorizationRequestBaseUri = "oauth2/authorization";
//        Map<String, String> oauth2AuthenticationUrls = new HashMap<>();
//        Iterable<ClientRegistration> clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;;
//        clientRegistrations.forEach(registration ->{
//            oauth2AuthenticationUrls.put(registration.getClientName(),
//                    authorizationRequestBaseUri + "/" + registration.getRegistrationId());
//            System.out.println("registration id " + registration.getRegistrationId());
//            System.out.println("client name " + registration.getClientName());
//        });
//        model.addAttribute("urls", oauth2AuthenticationUrls);
//        return "oauth2Login";
//    }
}
