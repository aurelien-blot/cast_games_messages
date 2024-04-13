package com.castruche.cast_games_messages.controller;


import com.castruche.cast_games_messages.dto.MessageDto;
import com.castruche.cast_games_messages.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.castruche.cast_games_messages.controller.ConstantUrl.MESSAGE;

@RestController
@RequestMapping(MESSAGE)
public class MessageController {

    private final MessageService messageService;
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping()
    public MessageDto getProfile(@RequestBody MessageDto messageDto) {
        return messageService.send(messageDto);
    }


}
