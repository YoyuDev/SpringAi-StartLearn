package springai.controller;


import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class gjldChatModelController {


    @Autowired
    private ChatModel chatModel;

    @GetMapping("/chat-with-model")
    public String chatWithModel(@RequestParam(value = "message", defaultValue = "你是谁") String message){
        String result = chatModel.call(message);
        return result;
    }

    @GetMapping("/chat-with-model2")
    public String chatWithModel2(@RequestParam(value = "message", defaultValue = "你是谁") String message){
        ChatResponse chatResponse = chatModel.call(
                new Prompt(
                        message,
                        ChatOptions.builder()
                                .model("Qwen/QwQ-32B")
                                .temperature(0.7)
                                .build()
                )
        );
        String content = chatResponse.getResult().getOutput().getText();
        return content;
    }
}
