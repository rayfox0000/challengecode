package com.example.medics.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;

import java.util.Properties;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 * Created by MeL on 3/19/2018.
 */
public class EmailTest {
    public static void main(String[] args) {
        final String fromEmail = "medicalSuggestion1995@gmail.com"; //requires valid gmail id
        final String password = "medicalSuggestion"; // correct password for gmail id
        final String toEmail = "rayfox00@hotmail.com"; // can be any email id

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

        EmailUtil.sendEmail(session, toEmail,"TLSEmail Testing Subject", "http://192.168.0.120:3000/verify?email=" + toEmail);

//        Scanner scanner = new Scanner(System.in);
//
//        if (scanner.nextLine().matches("^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$") || scanner.nextLine().matches("^[a-zA-z ]*$")) {
//            System.out.println("kaka");
//        }

        /*MailSender mailSender = new MailSender();
        mailSender.sendLoginCredentials("rayfox00@hotmail.com", "Helloelel");
*/
    }
}