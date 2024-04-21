package com.castruche.cast_games_messages.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class MessageReceptionDto {
    private Long conversationId;
    private String content;
    List<PlayerLightDto> members;
    private LocalDateTime readAt;
    private PlayerLightDto sender;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public LocalDateTime getReadAt() {
        return readAt;
    }

    public void setReadAt(LocalDateTime readAt) {
        this.readAt = readAt;
    }

    public List<PlayerLightDto> getMembers() {
        return members;
    }

    public void setMembers(List<PlayerLightDto> members) {
        this.members = members;
    }

    public PlayerLightDto getSender() {
        return sender;
    }

    public void setSender(PlayerLightDto sender) {
        this.sender = sender;
    }
}
