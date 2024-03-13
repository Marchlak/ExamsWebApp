/*package com.example.exams.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

        private final JavaMailSender mailSender;

        @Autowired
        public NotificationService(JavaMailSender mailSender) {
            this.mailSender = mailSender;
        }

        public void sendNotification(String to, String subject, String text) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("email@example.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
        }
    }
*/

