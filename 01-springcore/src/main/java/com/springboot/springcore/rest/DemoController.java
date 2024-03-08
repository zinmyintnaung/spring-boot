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
    private Coach anotherCoach;
    private Coach mySwimCoach;

    //Example of constructor injection, must include @Autowired
    @Autowired
    public DemoController(@Qualifier("baseballCoach") Coach theCoach, 
        @Qualifier("baseballCoach") Coach theAnotherCoach,
        @Qualifier("swimCoach") Coach theSwimCoach){
        System.out.println("Constructor called by : " + getClass().getSimpleName());
        myCoach = theCoach;
        anotherCoach = theAnotherCoach;
        mySwimCoach = theSwimCoach;
    }

    //Example of setter injection and you can give any method name for this but must include @Autowired
    /* @Autowired
    public void setCoach(Coach theCoach){
        myCoach = theCoach;
    } */

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        //return myCoach.getDailyWorkout();
        //return anotherCoach.getDailyWorkout();
        return mySwimCoach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String getMethodName() {
        return "Comparing Bean: " + (myCoach == anotherCoach);
    }
    
    

}
