package springai.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class gjldChatRoleController {

    @Autowired
    private ChatClient chatClient;

    @GetMapping("/chat-with-role")
    public String chatWithRole(@RequestParam(value = "message", defaultValue = "你是谁") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}
