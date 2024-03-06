package com.spingboot.hashid.controller;

import com.spingboot.hashid.service.HashidsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HashidsController {

    private final HashidsService hashidsService;

    public HashidsController(HashidsService hashidsService) {
        this.hashidsService = hashidsService;
    }

    @PostMapping("/encode/{number}")
    public String encode(@PathVariable Long number) {
        return hashidsService.encode(number);
    }

    @GetMapping("/decode/{hash}")
    public long decode(@PathVariable String hash) {
        return hashidsService.decode(hash);
    }
}
