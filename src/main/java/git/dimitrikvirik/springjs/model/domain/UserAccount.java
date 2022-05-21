package git.dimitrikvirik.springjs.model.domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name ="keycloakId", nullable = false, unique = true)
    private String keycloakId;

    @Column(name = "firstName", nullable = false)
    private String firstname;

    @Column(name = "lastName", nullable = false)
    private String lastname;


}