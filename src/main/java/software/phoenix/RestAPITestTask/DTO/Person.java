package software.phoenix.RestAPITestTask.DTO;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person extends BaseEntity {
    private String username;
    private String password;
    private String fullName;
    private String avatarUrl;
    private String role;

    public Person(String username, String password, String fullName, String avatarUrl, String role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.avatarUrl = avatarUrl;
        this.role = role;
        super.createdAt = LocalDateTime.now();
    }

}
