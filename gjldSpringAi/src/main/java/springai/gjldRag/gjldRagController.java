package springai.gjldRag;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class gjldRagController {

    @Autowired
    private VectorStore vectorStore;
    @Autowired
    @Qualifier("chatClientRag")
    private ChatClient chatClient;

    @GetMapping(value = "/rag/chat",produces = "text/html;charset=UTF-8")
    public Flux<String> rag(@RequestParam(name = "message", defaultValue = "介绍一下这个网站") String prompt) {
        return chatClient.prompt()
                .user(prompt)
                // 使用顾问类 QuestionAnswerAdvisor
                .advisors(new QuestionAnswerAdvisor(vectorStore))
                .stream()
                .content();
    }
}
