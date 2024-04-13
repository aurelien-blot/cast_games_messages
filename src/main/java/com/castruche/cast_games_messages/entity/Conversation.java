package com.castruche.cast_games_messages.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)  // Utilisez la stratégie JOINED pour une meilleure clarté des données
public abstract class Conversation extends AbstractEntity{
    @OneToMany(mappedBy = "conversation")
    private List<Message> messages = new ArrayList<>();


    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
