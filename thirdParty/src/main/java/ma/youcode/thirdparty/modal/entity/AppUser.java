package ma.youcode.thirdparty.modal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.UUID;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NonNull
    @Column(unique = true)
    private String username;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @Email
    @NonNull
    private String email;
    @NonNull
    private String password;
    private Boolean verified;

}
