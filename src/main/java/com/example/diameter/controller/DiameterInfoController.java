package com.example.diameter.controller;

import com.example.diameter.dto.DiameterMessageResponse;
import com.example.diameter.service.DiameterMessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diameter")
public class DiameterInfoController {

    private final DiameterMessageService service;

    public DiameterInfoController(DiameterMessageService service) {
        this.service = service;
    }

    @GetMapping("/capabilities")
    public DiameterMessageResponse capabilities(
            @RequestParam(defaultValue = "spring.diameter.example") String originHost,
            @RequestParam(defaultValue = "example.com") String originRealm) {
        return service.buildCapabilitiesMessage(originHost, originRealm);
    }
}
