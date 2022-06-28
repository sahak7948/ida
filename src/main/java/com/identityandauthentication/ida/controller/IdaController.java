package com.identityandauthentication.ida.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Api(value = "IdaController")
@RestController
public class IdaController {

    @Autowired
    RestTemplate template;

    @GetMapping("/api/ida")
    @ResponseBody
    public String getIdentification(@RequestParam String url, @RequestParam String identification) {
        if (!"30847300".equals(identification)) {
            return "{'error': 'unidentified'}";
        }
        return template.getForObject(url, String.class);
    }

}
