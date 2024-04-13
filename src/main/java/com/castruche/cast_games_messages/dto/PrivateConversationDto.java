package com.castruche.cast_games_messages.dto;

import java.time.LocalDateTime;

public class PrivateConversationDto extends AbstractDto {
    UserDto user1;
    UserDto user2;

    public UserDto getUser1() {
        return user1;
    }

    public void setUser1(UserDto user1) {
        this.user1 = user1;
    }

    public UserDto getUser2() {
        return user2;
    }

    public void setUser2(UserDto user2) {
        this.user2 = user2;
    }
}
