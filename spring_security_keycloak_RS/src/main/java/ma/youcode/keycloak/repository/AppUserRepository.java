package ma.youcode.keycloak.repository;

import ma.youcode.keycloak.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
