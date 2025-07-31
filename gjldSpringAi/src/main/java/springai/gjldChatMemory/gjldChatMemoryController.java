package springai.gjldChatMemory;


/**
 * spring ai记忆 快速入门
 */
import org.springframework.ai.chat.memory.ChatMemory;


import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.UserMessage;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class gjldChatMemoryController {

    @Autowired
    ChatMemory chatMemory;

    @Autowired
    private OpenAiChatModel chatModel;

    @GetMapping("/chat/memory")
    public String hello(@RequestParam(value = "message", defaultValue = "你以后就叫小花，是我的对象，明白了吗") String message) {
        // 将用户输入添加到 chatMemory 中
        chatMemory.add("user",new UserMessage(message));

        // 调用模型生成响应
        String result = chatModel.call(message);

        // 将模型响应添加到 chatMemory 中
        chatMemory.add("ai",new AssistantMessage(result));

        System.out.println(result);
        return result;
    }


}
