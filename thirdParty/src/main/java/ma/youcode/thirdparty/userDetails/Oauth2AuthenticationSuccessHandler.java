package ma.youcode.thirdparty.userDetails;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.thirdparty.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	private UserRepository userRepository;
	public Oauth2AuthenticationSuccessHandler(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
										HttpServletResponse response,
			Authentication authentication) throws IOException {


		response.sendRedirect("/index");
	}



}
