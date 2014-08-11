package com.tripadvisor.capstone.business.service.impl;

import java.util.LinkedHashMap;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>
 * This send mail to the user. Memcache can be used to prevent the multiple 
 * people being send mail. once the mail is send, put it in the cache for atleast 1 min. 
 * Every time you send the mail, check if the user is in memcache. If it is there, don't send
 * second mail.
 * </p>  
 * @author rachana
 * @since Aug 10, 2014
 * 
 */
@Component
public class EmailManager {
    @Value("${email.username:testemail1180@gmail.com}")
    private String username;

    @Value("${email.password:testing1180}")
    private String password;

    private Properties props = null;

    private Session session = null;

    private LinkedHashMap<String, Boolean> cache = null;

    @PostConstruct
    public void init() {
        //take these to properties file 
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,
                        password);
            }
        });
        cache = new LinkedHashMap<String, Boolean>(50, 0.75f, true);
    }

    public void send(String toEmailId, String subject, String body) {
        if (cache.containsKey(toEmailId)) {
            // Already send the mail, doesn't need to throw exception
            return;
        }
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from-email@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmailId));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            cache.put(toEmailId, true);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
