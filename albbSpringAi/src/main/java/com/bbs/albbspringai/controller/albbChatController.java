package com.bbs.albbspringai.controller;


import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class albbChatController {


    @Autowired
    private DashScopeChatModel chatModel;



    @GetMapping("/albb")
    public String chat(@RequestParam(value = "message", defaultValue = "你是谁") String message) {
        String result = chatModel
                .call( message);

        return result;
    }
}
