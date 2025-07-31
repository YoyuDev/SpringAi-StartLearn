package springai.gjldChatMemory;


/**
 * spring ai记忆 内存 Repository
 */

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class gjldChatMemoryNeiChunRepositoryController {

   @Autowired
   @Qualifier("girlfriendChatClient")
    private ChatClient chatClient;

   @GetMapping(value = "/memory/chat",produces = "text/html;charset=UTF-8")
   public Flux<String> chat(@RequestParam(name = "message", defaultValue = "你以后就叫小花，是我的对象，明白了吗") String prompt) {
       return chatClient.prompt()
               .user(prompt)
               .stream()
               .content();
   }

}
