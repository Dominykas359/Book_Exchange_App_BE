package book.exchange.app.controller;

import book.exchange.app.dto.chatDTOs.ChatRequestDTO;
import book.exchange.app.dto.chatDTOs.ChatResponseDTO;
import book.exchange.app.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chats")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatResponseDTO> createChat(
            @RequestBody ChatRequestDTO chatRequestDTO
            ){
        return ResponseEntity.ok(chatService.createChat(chatRequestDTO));
    }

    @GetMapping("/{id}")
    public List<ChatResponseDTO> getChats(@PathVariable("id") UUID id){
        return chatService.getChatsByUser(id);
    }

    @GetMapping("/{id}/with-messages")
    public ChatResponseDTO getChatById(@PathVariable("id") UUID id){
        return chatService.getChatById(id);
    }
}
