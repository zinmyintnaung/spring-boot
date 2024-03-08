package com.springboot.springcore.config;

import com.springboot.springcore.common.Coach;
import com.springboot.springcore.common.SwimCoach;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SportConfig {
    //Making our SwimCoach.java into Bean, the method name is class name with first letter lowercase
    //NOTE: We can also give bean Id which can use from @Qualifer Controller by using @Bean("aquatic")
    @Bean
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
