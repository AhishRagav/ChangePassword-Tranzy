package com.example.passwordchanger_tranzy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.passwordchanger_tranzy.model.Otp;


public interface Otprepo extends JpaRepository <Otp,String> {
    
}
