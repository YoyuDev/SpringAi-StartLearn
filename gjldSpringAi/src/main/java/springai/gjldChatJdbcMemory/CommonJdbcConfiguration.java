package springai.gjldChatJdbcMemory;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;

import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class CommonJdbcConfiguration {



    @Bean
    public ChatMemory chatMemory(JdbcChatMemoryRepository repository) {
        // 使用 JDBC 存储库创建带窗口限制的记忆
        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(repository)  // 使用 JDBC 存储库
                .maxMessages(20)  // 最多保存20条消息
                .build();
    }

    @Bean
    public ChatClient girlfriendJdbcChatClient(OpenAiChatModel chatModel, ChatMemory chatMemory) {
        return ChatClient
                .builder(chatModel)
                .defaultSystem("你叫小花，是我的女朋友")
                .defaultAdvisors(new SimpleLoggerAdvisor(),
                        MessageChatMemoryAdvisor
                                .builder(chatMemory)
                                .build())
                .build();
    }
}
