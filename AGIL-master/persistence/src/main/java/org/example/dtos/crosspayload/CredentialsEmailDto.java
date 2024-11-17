package org.example.dtos.crosspayload;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CredentialsEmailDto implements Serializable {
    private String email;
    private String username;
    private String password;
}
