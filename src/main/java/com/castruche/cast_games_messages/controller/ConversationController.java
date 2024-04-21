package com.castruche.cast_games_messages.controller;


import com.castruche.cast_games_messages.dto.MessageReceptionDto;
import com.castruche.cast_games_messages.service.MessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.castruche.cast_games_messages.controller.ConstantUrl.CONVERSATION;
import static com.castruche.cast_games_messages.controller.ConstantUrl.MESSAGE;

@RestController
@RequestMapping(CONVERSATION)
public class ConversationController {

    private final MessageService messageService;
    public ConversationController(MessageService messageService) {
        this.messageService = messageService;
    }

    /*@PostMapping()
    public MessageReceptionDto getProfile(@RequestBody MessageReceptionDto messageReceptionDto) {
        return messageService.send(messageReceptionDto);
    }*/


}
