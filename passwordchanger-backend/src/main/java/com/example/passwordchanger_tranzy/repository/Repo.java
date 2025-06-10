package com.example.passwordchanger_tranzy.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import com.example.passwordchanger_tranzy.model.User;


@Repository
public interface Repo extends JpaRepository <User,Long>{
    User findByUsername(String username);
    User findByEmail(String email);
}
