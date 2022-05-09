package com.mehmetpekdemir.statistics.service.infrastructure;

import com.mehmetpekdemir.statistics.service.adapter.order.feign.OrderApiFeignClient;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIT {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int serverPort;

    protected TestRestTemplate testRestTemplate;

    @MockBean
    protected OrderApiFeignClient orderApiFeignClient;

    @PostConstruct
    private void init() {
        testRestTemplate = new TestRestTemplate(restTemplateBuilder.rootUri("http://localhost:" + serverPort));
        TimeZone.setDefault(TimeZone.getTimeZone("UTC")); // Setting Spring Boot SetTimeZone
    }
}