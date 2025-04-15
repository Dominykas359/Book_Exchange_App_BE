package book.exchange.app.controller;

import book.exchange.app.dto.chatGptDTOs.ChatGptRequest;
import book.exchange.app.dto.chatGptDTOs.ChatGptResponse;
import book.exchange.app.model.Notice;
import book.exchange.app.service.GPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/gpt")
public class GPTController {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String url;

    @Autowired
    private RestTemplate template;

    @Autowired
    private GPTService gptService;

    @GetMapping("/chat")
    public List<Notice> chatGptResponse(@RequestParam("prompt") String prompt) {

        String text = gptService.preparePromptForJsonResponse(prompt);

        ChatGptRequest chatGptRequest = new ChatGptRequest(model, text);
        ChatGptResponse chatGptResponse = template.postForObject(url, chatGptRequest, ChatGptResponse.class);

        String gptTextResponse = chatGptResponse.getChoices().get(0).getMessage().getContent();

        return gptService.getNotices(gptTextResponse);
    }
}
