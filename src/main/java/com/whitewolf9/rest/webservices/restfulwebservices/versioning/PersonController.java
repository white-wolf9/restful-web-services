package com.whitewolf9.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    //  One of the ways to version API is to use two separate URIs

    @GetMapping("v1/person")
    public PersonV1 persionV1(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Fred", "Allen"));
    }

    //  Second way of versioning API is as per below, using the request param.

    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 personParamV1(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 personParamV2(){
        return new PersonV2(new Name("Fred", "Allen"));
    }


}
