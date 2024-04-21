package com.castruche.cast_games_messages.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SeeService {
    private static final Logger logger = LogManager.getLogger(SeeService.class);

    private static final String INIT_CONVERSATIONS = "INIT_CONVERSATIONS";
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    private ConversationService conversationService;
    public SeeService(ConversationService conversationService){
        this.conversationService = conversationService;
    }

    public SseEmitter initConversations(Long playerId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        new Thread(() -> {
            try {
                // Simulate fetching conversations from the database
                List<String> conversations = List.of("Conversation 1", "Conversation 2");
                emitter.send(SseEmitter.event().name(INIT_CONVERSATIONS).data(conversations));
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        }).start();

        return emitter;
    }
}
