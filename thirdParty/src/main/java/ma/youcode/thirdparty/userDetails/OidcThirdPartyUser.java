package ma.youcode.thirdparty.userDetails;

import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class OidcThirdPartyUser extends DefaultOidcUser implements ThirdPartyAuthenticatedPrincipal {

	public OidcThirdPartyUser(OidcUser user) {
		super(user.getAuthorities(), user.getIdToken());
	}

	@Override
	public String getFirstName() {
		return this.getAttributes().get("given_name").toString();
	}

	@Override
	public String getLastName() {
		return this.getAttributes().get("family_name").toString();
	}

	@Override
	public String getFirstAndLastName() {
		return getFirstName()+" "+getLastName();
	}

	@Override
	public String getEmail() {
		getFamilyName();
		return this.getAttributes().get("email").toString();
		
	}
	
}
