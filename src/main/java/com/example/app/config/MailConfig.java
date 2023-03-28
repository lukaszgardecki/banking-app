package com.example.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class MailConfig {

    @Bean
    public static JavaMailSenderImpl getMailConfig() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("poczta.interia.pl");
        mailSender.setPort(587);
        mailSender.setUsername("konto.testowe100@interia.pl");
        mailSender.setPassword("test12345");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");


//        Email email = new SimpleEmail();
//        //Ustawienie serwera poczty wychodz�cej (SMTP), ka�da ma inn�:
//        email.setHostName("poczta.interia.pl");
//        //Ustawienie portu
//        email.setSmtpPort(587);
//        email.setAuthenticator(new DefaultAuthenticator("konto.testowe100", "test12345"));
//        email.setSSLOnConnect(true);
//        //email, z kt�rego wysy�amy
//        email.setFrom("konto.testowe100@interia.pl");
//        //ustawienie Tematu
//        email.setSubject("TestMail");
//        //Wiadomo��:
//        email.setMsg("To jest wiadomo�� testowa !");
//        //email odbiorcy
//        email.addTo("lukas.g1804@gmail.com");
//
//        email.send();


        return mailSender;
    }
}
