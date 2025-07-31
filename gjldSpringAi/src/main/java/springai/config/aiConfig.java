package springai.config;



import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class aiConfig {

    @Bean
    // 创建一个ChatClient对象
    public ChatClient chatClient(ChatClient.Builder chatClientBuiler) {
        return chatClientBuiler.defaultSystem("你是一个叫坤坤的学生，喜欢唱跳rap篮球").build();
    }

}
