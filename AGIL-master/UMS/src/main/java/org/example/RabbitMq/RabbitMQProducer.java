package org.example.RabbitMq;

import lombok.RequiredArgsConstructor;
import org.example.dtos.crosspayload.CredentialsEmailDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;
    @Value("${spring.rabbitmq.queue.credentials-exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.queue.credentials-routing}")
    private String routing;


    public void sendCredentialsMail(String email, String username, String password) {
        CredentialsEmailDto emailDto = CredentialsEmailDto.builder()
                .email(email)
                .username(username)
                .password(password)
                .build();
        rabbitTemplate.convertAndSend(exchange, routing, emailDto);
    }

}
