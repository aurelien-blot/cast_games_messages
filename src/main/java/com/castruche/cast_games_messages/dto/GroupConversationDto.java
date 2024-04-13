package com.castruche.cast_games_messages.dto;

import java.time.LocalDateTime;
import java.util.List;

public class GroupConversationDto extends AbstractDto {
    private String name;

    private List<UserDto> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}
