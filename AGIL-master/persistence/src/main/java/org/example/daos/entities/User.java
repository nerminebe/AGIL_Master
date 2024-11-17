package org.example.daos.entities;

import lombok.*;
import org.example.enums.Role;
import org.example.utils.Auditable;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "AGIL_USER")
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;

    private String lastname;

    private Date dob;

    private String telephone;

    private String sexe;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    private String password;

    private String picture;

    private String address;

    private boolean enabled; // user can connect to the application or not

    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @Fetch(FetchMode.JOIN) // Use eager fetching
    private Set<Role> roles = new HashSet<>();


}
