package com.castruche.cast_games_messages.dto;

import com.castruche.cast_games_messages.enums.ConversationType;

import java.util.ArrayList;
import java.util.List;

public class ConversationDto extends AbstractDto {

    private String name;
    private List<MessageDto> messages;

    private ConversationType type;

    public List<MessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDto> messages) {
        this.messages = messages;
    }

    public ConversationType getType() {
        return type;
    }

    public void setType(ConversationType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlayerDto> getMembers(){
        List<PlayerDto> members = new ArrayList<>();
        if(this.type.equals(ConversationType.GROUP_CONVERSATION)){
            members.addAll(((GroupConversationDto)this).getPlayers());
        }
        else if(this.type.equals(ConversationType.PRIVATE_CONVERSATION)){
            members.add(((PrivateConversationDto)this).getPlayer1());
            members.add(((PrivateConversationDto)this).getPlayer2());
        }
        return members;
    }
}
