package springai.gjldChatMemory;


import jakarta.annotation.PostConstruct;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {
    @Autowired
    private ChatMemory chatMemory;

    // 初始化Memory
    @PostConstruct
    public void init() {
        // 创建一个Memory
        this.chatMemory = MessageWindowChatMemory.builder()
                .maxMessages(10) // 最多保存10条消息
                .build();
    }

    @Bean
    public ChatClient girlfriendChatClient(OpenAiChatModel chatModel) {
        return ChatClient
                .builder(chatModel)
                .defaultSystem("你叫小花，是我的女朋友")
                .defaultAdvisors(new SimpleLoggerAdvisor(), //记录聊天内容
                        MessageChatMemoryAdvisor
                                .builder(chatMemory)

                                .build())

                .build();

    }

}
