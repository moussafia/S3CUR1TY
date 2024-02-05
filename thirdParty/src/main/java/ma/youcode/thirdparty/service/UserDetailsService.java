package ma.youcode.thirdparty.service;


import lombok.RequiredArgsConstructor;
import ma.youcode.thirdparty.modal.Authorities;
import ma.youcode.thirdparty.modal.entity.AppUser;
import ma.youcode.thirdparty.repository.UserRepository;
import ma.youcode.thirdparty.userDetails.UserModal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;
    private static final boolean DEFAULT_ACC_NON_EXP = true;
    private static final boolean DEFAULT_CRED_NON_EXP = true;
    private static final boolean DEFAULT_ACC_NON_LOCKED = true;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        List<String> authorities = new ArrayList<>();
        authorities.add(Authorities.ROLE_USER);
        UserModal userModal = new UserModal(
                appUser.getUsername(),
                appUser.getPassword(),
                appUser.getVerified(),
                DEFAULT_ACC_NON_EXP,
                DEFAULT_CRED_NON_EXP,
                DEFAULT_ACC_NON_LOCKED,
                buildAuthorities(authorities)
        );
        userModal.setEmail(appUser.getEmail());
        userModal.setLastName(appUser.getLastName());
        userModal.setFirstName(appUser.getFirstName());
        return userModal;


    }

    private Collection<? extends GrantedAuthority> buildAuthorities(List<String> authorities) {
        List<GrantedAuthority> authorityList = new ArrayList<>(1);
        authorities.stream().forEach(a-> authorityList.add(new SimpleGrantedAuthority(a)));
        return authorityList;

    }
}
