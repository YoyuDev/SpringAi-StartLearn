package springai.controller;


import org.apache.catalina.User;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class gjldChatPromptController {

    @Autowired
    private ChatModel chatModel;


    @GetMapping("/chat-with-prompt")
    public String chatWithPrompt(@RequestParam("name") String name,
                                 @RequestParam("voice") String voice) {
        //设置用户输入信息
        String userInput = """
                我喜欢你
                """;

        UserMessage userMessage = new UserMessage(userInput);
        //设置系统提示 信息

        String systemPrompt = """
        我的名字是{name}，你是我的女性朋友,我将要和你表白，你将{voice}我
        """;

        //使用PromptTempLate设置信息
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemPrompt);

        //替换占位符
        Message systemMessage = systemPromptTemplate.createMessage(
                Map.of("name", name, "voice", voice)
        );

        //使用Prompt封装 信息
        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));

        //调用chatModel
        var chatResponse = chatModel.call(prompt);
        String content = chatResponse.getResult().getOutput().getText();
        return content;
    }
}
