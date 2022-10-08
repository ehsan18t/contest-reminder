package dev.pages.ahsan.creminder.email;

import dev.pages.ahsan.creminder.main.Config;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class MailSender {
    //this is responsible to send email.
    public static void sendEmail(String email, String pass, String message, String subject, String to) {
        //get the system properties
        Properties properties = System.getProperties();

        //host config
//        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", Config.HOST);
        properties.put("mail.smtp.port", Config.PORT);

        //Step 1: to get the session object.
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, pass);
            }
        });
//        session.setDebug(true);

        MimeMessage m = new MimeMessage(session);
        try {
            // from email
            m.setFrom(email);

            // adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // adding subject to message
            m.setSubject(subject);

            // adding text to message
//            m.setText(message);

            // adding html
            m.setText(message, "utf-8", "html");

            // send
            Transport.send(m);
            System.out.println(" - Email Sending Successful!");
        } catch (Exception e) {
            System.out.println(" - Email Sending Failed!");
            e.printStackTrace();
        }
    }
}


