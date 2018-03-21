package com.example.medics.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Component
public class MailSender {

    @Autowired
    JavaMailSender javaMailSender;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Session sendMail(){
        final String fromEmail = "medicalsuggestion1995@gmail.com"; //requires valid gmail id
        final String password = "medicalSuggestion"; // correct password for gmail id
        //String toEmail = to; // can be any email id
        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        return session;
    }

    public boolean verifyAccount(String to) {
        Session session = sendMail();
        boolean sent = EmailUtil.sendEmail(session, to,"Verify Account", Utils.IP+"/verify?email="  + to);

        return sent;
    }

    public boolean forgotPass(String to, String body) {
        Session session = sendMail();
        boolean sent = EmailUtil.sendEmail(session, to,"Forgot Pass", /*"Hello " + to + "!\n\n"
                + "You now have a Solip System Receipt Scanning Account. " +
                "Please use this password to login to your account through the app.\n\n"
                + */body);

        return sent;
    }
}
