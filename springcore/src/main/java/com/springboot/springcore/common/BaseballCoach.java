package com.springboot.springcore.common;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) //bean are SINGLETON by default, but setting PROTOTYPE will create new instances for each bean
public class BaseballCoach implements Coach {

    public BaseballCoach(){
        System.out.println("Constructor called by : " + getClass().getSimpleName());
    }

    // defind our init method, this execute after constructor call
    @PostConstruct
    public void doMyInit(){
        System.out.println("Init is called after Baseball coach constructor");
    }

    // define destroy method, this execute before instance destroy
    // IMPORTANT: For "prototype" scoped beans, Spring does not call the destroy method. 
    @PreDestroy
    public void doBeforeDestroy(){
        System.out.println("Destroy is called before Baseball coach destroy");
    }

    @Override
    public String getDailyWorkout(){
        return "Baseball Coach: Dribble 20 minutes!.";
    }
}
