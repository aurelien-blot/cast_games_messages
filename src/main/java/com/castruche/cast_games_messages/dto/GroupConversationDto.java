package com.castruche.cast_games_messages.dto;

import java.time.LocalDateTime;
import java.util.List;

public class GroupConversationDto extends ConversationDto {
    private String name;

    private List<PlayerDto> players;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlayerDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDto> players) {
        this.players = players;
    }
}
