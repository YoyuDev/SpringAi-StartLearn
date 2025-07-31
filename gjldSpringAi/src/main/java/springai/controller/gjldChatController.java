package springai.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class gjldChatController {


    private final ChatClient chatClient;
    //创建ChatClient
    public gjldChatController(ChatClient.Builder chatClientBuiler) {
        this.chatClient = chatClientBuiler.build();
    }

    //实现简单的对话
    @GetMapping("/chat")
    public String chat(@RequestParam(value = "message", defaultValue = "你是谁") String message) {
        return chatClient.prompt()
                .user(message)  //用户输入
                .call()  //请求模型
                .content();  //返回文本
    }

    //流式对话
    @GetMapping(value = "/chat-stream" ,produces = "text/html;charset=UTF-8")
    public Flux<String> chatStream(@RequestParam(value = "message", defaultValue = "你是谁") String message) {
        return chatClient.prompt()
                .user(message)
                .stream()
                .content();
    }
}
