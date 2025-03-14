package book.exchange.app.dto.chatGptDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import book.exchange.app.dto.chatGptDTOs.Message;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGptResponse {

    private List<Choice> choices;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Choice{

        private int index;
        private Message message;
    }
}
