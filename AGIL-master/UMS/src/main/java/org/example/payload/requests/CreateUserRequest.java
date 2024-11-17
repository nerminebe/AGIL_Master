package org.example.payload.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.utils.Auditable;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateUserRequest extends Auditable {

    private boolean notifyCred;

    private String firstname;

    private String lastname;

    private Date dob;

    private String telephone;

    private String sexe;

    private String email;

    private String username;

    private String picture;

    private String address;

    private boolean enabled = false;
}
