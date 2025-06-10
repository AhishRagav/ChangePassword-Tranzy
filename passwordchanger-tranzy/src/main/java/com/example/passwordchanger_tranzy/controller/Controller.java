package com.example.passwordchanger_tranzy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.passwordchanger_tranzy.dto.Email;
import com.example.passwordchanger_tranzy.dto.ONpassword;
import com.example.passwordchanger_tranzy.dto.OTPpassword;
import com.example.passwordchanger_tranzy.service.PasswordService;

@RestController
@CrossOrigin
public class Controller {
    @Autowired
    PasswordService passwordService;

    @PostMapping("/changepassword")
    public String changepassword(@RequestBody ONpassword onpassword){
        return passwordService.changepassword(onpassword);
    }

    @PostMapping("/send-email")
    public String sendemail(@RequestBody Email email){
        return passwordService.sendemail(email);
    }

    @PostMapping("/changewith-otp")
    public String changewithotp(@RequestBody OTPpassword otppassword){
        return passwordService.changwwithotp(otppassword);
    }
}
