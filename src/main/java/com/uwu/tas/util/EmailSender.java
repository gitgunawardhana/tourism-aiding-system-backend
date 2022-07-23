package com.uwu.tas.util;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailSender {

    private final JavaMailSender javaMailSender;

    @Async
    public void sendEmail(List<String> addresses, String subject, String body) {
//        log.info("\nNormal mail: Addresses: {} \nSubject: {}", addresses, subject);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(addresses.toArray(new String[0]));

        msg.setSubject(subject);
        msg.setText(body);

        javaMailSender.send(msg);

    }
}
