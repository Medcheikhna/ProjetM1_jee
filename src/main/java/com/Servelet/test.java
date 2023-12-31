package com.Servelet;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class test {
   public static void main(String[] args) {
      String to = "recipient@example.com";
      String from = "sender@example.com";
      String host = "localhost";
      Properties properties = System.getProperties();
      properties.setProperty("mail.smtp.host", host);
      Session session = Session.getDefaultInstance(properties);
      try {
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));
         message.setSubject("This is the Subject Line!");
         message.setText("This is actual message");
         Transport.send(message);
         System.out.println("Sent message successfully....");
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}