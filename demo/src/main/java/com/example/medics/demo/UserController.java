package com.example.medics.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {


    @Autowired
    private UserService UserService;


    private BCryptPasswordEncoder passwordEncoder;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    //@RequestMapping(value = "/register",method=RequestMethod.POST)
    @ResponseBody
    public UserDTO Login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        UserDTO userDTO = UserService.findByEmail(email);
        if (userDTO != null) {
            if(userDTO.getVerified()) {
                passwordEncoder = new BCryptPasswordEncoder();
                boolean matches = passwordEncoder.matches(password, userDTO.getPassword());
                System.out.println("Coming in");
                return userDTO;
            }else{
                return null;

            }
        } else {
            System.out.println("Coming NULL");

            return null;

        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    //@RequestMapping(value = "/register",method=RequestMethod.POST)
    @ResponseBody
    public UserDTO Register(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password, @RequestParam(value = "name") String name) {
        UserDTO userDTO = UserService.findByEmail(email);
        //System.out.println(UserDTO);
        if (userDTO != null) {
            return null;
        } else {
            passwordEncoder = new BCryptPasswordEncoder();
            System.out.println(passwordEncoder.encode(password));
            userDTO = new UserDTO(email, passwordEncoder.encode(password), name);
            UserService.save(userDTO);

            MailSender mailSender = new MailSender();
            mailSender.verifyAccount(email);
            return userDTO;
        }
    }

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    @ResponseBody
    public String verify(@RequestParam(value = "email") String email) {

        UserDTO userDTO = UserService.findByEmail(email);
        if (userDTO == null) {
            return "Verification unsuccessful";
        } else {
            if (!userDTO.getVerified()) {
                userDTO.setVerified(true);
                UserService.save(userDTO);
            }
            return "Verified";
        }
    }

    @RequestMapping(value = "/forgotPass", method = RequestMethod.GET)
    @ResponseBody
    public String forgotPassword(@RequestParam(value = "email") String email) {
        UserDTO userDTO = UserService.findByEmail(email);
        if (userDTO == null) {
            return "Invalid Email";
        } else {
            RandomString gen = new RandomString(Utils.randomLength);
            String password = gen.nextString();
            System.out.println(password);

            MailSender mailSender = new MailSender();
            boolean sent = mailSender.forgotPass(email,password);

            if(sent) {
                passwordEncoder = new BCryptPasswordEncoder();

                userDTO.setPassword(passwordEncoder.encode(password));
                UserService.save(userDTO);
                return "Password sent to email";
            }

            return "Connection error";



        }
    }



    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    @ResponseBody
    public String changePassword(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password, @RequestParam(value = "newPassword") String newPassword) {
        UserDTO userDTO = UserService.findByEmail(email);
        if (userDTO != null) {
            passwordEncoder = new BCryptPasswordEncoder();
            boolean matches = passwordEncoder.matches(password, userDTO.getPassword());
            if (matches) {
                passwordEncoder = new BCryptPasswordEncoder();
                System.out.println(passwordEncoder.encode(password));
                userDTO.setPassword(passwordEncoder.encode(newPassword));
                UserService.save(userDTO);
                return "Successful";
            } else {
                return "Error Password";
            }

        }
        return "Failed";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    @ResponseBody
    public UserDTO editName(@RequestParam(value = "email") String email, @RequestParam(value = "name") String name) {
        UserDTO userDTO = UserService.findByEmail(email);
        if (userDTO != null) {
            userDTO.setName(name);
            UserService.save(userDTO);
            return userDTO;
        }else {
            return null;
        }
    }



}


//        UserDTO UserDTO = UserService.findByEmail(email);
//        if (UserDTO != null) {
//            UserDTO UserDTO = new UserDTO(username, password, email);
//            return UserDTO;
//        }
//
//
//        UserDTO UserDTO = UserService.findByUsernamePassword(email, password);
//        if (UserDTO != null) {
//            System.out.println("Hello is me, the mel");
//
//            return UserDTO;
//        } else {
//            System.out.println("Hello is me, the ray");
//
//            return null;
//
//        }


