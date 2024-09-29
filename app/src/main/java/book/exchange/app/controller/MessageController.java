package book.exchange.app.controller;

import book.exchange.app.dto.messageDTOs.MessageRequestDTO;
import book.exchange.app.dto.messageDTOs.MessageResponseDTO;
import book.exchange.app.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageResponseDTO> createMessage(
            @RequestBody MessageRequestDTO messageRequestDTO
            ){
        return ResponseEntity.ok(messageService.createMessage(messageRequestDTO));
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateMessage(
            @PathVariable("id") UUID id,
            @RequestBody MessageRequestDTO messageRequestDTO
    ){
        return messageService.updateMessage(id, messageRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable("id") UUID id){
        messageService.deleteMessage(id);
    }
}
