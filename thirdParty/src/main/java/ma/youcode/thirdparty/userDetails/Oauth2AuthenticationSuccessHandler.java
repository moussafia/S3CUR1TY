package ma.youcode.thirdparty.userDetails;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.thirdparty.modal.Dto.UserRegistrationRequest;
import ma.youcode.thirdparty.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	private UserService userService;

	public Oauth2AuthenticationSuccessHandler(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
										HttpServletResponse response,
			Authentication authentication) throws IOException {
		if(this.userService.getUser(authentication.getName()).isEmpty()) {
			UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
			OidcUser oidcUser = (OidcUser)authentication.getPrincipal();
			registrationRequest.setUsername(oidcUser.getSubject());
			registrationRequest.setEmail(oidcUser.getEmail());
			registrationRequest.setFirstname(oidcUser.getGivenName());
			registrationRequest.setLastname(oidcUser.getFamilyName());
			this.userService.registerUser(registrationRequest);
		}

		response.sendRedirect("/index");
	}



}
