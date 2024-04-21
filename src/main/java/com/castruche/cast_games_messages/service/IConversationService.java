package com.castruche.cast_games_messages.service;

import com.castruche.cast_games_messages.dto.GroupConversationDto;
import com.castruche.cast_games_messages.dto.MessageReceptionDto;

public interface IConversationService {

    public void createConservation(MessageReceptionDto messageReceptionDto);

}
