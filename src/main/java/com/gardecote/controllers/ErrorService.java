package com.gardecote.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Created by Dell on 23/12/2016.
 */
@Service
@PropertySource("classpath:httpErrorCodes.properties")
public class ErrorService {
    @Autowired
    private Environment env;

    public String generateErrorMessage(final int error_code){
        System.out.println("hhhhhhhhhhhhhhhhhhhh");
        String message="";
        switch(error_code){
            case 400: message=env.getProperty("400");
                break;
            case 401: message=env.getProperty("401");
                break;
            case 404: message=env.getProperty("404");
                break;
            case 500: message=env.getProperty("500");
                break;
            case 501: message=env.getProperty("501");
                break;
            //etc
            //Put in all Http error codes here.
            default:message=env.getProperty("50x");
                break;
        }
        System.out.println("error_code :"+error_code);
        return message;
    }
}
