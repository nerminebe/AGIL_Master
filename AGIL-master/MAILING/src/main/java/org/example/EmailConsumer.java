package org.example;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dtos.crosspayload.CredentialsEmailDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue.credentials}")
    public void listen(@Payload CredentialsEmailDto credentialsEmailDto) {
        System.out.println("Sending Mail Credentials ****-----------------");
        emailService.sendCredentialsEmail(credentialsEmailDto);
    }
}
