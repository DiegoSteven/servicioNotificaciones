package com.example.services.Notificators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.models.*;
import com.example.services.NotificationFormatter;

@Component
public class NotificatorMail implements Notificator {

    //private final JavaMailSender mailSender;
     //private final NotificationFormatter formatter;

    @Autowired
    //public NotificatorMail(JavaMailSender mailSender, NotificationFormatter formatter) {
      //  this.mailSender = mailSender;
       //  this.formatter = formatter;
   //  }
         

    @Override
    public String getType() {
        return "mail";
    }
    
    @Override
    public void send(NotificationModel notification, User user, Event event, Position position) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            System.err.println("❌ Usuario sin email. Notificación ignorada.");
            return;
        }

         //NotificationMessage message = formatter.format(notification, user, event, position);

        // SimpleMailMessage email = new SimpleMailMessage();
        // email.setTo(user.getEmail());
         //email.setSubject(message.getSubject());
         //email.setText(message.getBody());

         //mailSender.send(email);

        System.out.println("✅ Correo enviado a " + user.getEmail());
        
    }
       
}
    