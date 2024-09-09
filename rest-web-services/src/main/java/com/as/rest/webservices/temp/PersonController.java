package com.as.rest.webservices.temp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class PersonController {

    private final MessageSource messageSource;

    @Autowired
    public PersonController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/GoodMorning")
    public String goodMorning(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("test.good.morning", null, "Default Message", locale);
    }

    @GetMapping("/v1/person")
    public EntityModel<Person1> getPerson1(){
        EntityModel<Person1> person1EntityModel = EntityModel.of(Person1.builder().name("Aman").build());
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).getPerson2());
        person1EntityModel.add(linkBuilder.withSelfRel());
        return person1EntityModel;
    }

    @GetMapping("/v2/person")
    public Person2 getPerson2(){
        return new Person2(Name.builder().firstName("Aman").lastName("Singh").build());
    }

    @GetMapping(path = "/person", params = "version=v1")
    public Person1 getPersonRequestParameter(){
        return Person1.builder().name("AmanRP").build();
    }

    @GetMapping(value = "/person", params = "version=v2")
    public Person2 getPerson2RequestParameter(){
        return new Person2(Name.builder().firstName("AmanRP").lastName("SinghRP").build());
    }

    @GetMapping(path = "/person", headers = "X-API-VERSION=1")
    public Person1 getPersonHeaders(){
        return Person1.builder().name("AmanHeader").build();
    }

    @GetMapping(value = "/person", headers = "X-API-VERSION=2")
    public Person2 getPerson2Headers(){
        return new Person2(Name.builder().firstName("AmanHeader").lastName("SinghHeader").build());
    }

    @GetMapping(path = "/person/mime", produces = "application/amd.company+json")
    public Person1 getPersonMime(){
        return Person1.builder().name("AmanMIME").build();
    }

    @GetMapping(value = "/person/mime", produces = "application/amd.company2+json")
    public Person2 getPerson2Mime(){
        return new Person2(Name.builder().firstName("AmanMIME").lastName("SinghMIME").build());
    }
}
