package com.castruche.cast_games_messages.service;

import com.castruche.cast_games_messages.dto.ConversationDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SseService {
    private static final Logger logger = LogManager.getLogger(SseService.class);

    private static final String INIT_CONVERSATIONS = "INIT_CONVERSATIONS";
    private static final String UPDATE_CONVERSATIONS = "UPDATE_CONVERSATIONS";
    private Map<Long, SseEmitter> userEmitters = new ConcurrentHashMap<>();
    private ConversationService conversationService;

    public SseService(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    public SseEmitter initConversations(Long sourcePlayerId) {
        List<ConversationDto> getConversationListForPlayer = conversationService.getConversationListForPlayer(sourcePlayerId);
        return initConversations(sourcePlayerId, getConversationListForPlayer);
    }

    public SseEmitter initConversations(Long sourcePlayerId, List<ConversationDto> conversationList) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        userEmitters.put(sourcePlayerId, emitter);
        emitter.onCompletion(() -> userEmitters.remove(sourcePlayerId));
        emitter.onTimeout(() -> userEmitters.remove(sourcePlayerId));
        emitter.onError((e) -> userEmitters.remove(sourcePlayerId));

        new Thread(() -> {
            try {
                emitter.send(SseEmitter.event().name(INIT_CONVERSATIONS).data(conversationList));
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        }).start();

        return emitter;
    }

    public void updateConversations(Long sourcePlayerId, List<ConversationDto> conversationList) {
        SseEmitter emitter = userEmitters.get(sourcePlayerId);
        if (emitter == null) {
            logger.warn("No emitter found for player " + sourcePlayerId);
            return;
        }
        try {
            emitter.send(SseEmitter.event().name(UPDATE_CONVERSATIONS).data(conversationList));
        } catch (IOException e) {
            userEmitters.remove(sourcePlayerId);
            logger.error("Error sending SSE", e);
            emitter.completeWithError(e);
        }
    }
}
