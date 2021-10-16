package com.whitewolf9.rest.webservices.restfulwebservices.controllers;

import com.whitewolf9.rest.webservices.restfulwebservices.entity.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {

        //  Uses the ACCEPT-LANGUAGE from the request header to return the message
        //  return messageSource.getMessage("good.morning.menssage",null, "Default Message", locale);

        //  Uses the LocaleContextHolder to dynamically fetch the locale, if not specified returns EN
        return messageSource.getMessage("good.morning.message",null, "Default Message", LocaleContextHolder.getLocale());
    }

}
