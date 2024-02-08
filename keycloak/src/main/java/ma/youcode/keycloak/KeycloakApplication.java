package ma.youcode.keycloak;

import ma.youcode.keycloak.entity.AppUser;
import ma.youcode.keycloak.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KeycloakApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeycloakApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AppUserRepository appUserRepository){
		return (arg)->{
			appUserRepository.save(new AppUser(null, "mohammed", "moha@gmail.com"));
			appUserRepository.save(new AppUser(null, "moussafia", "msf@gmail.com"));
			appUserRepository.save(new AppUser(null, "data", "data@gmail.com"));
		};
	}

}
