package ma.youcode.thirdparty.userDetails;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MFAUser extends User implements ThirdPartyAuthenticatedPrincipal {

	public MFAUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	@Setter
	@Getter
	private String firstName;
	@Setter
	@Getter
	private String lastName;
	@Setter
	@Getter
	private String email;
	
	@Override
	public String getFirstAndLastName() {

		return firstName+" "+lastName;
	}

	@Override
	public String getEmail() {

		return email;
	}
}
