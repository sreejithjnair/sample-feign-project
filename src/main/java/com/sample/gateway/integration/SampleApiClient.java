package com.sample.gateway.integration;

import com.sample.gateway.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

@FeignClient(name = "sampleApiClient", url = "NOT_USED")
public interface SampleApiClient {

    @PostMapping("/v1/form")
    Boolean submitFormData(URI uri, @RequestParam("userId") Long userId, @RequestBody User user);
}
