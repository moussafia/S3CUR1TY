package ma.youcode.thirdparty.userDetails;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import jakarta.servlet.http.HttpServletRequest;

public class AdditionalAuthenticationDetailsSource extends WebAuthenticationDetailsSource{

	@Override
	public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
		return new AdditionalAuthenticationDetails(context);
	}
}
