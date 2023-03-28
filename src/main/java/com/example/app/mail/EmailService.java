package com.example.app.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailService {

    public static void sendEmail(String from, String toMail, String subject, String body) throws MessagingException {
        JavaMailSenderImpl sender = MailConfig.getMailConfig();
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toMail);
        helper.setFrom(from);
        helper.setSubject(subject);
        helper.setText(body, true);
        sender.send(message);
    }
}
