package com.bbs.ollamaspringai.controller;


import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OllamaController {


    @Autowired
    private OllamaChatModel ollamaChatModel;

    @GetMapping("/ollama")
    public String chat(@RequestParam(value = "message", defaultValue = "你是谁") String message) {
        String result = ollamaChatModel.call( message);
        return result;
    }
}


