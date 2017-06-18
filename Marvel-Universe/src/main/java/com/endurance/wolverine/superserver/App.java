package com.endurance.wolverine.superserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Created by kapish on 14/6/17.
 */
@SpringBootApplication
public class App {
    public static void main(String []args){
        SpringApplication.run(App.class,args);
        System.out.println("Hello");

    }

}
