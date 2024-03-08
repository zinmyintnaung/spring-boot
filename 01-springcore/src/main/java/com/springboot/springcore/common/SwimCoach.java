package com.springboot.springcore.common;

//THIS IS NORMAL JAVA CLASS, NOT BEAN COMPONENT, WE WILL MAKE IT AS SPRING BEAN FROM SportConfig.java
public class SwimCoach implements Coach {
    
    public SwimCoach(){
        System.out.println("Constructor " + getClass().getSimpleName() + " executed");
    }

    @Override
    public String getDailyWorkout(){
        return "Swim a thousand meter!";
    }
}
