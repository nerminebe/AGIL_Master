package org.example.dtos.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.Role;
import org.example.utils.Auditable;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto extends Auditable {
    private Long id;

    private String firstname;

    private String lastname;

    private Date dob;

    private String telephone;

    private String sexe;

    private String email;

    private String username;

    private String picture;

    private String address;

    private List<Role> roles;
}
