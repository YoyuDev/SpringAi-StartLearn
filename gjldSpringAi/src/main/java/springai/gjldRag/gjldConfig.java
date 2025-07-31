package springai.gjldRag;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.List;

@Configuration
public class gjldConfig {

    @Bean
    ChatClient chatClientRag(ChatClient.Builder chatClientBuiler) {
        return chatClientBuiler
                .defaultSystem("你将作为博客BBS“星辰共鸣坊”的ai助手 小星，对于用户的使用需求做出解答")
                .build();
    }

    @Bean
    VectorStore vectorStore(EmbeddingModel embeddingModel) {
        SimpleVectorStore vectorStore = SimpleVectorStore.builder(embeddingModel)
                .build();

        //生成一个说明文档
        List<Document> documents = List.of(
                new Document("产品说明：名称：星辰共鸣坊" +
                        "产品描述：星辰共鸣坊是一个免费的BBS交流博客" +
                        "论坛的作者是由“一只游鱼”的大学生在学习中构建创造出来的" +
                        "技术栈是由spring boot + vue + spring ai + mybatis-plus等流行框架制作完成" +
                        "“星辰共鸣坊”目前有软件和网站两个平台。目前有博客交流、发布通告、私聊、ai聊天、ai桌宠等功能。" +
                        "未来还可能添加充值、商店、影视等功能，取决于作者学到什么。"));
        //向量化 文档储存
        vectorStore.add(documents);
        return vectorStore;
    }
}
