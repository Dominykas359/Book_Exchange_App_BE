package book.exchange.app.controller;

import book.exchange.app.dto.chatGptDTOs.ChatGptRequest;
import book.exchange.app.dto.chatGptDTOs.ChatGptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/gpt")
public class GPTController {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String url;

    @Autowired
    private RestTemplate template;

    @GetMapping("/chat")
    public String chatGptResponse(@RequestParam("prompt") String prompt){

        ChatGptRequest chatGptRequest = new ChatGptRequest(model, prompt);
        ChatGptResponse chatGptResponse = template.postForObject(url, chatGptRequest, ChatGptResponse.class);

        return chatGptResponse.getChoices().get(0).getMessage().getContent();
    }
}
