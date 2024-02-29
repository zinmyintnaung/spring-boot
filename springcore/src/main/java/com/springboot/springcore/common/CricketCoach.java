package com.springboot.springcore.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary //If we didn't define any bean, this coach will be used, but @Qualifier has high priority over @Primary

public class CricketCoach implements Coach{

    public CricketCoach(){
        System.out.println("Constructor called by : " + getClass().getSimpleName());
    }
    
    @Override
    public String getDailyWorkout(){
        return "Cricket Coach: Practice fast bowling for 15 minutes.!";
    }
}
