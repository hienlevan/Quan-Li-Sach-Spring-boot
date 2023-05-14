package com.hlv.quanlisach;

import com.hlv.quanlisach.entities.User;
import com.hlv.quanlisach.repositories.user.UserRepository;
import com.hlv.quanlisach.utils.EnumRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Arrays;

@SpringBootApplication

public class QuanlisachApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(QuanlisachApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if(userRepository.count() == 0){
            User user = new User("hienlv", "hachac2k2@gmail.com", "123456",
                    Arrays.asList(EnumRole.ROLE_ADMIN.name()));
            userRepository.save(user);
        }
    }
}
