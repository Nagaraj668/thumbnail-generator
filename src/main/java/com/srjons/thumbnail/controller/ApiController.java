package com.srjons.thumbnail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process")
public class ApiController {

    @Autowired
    ImageProcessService imageProcessService;

    @GetMapping
    public String process() {
        imageProcessService.processAll();
        return "Done";
    }
}
