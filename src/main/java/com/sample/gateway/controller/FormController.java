package com.sample.gateway.controller;

import com.sample.gateway.facade.FormFacade;
import com.sample.gateway.view.FieldsView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/form")
@Slf4j
public class FormController {

    @Autowired
    FormFacade formFacade;

    @GetMapping("/service-id/{serviceId}/user/{userId}")
    public ResponseEntity<List<FieldsView>> getFormData(@PathVariable final Long serviceId, @PathVariable final Long userId) throws Exception {
        log.info("Received request to fetch missing fields");
        return ResponseEntity.ok(formFacade.getFormData(serviceId, userId));
    }

    @PostMapping("/service-id/{serviceId}/user/{userId}")
    public ResponseEntity<Boolean> submitFormData(@PathVariable final Long serviceId, @PathVariable final Long userId,
                                                  @RequestBody final Map<String, String> formData) throws Exception {
        log.info("Received request to submit form");
        return ResponseEntity.ok(formFacade.submitFormData(formData, serviceId, userId));
    }

    @PostMapping
    public ResponseEntity<Boolean> submitFormData(@RequestParam("userId") final Long userId,
                                                  @RequestBody final Map<String, String> formData) throws Exception {
        log.info("Received request: {}", formData);
        return ResponseEntity.ok().build();
    }

}
