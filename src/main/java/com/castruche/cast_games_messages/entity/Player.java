package com.castruche.cast_games_messages.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Player extends AbstractEntity{
    private Long sourcePlayerId;
    private String username;

    @ManyToMany
    @JoinTable(
            name = "conversation_player",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "conversation_id")
    )
    private List<GroupConversation> groupConversations = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getSourcePlayerId() {
        return sourcePlayerId;
    }

    public void setSourcePlayerId(Long sourcePlayerId) {
        this.sourcePlayerId = sourcePlayerId;
    }

    public List<GroupConversation> getGroupConversations() {
        return groupConversations;
    }

    public void setGroupConversations(List<GroupConversation> groupConversations) {
        this.groupConversations = groupConversations;
    }
}
