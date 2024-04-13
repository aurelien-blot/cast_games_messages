package com.castruche.cast_games_messages.entity;

import jakarta.persistence.Entity;

@Entity
public class User extends AbstractEntity{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
