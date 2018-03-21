package com.example.medics.demo;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public class EmailUtil {
    public static boolean sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@medicSource.com", "MedicSource"));

            msg.setReplyTo(InternetAddress.parse("no_reply@medicSource.com", false));

            msg.setSubject(subject, "UTF-8");

            //MimeMultipart multipart = new MimeMultipart("related");
            //BodyPart messageBodyPart = new MimeBodyPart();
            //String htmlImg = "<img src=\"cid:logo.png\" alt=\"Solip Systems\">\n";
            //messageBodyPart.setContent(htmlImg, "text/html");

            //multipart.addBodyPart(messageBodyPart);

            //messageBodyPart = new MimeBodyPart();

            String someHtmlMessage = "<h1>Verify this by clicking this</h1>\n" + body;
            //messageBodyPart.setContent(someHtmlMessage, "text/html");

            //multipart.addBodyPart(someHtmlMessage);

            msg.setContent(someHtmlMessage, "text/html; charset=utf-8");
            //msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("E-Mail Sent Successfully!!");
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

