package ma.youcode.thirdparty.userDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	//private final
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										FilterChain chain,
										Authentication authentication)
			throws IOException, ServletException {
		AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
										HttpServletResponse response,
			Authentication authentication) throws IOException {

		//this.redirectStrategy.sendRedirect(request, response, "/index");
	}



}
