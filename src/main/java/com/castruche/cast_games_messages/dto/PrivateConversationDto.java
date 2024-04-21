package com.castruche.cast_games_messages.dto;

import java.time.LocalDateTime;

public class PrivateConversationDto extends ConversationDto {
    PlayerDto player1;
    PlayerDto player2;

    public PlayerDto getPlayer1() {
        return player1;
    }

    public void setPlayer1(PlayerDto player1) {
        this.player1 = player1;
    }

    public PlayerDto getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerDto player2) {
        this.player2 = player2;
    }
}
