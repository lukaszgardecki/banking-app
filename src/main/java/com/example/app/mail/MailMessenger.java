package com.example.app.mail;

import com.example.app.config.MailConfig;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailMessenger {

    public static void htmlEmailMassenger(String from, String toMail, String subject, String body) throws MessagingException {
        JavaMailSenderImpl sender = MailConfig.getMailConfig();
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper htmlMessage = new MimeMessageHelper(message, true);

        htmlMessage.setTo(toMail);
        htmlMessage.setFrom(from);
        htmlMessage.setSubject(subject);
        htmlMessage.setText(body, true);
        sender.send(message);
    }
}
