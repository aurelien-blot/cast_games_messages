package com.castruche.cast_games_messages.controller;


import com.castruche.cast_games_messages.dto.MessageReceptionDto;
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

   /* @PostMapping()
    public MessageReceptionDto getProfile(@RequestBody MessageReceptionDto messageReceptionDto) {
        return messageService.send(messageReceptionDto);
    }*/


}
