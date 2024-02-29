package com.springboot.springcore.common;

import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach {

    public BaseballCoach(){
        System.out.println("Constructor called by : " + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout(){
        return "Baseball Coach: Dribble 20 minutes!.";
    }
}
