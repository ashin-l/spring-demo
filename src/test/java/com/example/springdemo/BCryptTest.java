package com.example.springdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class BCryptTest {
    @Autowired
    BCryptPasswordEncoder encoder;

    @Test
    void test() {
        var p = encoder.encode("111111");
        System.out.println(p);
    }

}
