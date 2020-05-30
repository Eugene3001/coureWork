package ua.edu.onu.autoChecking.dao.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_user", uniqueConstraints = {
        @UniqueConstraint(name = "app_user_uk", columnNames = "user_name")
})
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "encrypted_password", nullable = false)
    private String encryptedPassword;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;
}