package com.example.passwordchanger_tranzy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.passwordchanger_tranzy.dto.Email;
import com.example.passwordchanger_tranzy.dto.ONpassword;
import com.example.passwordchanger_tranzy.dto.OTPpassword;
import com.example.passwordchanger_tranzy.repository.Otprepo;
import com.example.passwordchanger_tranzy.repository.Repo;
import com.example.passwordchanger_tranzy.model.Otp;
import com.example.passwordchanger_tranzy.model.User;


@Service
public class PasswordService {

    @Autowired
    Repo repo;
    @Autowired
    public JavaMailSender javaMailSender;
    @Autowired
    public Otprepo  otprepo;
    
    

    public String changepassword(ONpassword onpassword){
        User user=repo.findByUsername(onpassword.getUsername());

        if(user==null) return "user not found";

        if(user.getPassword().equals(onpassword.getOldpassword())){
            user.setPassword(onpassword.getNewpassword());
            repo.save(user);
            return "success";
        }
        else{
            return "failed";
        }
    }


    public String sendemail(Email email){

        SimpleMailMessage message = new SimpleMailMessage();
        int otp = (int)(Math.random() * 900000) + 100000;

        message.setFrom("ahishragav16@gmail.com");
        message.setTo(email.getEmail());
        message.setSubject("otp for changing you password");
        message.setText("you otp is "+otp);

        User user = repo.findByEmail(email.getEmail());
        if(user==null){
            return "no user found";
        }


       try{ 
            javaMailSender.send(message);

            Otp otpobject=new Otp();
            otpobject.setEmail(email.getEmail());
            otpobject.setOtp(otp);
            otprepo.save(otpobject);
            return "success";
        }
        catch(Exception e){
            return "enter valid email";
        }
        
    }


    public  String changwwithotp(OTPpassword otppassword){
        Otp otp  =otprepo.getById(otppassword.getEmail());
        if(otp.getOtp() == Integer.parseInt(otppassword.getOtp())){
            
                 User user =repo.findByEmail(otppassword.getEmail());
                 user.setPassword(otppassword.getPassword());
                repo.save(user);
                return "success";
            
        }

        return "incorrect otp";
    }
    

}
