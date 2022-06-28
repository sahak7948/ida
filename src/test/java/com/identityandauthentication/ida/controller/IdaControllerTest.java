package com.identityandauthentication.ida.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IdaControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void idlTest_success() {

        String url = "https://api.lloydsbank.com/open-banking/v2.2/atms";
        String identification = "30847300";

        String testUrl = String.format("http://localhost:%s/api/ida?url=%s&identification=%s", port, url, identification);

        assertThat(this.restTemplate.getForObject(testUrl, String.class)).isNotEmpty();
    }

    @Test
    public void idlTest_fail() {

        String url = "https://api.lloydsbank.com/open-banking/v2.2/atms";
        String identification = "30847301"; // identification not valid

        String testUrl = String.format("http://localhost:%s/api/ida?url=%s&identification=%s", port, url, identification);

        assertThat(this.restTemplate.getForObject(testUrl, String.class)).contains("error");
    }

}
