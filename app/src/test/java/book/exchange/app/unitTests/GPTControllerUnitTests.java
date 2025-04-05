package book.exchange.app.unitTests;

import book.exchange.app.controller.GPTController;
import book.exchange.app.dto.chatGptDTOs.ChatGptRequest;
import book.exchange.app.dto.chatGptDTOs.ChatGptResponse;
import book.exchange.app.dto.chatGptDTOs.Message;
import book.exchange.app.model.Notice;
import book.exchange.app.service.GPTService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(properties = "spring.profiles.active=test")
public class GPTControllerUnitTests {

    @MockBean
    private GPTService gptService;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private GPTController gptController;

    @BeforeEach
    void setUp() {
        reset(gptService, restTemplate);

        Message message = new Message();
        message.setContent("Generated GPT response text");

        ChatGptResponse.Choice choice = new ChatGptResponse.Choice();
        choice.setMessage(message);

        ChatGptResponse mockResponse = new ChatGptResponse();
        mockResponse.setChoices(Collections.singletonList(choice));

        when(restTemplate.postForObject(any(String.class), any(ChatGptRequest.class), eq(ChatGptResponse.class)))
                .thenReturn(mockResponse);
    }

    @Test
    public void testGptControllerGetOneNotice() {

        Notice notice = Notice.builder()
                .id(UUID.randomUUID())
                .userId(UUID.randomUUID())
                .bookId(UUID.randomUUID())
                .comicId(null)
                .periodicalId(null)
                .timePosted(LocalDate.now())
                .build();
        List<Notice> list = Collections.singletonList(notice);

        when(gptService.getNoticesFromGptResponse(anyString()))
                .thenReturn(list);

        List<Notice> response = gptController.chatGptResponse("prompt");

        assertEquals(1, response.size(), "size should be 1");

        verify(restTemplate).postForObject(any(String.class), any(ChatGptRequest.class), eq(ChatGptResponse.class));
        verify(gptService).getNoticesFromGptResponse(anyString());
    }

    @Test
    public void testGptControllerFiveNotices(){

        Notice notice;
        List<Notice> list = new ArrayList<>();
        for(int i = 0; i < 5; i++){

            notice = Notice.builder()
                    .id(UUID.randomUUID())
                    .userId(UUID.randomUUID())
                    .bookId(UUID.randomUUID())
                    .comicId(null)
                    .periodicalId(null)
                    .timePosted(LocalDate.now())
                    .build();

            list.add(notice);
        }

        when(gptService.getNoticesFromGptResponse(anyString()))
                .thenReturn(list);

        List<Notice> response = gptController.chatGptResponse("prompt");

        assertEquals(5, response.size(), "size should be 5");

        verify(restTemplate).postForObject(any(String.class), any(ChatGptRequest.class), eq(ChatGptResponse.class));
        verify(gptService).getNoticesFromGptResponse(anyString());
    }

    @Test
    public void testGptControllerZeroNotices(){

        List<Notice> list = new ArrayList<>();

        when(gptService.getNoticesFromGptResponse(anyString()))
                .thenReturn(list);

        List<Notice> response = gptController.chatGptResponse("prompt");

        assertEquals(0, response.size(), "should be null");

        verify(restTemplate).postForObject(any(String.class), any(ChatGptRequest.class), eq(ChatGptResponse.class));
        verify(gptService).getNoticesFromGptResponse(anyString());
    }
}
