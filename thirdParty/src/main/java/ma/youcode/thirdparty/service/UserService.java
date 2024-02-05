package ma.youcode.thirdparty.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.youcode.thirdparty.modal.Dto.UserRegistrationRequest;
import ma.youcode.thirdparty.modal.entity.AppUser;
import ma.youcode.thirdparty.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public AppUser registerUser(UserRegistrationRequest userRegistrationRequest){
        AppUser user = new AppUser().builder()
                .email(userRegistrationRequest.getEmail())
                .firstName(userRegistrationRequest.getFirstname())
                .lastName(userRegistrationRequest.getLastname())
                .username(userRegistrationRequest.getUsername())
                .build();
        return userRepository.save(user);
    }
        public Optional<AppUser> getUser(String username){
        return userRepository.findByUsername(username);
    }
}
