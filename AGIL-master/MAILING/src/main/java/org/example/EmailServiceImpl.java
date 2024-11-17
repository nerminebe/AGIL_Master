package org.example;

import lombok.RequiredArgsConstructor;
import org.example.dtos.crosspayload.CredentialsEmailDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.noreply}")
    private String email;

    @Override
    public void sendCredentialsEmail(CredentialsEmailDto credentialsEmailDto) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        try {
            helper.setFrom(email);
            helper.setTo(credentialsEmailDto.getEmail());
            helper.setSubject("Credentials Access to The Plateform");
            Context context = new Context();
            context.setVariable("email", credentialsEmailDto.getEmail());
            context.setVariable("username", credentialsEmailDto.getUsername());
            context.setVariable("password", credentialsEmailDto.getPassword());
            String htmlContent = templateEngine.process("email-template", context);
            helper.setText(htmlContent, true);
            emailSender.send(mimeMessage);

        } catch (MailException | MessagingException e) {
            e.printStackTrace();
            System.out.println("mail not send !");
        }
    }


}
