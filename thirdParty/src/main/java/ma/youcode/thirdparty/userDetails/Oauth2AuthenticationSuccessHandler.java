package ma.youcode.thirdparty.userDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import ma.youcode.thirdparty.model.UserOAuth2Dto;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Configuration("oauth2authSuccessHandler")
@RequiredArgsConstructor
public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	private final RedirectStrategy redirectStrategy;


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
		AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		this.redirectStrategy.sendRedirect(request, response, "/portfolio");
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

	}


/**	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		if(!this.portfolioService.userHasAportfolio(authentication.getName())) {
			this.portfolioService.createNewPortfolio(authentication.getName());
			OAuth2AuthenticationToken token = (OAuth2AuthenticationToken)authentication;
			Map<String, Object> attributes = token.getPrincipal().getAttributes();
			String firstname = null, lastname = null, email = null;
			if(token.getAuthorizedClientRegistrationId().equals("facebook")) {
				String name = attributes.get("name").toString();
				firstname = name.split(" ")[0];
				lastname = name.split(" ")[1];
				email = attributes.get("email").toString();
			} else if (token.getPrincipal() instanceof DefaultOidcUser) {	
				DefaultOidcUser oidcToken = (DefaultOidcUser) token.getPrincipal();
				firstname = oidcToken.getGivenName();
				lastname = oidcToken.getFamilyName();
				email = oidcToken.getEmail();
			}
			UserOAuth2Dto user = new UserOAuth2Dto(firstname,lastname,authentication.getName(),email);
			this.userRegistrationService.registerNewAuth2User(user);
		}

		this.redirectStrategy.sendRedirect(request, response, "/portfolio");
	}
**/
}
