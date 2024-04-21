package com.castruche.cast_games_messages.dto;

import com.castruche.cast_games_messages.enums.ConversationType;

import java.util.List;

public class ConversationDto extends AbstractDto {

    private List<MessageDto> messages;

    private ConversationType type;

    public List<MessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDto> messages) {
        this.messages = messages;
    }

    public ConversationType getType() {
        return type;
    }

    public void setType(ConversationType type) {
        this.type = type;
    }
}
