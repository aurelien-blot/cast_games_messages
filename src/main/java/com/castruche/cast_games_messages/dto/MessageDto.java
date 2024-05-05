package com.castruche.cast_games_messages.dto;

import com.castruche.cast_games_messages.entity.Conversation;
import com.castruche.cast_games_messages.entity.Player;
import com.castruche.cast_games_messages.enums.ConversationType;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.List;

public class MessageDto extends AbstractDto {
    private Long conversationId;
    private ConversationType conversationType;
    private String content;

    private Long authorId;

    private LocalDateTime readAt;

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public LocalDateTime getReadAt() {
        return readAt;
    }

    public void setReadAt(LocalDateTime readAt) {
        this.readAt = readAt;
    }

    public ConversationType getConversationType() {
        return conversationType;
    }

    public void setConversationType(ConversationType conversationType) {
        this.conversationType = conversationType;
    }
}
