package com.castruche.cast_games_messages.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class PrivateConversation extends Conversation {
    @OneToOne
    private User user1;

    @OneToOne
    private User user2;

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }
}
