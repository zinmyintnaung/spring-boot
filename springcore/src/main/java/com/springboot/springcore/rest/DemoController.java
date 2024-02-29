package com.springboot.springcore.rest;

import org.springframework.web.bind.annotation.RestController;

import com.springboot.springcore.common.Coach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
//@Lazy //Only call when this bean is required during runtime
public class DemoController {
    private Coach myCoach;

    //Example of constructor injection, must include @Autowired
    @Autowired
    public DemoController(@Qualifier("baseballCoach") Coach theCoach){
        System.out.println("Constructor called by : " + getClass().getSimpleName());
        myCoach = theCoach;
    }

    //Example of setter injection and you can give any method name for this but must include @Autowired
    /* @Autowired
    public void setCoach(Coach theCoach){
        myCoach = theCoach;
    } */

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
    

}
