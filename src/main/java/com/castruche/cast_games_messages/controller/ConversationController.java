package com.castruche.cast_games_messages.controller;


import com.castruche.cast_games_messages.dto.MessageReceptionDto;
import com.castruche.cast_games_messages.service.MessageService;
import com.castruche.cast_games_messages.service.SseService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.castruche.cast_games_messages.controller.ConstantUrl.CONVERSATION;
import static com.castruche.cast_games_messages.controller.ConstantUrl.MESSAGE;

@RestController
@RequestMapping(CONVERSATION)
public class ConversationController {

    private final SseService sseService;
    public ConversationController(SseService sseService) {
        this.sseService = sseService;
    }

    @GetMapping("/stream/{sourcePlayerId}")
    public SseEmitter streamConversations(@PathVariable Long sourcePlayerId) {
        return this.sseService.initConversations(sourcePlayerId);
    }

}
