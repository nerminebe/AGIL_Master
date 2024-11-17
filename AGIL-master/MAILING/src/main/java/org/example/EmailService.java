package org.example;

import org.example.dtos.crosspayload.CredentialsEmailDto;

public interface EmailService {
    void sendCredentialsEmail(CredentialsEmailDto credentialsEmailDto);
}
